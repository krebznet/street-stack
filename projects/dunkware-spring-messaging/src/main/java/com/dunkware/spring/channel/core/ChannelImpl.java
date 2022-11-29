package com.dunkware.spring.channel.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.dunkware.common.kafka.consumer.DKafkaByteConsumer2;
import com.dunkware.common.kafka.consumer.DKafkaByteHandler2;
import com.dunkware.common.kafka.producer.DKafkaByteProducer;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.ConsumerType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2Spec.OffsetType;
import com.dunkware.common.spec.kafka.DKafkaByteConsumer2SpecBuilder;
import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.uuid.DUUID;
import com.dunkware.spring.channel.Channel;
import com.dunkware.spring.channel.ChannelException;
import com.dunkware.spring.channel.ChannelService;
import com.dunkware.spring.channel.core.ChannelHandler.ChannelSetter;
import com.dunkware.spring.channel.core.ChannelHandler.MessageHandler;
import com.dunkware.spring.channel.core.ChannelHandler.MessageReply;
import com.dunkware.spring.message.Message;
import com.dunkware.spring.message.MessageHelper;
import com.dunkware.spring.message.MessageInterceptor;
import com.dunkware.spring.message.MessageTransport;

public class ChannelImpl implements Channel, DKafkaByteHandler2 {

	private String channelType;
	private String brokers;
	private String consumer;
	private String producer;
	private Map<String, Object> beans;

	private DKafkaByteConsumer2 kafkaConsumer;
	private DKafkaByteProducer kafkaProducer;

	private BlockingQueue<MessageTransport> inboundMessages = new LinkedBlockingQueue<MessageTransport>();

	@Autowired
	private ApplicationContext parentContext;

	@Autowired
	private ChannelService channelService;

	private GenericApplicationContext channelContext;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private MessageConsumer messageConsumer = null;

	private List<MessageInterceptor> messageInterceptors = new ArrayList<MessageInterceptor>();
	private Semaphore messageInterceptorLock = new Semaphore(1);
	private List<ChannelHandler> channelHandlers = new ArrayList<ChannelHandler>();
	private Semaphore channelHandlerLock = new Semaphore(1);

	private DExecutor executor;

	private boolean disposed = false;

	private Marker marker = MarkerFactory.getMarker("Channel");
	
	public void start(DExecutor executor, String type, String brokers, String consumer, String producer,
			Map<String, Object> beans, List<Class<?>> defaultHandlers) throws ChannelException {
		this.channelType = type;
		this.brokers = brokers;
		this.consumer = consumer;
		this.producer = producer;
		this.beans = beans;
		this.executor = executor;
		beans.put("channel", this);

	

		// create the channel handles
		for (Class<?> handlerClass : defaultHandlers) {
			Object handler = null;
			try {
				handler = handlerClass.newInstance();
				parentContext.getAutowireCapableBeanFactory().autowireBean(handler);

			} catch (Exception e) {
				throw new ChannelException("Exception creating/auto wiring default handler class "
						+ handlerClass.getName() + " " + e.toString());
			}
			ChannelHandler channelHandler = null;
			try {
				channelHandler = ChannelHandler.newInstance(handler, this);
				logger.info(marker, "Channel {} Default Handler {} Added", type, channelHandler.getTarget().getClass().getName());
				
				for (MessageReply reply : channelHandler.getMessageReplies()) {
					logger.info(marker, "Channel {} Handler {}  Message Reply Incoming {} Response {}", type,channelHandler.getTarget().getClass().toString(), reply.getMessageType().toString(), reply.getResponseType().toString());
					
				}
				this.channelHandlers.add(channelHandler);
			} catch (Exception e) {
				throw new ChannelException("Exception creating channel handler from default handler "
						+ handlerClass.getName() + " " + e.toString());
			}

			List<ChannelSetter> setters = channelHandler.getChannelSetters();
			for (ChannelSetter setter : setters) {
				Object value = getInjectable(setter.getSetterType());
				if (value == null) {
					throw new ChannelException(
							"Channel Setter type " + setter.getSetterType().getName() + " not found in beans");
				}
				try {
					setter.getMethod().invoke(channelHandler.getTarget(), value);
				} catch (Exception e) {
					throw new ChannelException("Exception setting channel setter method value "
							+ value.getClass().getName() + " handler class "
							+ channelHandler.getTarget().getClass().getName() + " " + e.toString());
				}

			}

		}

		// create/start/add handler kafka consumer
		try {
			DKafkaByteConsumer2Spec spec = DKafkaByteConsumer2SpecBuilder
					.newBuilder(ConsumerType.Auto, OffsetType.Latest).addBroker(brokers).addTopic(consumer)
					.setClientAndGroup("DataContainerCluster_" + DUUID.randomUUID(4),
							"ChannelConsumer" + DUUID.randomUUID(4))
					.build();
			kafkaConsumer = DKafkaByteConsumer2.newInstance(spec);
			kafkaConsumer.start();
			kafkaConsumer.addStreamHandler(this);
		} catch (Exception e) {
			throw new ChannelException("Exception creating kafka consumer " + e.toString());
		}

		// create producer
		try {
			kafkaProducer = DKafkaByteProducer.newInstance(brokers, producer, "Channel" + DUUID.randomUUID(5));
		} catch (Exception e) {
			kafkaConsumer.dispose();
			throw new ChannelException("Exception Creating Channel Producer " + e.toString());
		}

		messageConsumer = new MessageConsumer();
		messageConsumer.start();

	}

