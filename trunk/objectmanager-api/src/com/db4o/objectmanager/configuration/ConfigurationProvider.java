/* Copyright (C) 2004 - 2007 db4objects Inc. http://www.db4o.com */
/**
 * User: gishac
 * Date: Feb 02, 2008
 * Time: 10:15:25 PM
 */


package com.db4o.objectmanager.configuration;

import java.io.Serializable;

import com.db4o.Db4o;
import com.db4o.config.Configuration;
import com.db4o.io.IoAdapter;

/**
 * 
 * @author gish@c
 *
 */
public class ConfigurationProvider implements Serializable {

	transient private ConfigurationFacade _configuration;
	transient IoAdapter _encryptionProvider;
	private boolean _unicodeEnabled;
	private boolean _lockDatabase;
	private boolean _callConstructors;

	public ConfigurationProvider() {
				
	}
	
	public void encryptionProvider(IoAdapter encryptionProvider) {	    
		this._encryptionProvider = encryptionProvider;
	}
	
	public IoAdapter encryptionProvider()
	{
		return this._encryptionProvider;
	}
	
	public void unicodeEnabled(boolean unicodeEnabled){
		this._unicodeEnabled = unicodeEnabled;
	}
	
	public boolean unicodeEnabled(){
		return _unicodeEnabled;
	}
	
	public void lockDatabase(boolean lockDatabase){
		this._lockDatabase = lockDatabase;
	}
	
	public boolean lockDatabase(){
		return this._lockDatabase;
	}

	public ConfigurationFacade configuration() {
		return _configuration;
	}

	public void configuration(ConfigurationFacade configuration) {
		this._configuration = configuration;
	}
	
	public void callConstructors(boolean callConstructors){
		this._callConstructors = callConstructors;
	}
	
	public boolean callConstructors(){
		return this._callConstructors;
	}
	
	/**
	 * Build the configuration object
	 * @param encryptionProvider
	 * @param unicodeEnabled
	 * @param lockDatabase
	 * @param callConstructors
	 * @return
	 */
	public ConfigurationFacade buildConfiguration(IoAdapter encryptionProvider, boolean unicodeEnabled,
			boolean lockDatabase, boolean callConstructors)
	{		
		this._unicodeEnabled = unicodeEnabled;
		this._lockDatabase = lockDatabase;
		this._callConstructors = callConstructors;
		this._encryptionProvider = encryptionProvider;
		return buildConfiguration();
	}
	
	/**
	 * Build the configuration object
	 * @return
	 */
	public ConfigurationFacade buildConfiguration(){
		_configuration = new ConfigurationFacade();
		_configuration.callConstructors(callConstructors());
		_configuration.unicode(unicodeEnabled());
		_configuration.lockDatabaseFile(lockDatabase());
		if(encryptionProvider() != null)
			_configuration.encryptionProvider(encryptionProvider());
		return _configuration;
	}
	
}





















