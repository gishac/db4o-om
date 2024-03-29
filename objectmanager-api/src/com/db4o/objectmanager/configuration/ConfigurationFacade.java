/* Copyright (C) 2004 - 2007 db4objects Inc. http://www.db4o.com */
/**
 * User: gishac
 * Date: Apr 14, 2008
 * Time: 11:42:03 PM
 */
package com.db4o.objectmanager.configuration;

import java.io.Serializable;

import com.db4o.io.IoAdapter;

public class ConfigurationFacade implements Serializable {

	transient IoAdapter _encryptionProvider;
	private boolean _unicode;
	private boolean _lockDatabaseFile;
	private boolean _callConstructors;
	private String _encryptionPassword;
	private String _encriptionProviderPath;
	public static String CONFIGURATION_FILE_PATH = ".\\configuration.cfg";
	
	public ConfigurationFacade(){
		
	}
	
	public String encriptionProviderPath() {
		return _encriptionProviderPath;
	}
	public void encriptionProviderPath(String providerPath) {
		_encriptionProviderPath = providerPath;
	}
	public String encryptionPassword() {
		return _encryptionPassword;
	}
	public void encryptionPassword(String password) {
		_encryptionPassword = password;
	}
	public IoAdapter encryptionProvider() {
		return _encryptionProvider;
	}
	public void encryptionProvider(IoAdapter provider) {
		_encryptionProvider = provider;
	}
	public boolean unicode() {
		return _unicode;
	}
	public void unicode(boolean enabled) {
		_unicode = enabled;
	}
	public boolean lockDatabaseFile() {
		return _lockDatabaseFile;
	}
	public void lockDatabaseFile(boolean lockDatabaseFile) {
		_lockDatabaseFile = lockDatabaseFile;
	}
	public boolean callConstructors() {
		return _callConstructors;
	}
	public void callConstructors(boolean callConstructors) {
		_callConstructors = callConstructors;
	}
	
	
}