	@Override
	public void dispose() {
		if (!disposed) {
			messageConsumer.interrupt();
			kafkaConsumer.dispose();
			kafkaProducer.dispose();
			// todo notify handlers;
		}
	}

	@Override // object ?
	public void send(Object payload) throws ChannelException {
		try {
			String messageId = DUUID.randomUUID(5);
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put(Message.HEADER_KEY_MESSAGE_ID, messageId);
			MessageTransport transport = new MessageTransport();
			String payloadString = null;
			try {
				payloadString = DJson.serialize(payload);
			} catch (Exception e) {
				throw new ChannelException("Exception serializing object payload " + e.toString());
			}
			transport.setHeaders(headers);
			transport.setPayload(payloadString);
			transport.setPayloadClass(payload.getClass().getName());

			String serialized = DJson.serialize(transport);
			byte[] bytes = serialized.getBytes();
			kafkaProducer.sendBytes(bytes);
			if(logger.isTraceEnabled()) { 
				logger.trace(marker, "Channel {} message sent {} ",channelType,payload.getClass().getName());
			}
		} catch (Exception e) {
			throw new ChannelException("Exception Sending Channel Message " + e.toString());
		}
	}

	@Override // ? object
	public Message sendReply(Object payload) throws ChannelException {
	
		Message message = Message.newInstance(payload);
		String requestId = DUUID.randomUUID(5);
		message.setHeader(Message.HEADER_KEY_MESSAGE_ID, requestId);
		message.setHeader(Message.HEADER_KEY_MESSAGE_TYPE, Message.HEADER_MESSAGE_TYPE_REQUEST);

		BlockingQueue<Object> responseQueue = new LinkedBlockingQueue<Object>();

		MessageInterceptor interceptor = new MessageInterceptor() {

			@Override
			public boolean intercept(Message message) {
				if (message.hasHeader(Message.HEADER_KEY_MESSAGE_REQUEST_ID)) {
					if (message.getHeader(Message.HEADER_KEY_MESSAGE_REQUEST_ID).equals(requestId)) {
						if (message.hasHeader(Message.HEADER_KEY_MESSAGE_RESPONSE_ERROR)) {
							responseQueue.add(message.getHeader(Message.HEADER_KEY_MESSAGE_RESPONSE_ERROR));
						} else {
							if(logger.isDebugEnabled()) { 
								logger.debug(marker, "Channel send/reply response type {} receieved for request type {}", channelType, message.getPayload().getClass().getName(), payload.getClass().getName());
							}
							responseQueue.add(message.getPayload());
						}
						removeMessageInterceptor(this);
						return true;
					}
				}
				return false;
			}
		};
		addMessageInterceptor(interceptor);
		try {
			MessageTransport transport = new MessageTransport();
			transport.setHeaders(message.getHeaders());
			String payloadString = null;
			try {
				payloadString = DJson.serialize(payload);
			} catch (Exception e) {
				throw new ChannelException("Exception serializing object payload " + e.toString());
			}
			transport.setPayload(payloadString);
			transport.setPayloadClass(payload.getClass().getName());
			String serialized = DJson.serialize(transport);
			byte[] bytes = serialized.getBytes();
			if(logger.isDebugEnabled()) { 
				logger.debug(marker, "Channel {} send/reply starting payload {}", channelType, payload.getClass().getName());
			}
			kafkaProducer.sendBytes(bytes);
		} catch (Exception e) {
			throw new ChannelException("Fucked up sending request reply " + e.toString());
		}
		try {
			Object response = responseQueue.poll(10, TimeUnit.MINUTES);
			if (response == null) {
				throw new ChannelException("Did not get a response within timeout limit");
			}
			if (response instanceof String) {
				String error = (String) response;
				throw new ChannelException("Response Error " + error);
			}
			return Message.newInstance(response);
		} catch (Exception e) {
			throw new ChannelException("Shit Fucked Up on erquest reply " + e.toString());
		}
	}
	

