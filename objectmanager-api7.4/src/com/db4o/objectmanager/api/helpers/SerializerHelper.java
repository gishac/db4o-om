/* Copyright (C) 2004 - 2007 db4objects Inc. http://www.db4o.com */
/**
 * User: gishac
 * Date: Apr 20, 2008
 * Time: 1:52:50 AM
 */
package com.db4o.objectmanager.api.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializerHelper {
	/**
	 * Serializes an object to the specified file path
	 * @param serializableObject Object to serialize
	 * @param filePath Path to save the serialized object
	 * @throws IOException
	 */
	public static void Serialize(Object serializableObject, String filePath)
			throws IOException {
		FileOutputStream fileStream = new FileOutputStream(filePath);
		ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
		objectStream.writeObject(serializableObject);
		objectStream.flush();
		objectStream.close();
	}

	/**
	 * Deserializes an object
	 * @param filePath Path of the serialized object
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object Deserialize(String filePath) throws IOException, ClassNotFoundException {
		Object result = null;
		if(! new File(filePath).exists())
			return result;
		FileInputStream fileStream = new FileInputStream(filePath);
		ObjectInputStream objectStream = new ObjectInputStream(fileStream);
		result = objectStream.readObject();
		return result;
	}
}
