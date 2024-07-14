package com.dunkware.spring.messaging.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dunkware.common.util.json.DunkJson;

public class StreamingAdapter implements StreamingResponseBody {

	private BlockingQueue<Object> streamQueue = new LinkedBlockingQueue<Object>();

	private Status status;

	private String identifier;

	private List<StreamingAdapterListener> listeners = new ArrayList<StreamingAdapterListener>();
	private Semaphore listenerLock = new Semaphore(1);

	private boolean serverDisconnect = false;

	public static enum Status {
		Connected, ClientDisconnect, ServerDisconnect
	}

	public StreamingAdapter(String identifer) {
		status = Status.Connected;
		this.identifier = identifer;
	}

	public boolean isConnected() {
		if (status == Status.Connected) {
			return true;
		}
		return false;
	}

	public void disconnect() {
		serverDisconnect = true;
		notifyServerDisconnect();
	}

	public void removeListener(StreamingAdapterListener listener) {
		Thread runner = new Thread() {

			public void run() {
				try {
					listenerLock.acquire();
					listeners.remove(listener);
				} catch (Exception e) {
					
				} finally {
					listenerLock.release();
				}

			}
		};

		runner.start();

	}

	public void addListener(StreamingAdapterListener listener) {
		Thread runner = new Thread() {

			public void run() {
				try {
					listenerLock.acquire();
					listeners.add(listener);
				} catch (Exception e) {
					
				} finally {
					listenerLock.release();
				}

			}
		};

		runner.start();
	}

	/**
	 * Okay so this is the method that has to stay in a look and not return
	 * otherwise the streaming response will close. we need to gracefully handle a
	 * client close.
	 */
	@Override
	public void writeTo(OutputStream outputStream) throws IOException {
		PrintWriter writer = new PrintWriter(outputStream);

		while (true) {
			try {
				String serialized = null;
				try {
					while (!isConnected()) {
						try {
							Thread.sleep(250);
							if (serverDisconnect) {
								return;
							}
						} catch (Exception e) {
							// not the beset but all good
						}
					}
					Object object = streamQueue.poll(5, TimeUnit.SECONDS);
					if (isConnected() == false) {
						continue;
					}
					// else if object not null send it
					if (object == null) {
						continue;
					}

					serialized = DunkJson.serialize(object);
					writer.println(serialized);
					writer.flush();
					outputStream.flush();
				} catch (Exception e) {
					// okay likely client connect problem
					status = Status.ClientDisconnect;
					notifyClientDisconnect();
					// outputStream.close();
					// writer.close();

					return;
				}

				System.out.println("send messsage");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}

	}

	public String getIdentifier() {
		return identifier;
	}

	public void send(Object jsonObject) {
		streamQueue.add(jsonObject);
	}

	private void notifyServerDisconnect() {
		Thread runner = new Thread() {
			public void run() {
				try {
					listenerLock.acquire();
					for (StreamingAdapterListener streamingListener : listeners) {
						streamingListener.serverDisconnect(StreamingAdapter.this);
					}
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally {
					listenerLock.release();
				}
			}
		};
		runner.start();

	}

	private void notifyClientDisconnect() {
		Thread runner = new Thread() {
			public void run() {
				try {
					listenerLock.acquire();
					for (StreamingAdapterListener streamingListener : listeners) {
						streamingListener.clientDisconnect(StreamingAdapter.this);
					}
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally {
					listenerLock.release();
				}
			}
		};
		runner.start();

	}
}