	@Override
	public void send(Message message) throws ChannelException {
		try {
			if(logger.isDebugEnabled()) { 
				if(message.getPayload() != null) { 
					logger.debug(marker, "Channel {} sending message with payload type {}",channelType, message.getPayload().getClass().getName());					
				} else { 
					logger.debug(marker, "Channel {} sending message with no payload headers  {}",channelType, message.getHeaders().toString());
				}

			}
			MessageTransport port = MessageHelper.toTransport(message);
			String portString = DJson.serialize(port);
			kafkaProducer.sendBytes(portString.getBytes());
		} catch (Exception e) {
			throw new ChannelException("Exception Sending Message " + e.toString());
		}
		
	}

	@Override
	public void addHandler(final Object annotedHandler) throws ChannelException {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {
					
					ChannelHandler handler = ChannelHandler.newInstance(annotedHandler, ChannelImpl.this);
					channelHandlerLock.acquire();
					channelHandlers.add(handler);
					if(logger.isDebugEnabled()) { 
						logger.debug(marker, "Channel {} added handler {}", channelType,annotedHandler.getClass().getName());
					}
				} catch (Exception e) {
					logger.error("Exception adding channel handler class " +  annotedHandler.getClass().getName() + " " + e.toString());
				} finally  { 
					channelHandlerLock.release();
				}
				
				
			}
		};

		executor.execute(runner);
	}

	@Override
	public void removeHandler(final Object annotedHandler) throws ChannelException {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				try {

					channelHandlerLock.acquire();
					ChannelHandler removeHandler = null;
					for (ChannelHandler channelHandler : channelHandlers) {
						if(channelHandler.getTarget().equals(annotedHandler)) { 
							removeHandler = channelHandler;
							break;
						}
					}
					if(removeHandler != null) { 
						channelHandlers.remove(removeHandler);
						if(logger.isDebugEnabled()) { 
							logger.debug(marker, "Channel {} removed handler {}", channelType,annotedHandler.getClass().getName());
						}
					}

				} catch (Exception e) {
					logger.error("Exception removing channel handler class " +  annotedHandler.getClass().getName() + " " + e.toString());
				} finally  { 
					channelHandlerLock.release();
				}
				
				
			}
		};

		executor.execute(runner);
	}

	@Override
	public void addMessageInterceptor(final MessageInterceptor interceptor) {
		
		Thread fucker = new Thread() { 
			
			@Override
			public void run() {
				try {
					messageInterceptorLock.acquire();
					messageInterceptors.add(interceptor);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					messageInterceptorLock.release();
				}

			}
		};
		
		fucker.start();
	}

	@Override
	public void removeMessageInterceptor(MessageInterceptor interceptor) {
		
		Thread fucker = new Thread() { 
			
			@Override
			public void run() {
				try {
					messageInterceptorLock.acquire();
					messageInterceptors.remove(interceptor);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					messageInterceptorLock.release();
				}

			}
		};
		
		fucker.start();
		
	}

	@Override
	public void record(ConsumerRecord<String, byte[]> record) {
		try {
			MessageTransport transport = DJson.getObjectMapper().readValue(record.value(), MessageTransport.class);
			inboundMessages.add(transport);
		} catch (Exception e) {
			logger.error("Exception deserialziing message transport from kafka on channel " + channelType + " "
					+ e.toString());
		}
	}

	public Object getInjectable(Class<?> type) {
		for (String key : beans.keySet()) {
			Object bean = beans.get(key);
			if (type.isInstance(bean)) {
				return bean;
			}
		}
		return null;
	}

	private class MessageConsumer extends Thread {

		public void run() {
			while (!interrupted()) {
				try {

					MessageTransport transport = inboundMessages.take();
					Message message = null;
					try {
						message = Message.newInstance(transport);
					} catch (Exception e) {
						logger.error("Exception Parsing Inbound Message " + e.toString());
						continue;
					}
					
					// check if we have  request/reply
					if(message.hasHeader(Message.HEADER_KEY_MESSAGE_TYPE)) {
						if(message.getHeader(Message.HEADER_KEY_MESSAGE_TYPE).equals(Message.HEADER_MESSAGE_TYPE_REQUEST)) { 
							Object payload = message.getPayload();
							if(payload == null) { 
								logger.error("Received request/response message with null payload");
								continue;
							}
							boolean handled = false;
							try {
								channelHandlerLock.acquire();
								for (ChannelHandler channelHandler : channelHandlers) {
									if(channelHandler.hasMessageReply(payload)) { 
										try {
											MessageReply messageReply = channelHandler.getMessageReply(payload);
											Method method = messageReply.getMethod();
											Object response = method.invoke(channelHandler.getTarget(), payload);
											Message responseMessage = Message.newInstance(response);
											responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_TYPE, Message.HEADER_REQUEST_TYPE_RESPONSE);
											responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_REQUEST_ID, message.getHeader(Message.HEADER_KEY_MESSAGE_ID));
											send(responseMessage);
											handled = true; 
										} catch (Exception e) {
											Message responseMessage = Message.newInstance();
											responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_TYPE, Message.HEADER_REQUEST_TYPE_RESPONSE);
											responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_REQUEST_ID, message.getHeader(Message.HEADER_KEY_MESSAGE_ID));
											responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_RESPONSE_ERROR, e.toString());
											try {
												send(responseMessage);
												handled = true;
											} catch (Exception e2) {
												logger.error("Exception sending response error message " + e2.toString());
											}
										}
									}
								}
								if(!handled) {
									// not found --> 
									Message responseMessage = Message.newInstance();
									responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_TYPE, Message.HEADER_REQUEST_TYPE_RESPONSE);
									responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_REQUEST_ID, message.getHeader(Message.HEADER_KEY_MESSAGE_ID));
									responseMessage.setHeader(Message.HEADER_KEY_MESSAGE_RESPONSE_ERROR, "No handlers found");
									try {
										send(responseMessage);
									} catch (Exception e2) {
										logger.error("Exception sending response error message " + e2.toString());
									}
									
								}
							} catch (Exception e) {
								if (e instanceof InvocationTargetException) { 
									logger.error("invocation target exception cause " + e.getCause().getMessage());
									
								}
								logger.error(e.toString());
								// TODO: handle exception
							} finally {
								channelHandlerLock.release();
							}
						
							continue;
						}
					}

					try {
						messageInterceptorLock.acquire();
						for (MessageInterceptor interceptor : messageInterceptors) {
							// route to message interceptors first
							if (interceptor.intercept(message)) {
								continue;
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					} finally { 
						messageInterceptorLock.release();
					}
					

					// we will take care of message replies soon enough.
					try {
						channelHandlerLock.acquire();
						for (ChannelHandler channelHandler : channelHandlers) {
							if (message.getPayload() != null) {
								List<MessageHandler> handlers = channelHandler.getMessageHandlers(message.getPayload());
								for (MessageHandler messageHandler : handlers) {
									try {
										Method method = messageHandler.getMethod();
										messageHandler.getMethod().invoke(channelHandler.getTarget(), message.getPayload());
									} catch (Exception e) {
										logger.error("Exception Invoking Message Handler method "
												+ messageHandler.getMethod().getName() + " class " + e.toString());
										;
									}
								}
							}
						}

					} catch (Exception e) {
						// TODO: handle exception 
					} finally { 
						channelHandlerLock.release();
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}


}
