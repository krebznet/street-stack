package com.dunkware.common.util.json;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessagePacker;
import org.msgpack.core.MessageUnpacker;

public class MessagePackTest {

	   public MessagePacker messagePacker = MessagePack.newDefaultBufferPacker();

	    
public static void main(String[] args) throws Exception{
	MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
    packer
            .packInt(1)
            .packString("leo")
            .packArrayHeader(2)
            .packString("xxx-xxxx")
            .packString("yyy-yyyy")
            .packMapHeader(1)
            .packInt(1)
            .packDouble(3.2);
    		
    packer.close(); // Never forget to close (or flush) the buffer

    // Deserialize with MessageUnpacker
    MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray());
    int id = unpacker.unpackInt();             // 1
    String name = unpacker.unpackString();     // "leo"
    int numPhones = unpacker.unpackArrayHeader();  // 2
    String[] phones = new String[numPhones];
    for (int i = 0; i < numPhones; ++i) {
        phones[i] = unpacker.unpackString();   // phones = {"xxx-xxxx", "yyy-yyyy"}
    }
    int keyValues = unpacker.unpackMapHeader();
    for(int i = 0; i < keyValues; i++) {
    	int keyOne = unpacker.unpackInt();
    	double valueOne = unpacker.unpackDouble();
    	System.out.println(keyOne + " " + valueOne);
    }
    
    unpacker.close();

    System.out.println(String.format("id:%d, name:%s, phone:[%s]", id, name, join(phones)));
}

private static String join(String[] in)
{
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < in.length; ++i) {
        if (i > 0) {
            s.append(", ");
        }
        s.append(in[i]);
    }
    return s.toString();
}


}
