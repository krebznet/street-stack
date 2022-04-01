package com.dunkware.common.util.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class DSerializable {
	
	public static String serializeObject(Serializable serializable) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(serializable);
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	/**
	 * Derserializes an Object
	 * 
	 * @param serialized
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserializeObject(String serialized) throws IOException, ClassNotFoundException {
		byte[] data = Base64.getDecoder().decode(serialized);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		return o;

	}

}
