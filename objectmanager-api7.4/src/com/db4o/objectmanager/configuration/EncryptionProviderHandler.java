package com.db4o.objectmanager.configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.db4o.io.IoAdapter;

public class EncryptionProviderHandler {

	public static IoAdapter getEncryptionProvider(Class clazz, String password)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		Object instance = null;
		if (clazz != null) {
			Constructor[] constructors = clazz.getConstructors();
			if (constructors.length > 0) {
				Constructor ctor;
				//Try to find a default constructor which receives the password
				ctor = clazz.getConstructor(String.class);
				if (ctor == null) {
					//Use the default constructor
					//We have to set the password but the IoAdapter class doesn't defines an standard
					//interface in order to set the password property via reflections
					ctor = clazz.getConstructor();
					instance = ctor.newInstance();
				} else {
					instance = ctor.newInstance(password);
				}
				if (instance.getClass().getSuperclass() == IoAdapter.class)
					System.out.println("Created IoAdapter: " + clazz.getName());
				else
					System.out.println("Created but not IoAdapter: "
							+ clazz.getName());
			}
		}
		return (instance != null)? (IoAdapter) instance : null;

	}

}
