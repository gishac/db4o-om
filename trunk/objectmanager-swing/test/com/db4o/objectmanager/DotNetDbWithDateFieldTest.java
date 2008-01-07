package com.db4o.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.config.TypeAlias;
import com.db4o.config.WildcardAlias;
import com.db4o.query.Predicate;

/**
 * @author gishac
 * 
 */
 
public class DotNetDbWithDateFieldTest {

	/***
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectContainer db = getDb();
		List<StorageTest> result = executeTestQuery(db);
		for(StorageTest item : result)
		{
			//The date field is null, if you read the objects from dotnet the values are correct.
			//this is producing a null reference exception in the object manager
			System.out.println(item.getVarString() + " Creation date: " + item.getVarDate());
		}
		db.close();
	}
	
	private static ObjectContainer getDb()
	{
		ObjectContainer db = null;
		File currentPath = new File(".");
		String dbFile;
		try {
			dbFile = currentPath.getCanonicalPath() + "\\src\\com\\db4o\\test\\dotnetDB.yap";
			System.out.println(dbFile);
			db = Db4o.openFile(getConfiguration(),dbFile );
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return db;
	}

	
	/**
	 * Build the configuration to read the objects stored in dotnet
	 * @return 
	 */
	private static Configuration getConfiguration() {
		Configuration configuration = Db4o.newConfiguration();
		configuration.addAlias(new WildcardAlias(
				"Db4oInterfaceStorageTest.*, Db4oInterfaceStorageTest",
				"com.db4o.test.*"));
		configuration.addAlias(new TypeAlias(
				"Db4objects.Db4o.Ext.Db4oDatabase, Db4objects.Db4o",
				"com.db4o.ext.Db4oDatabase"));
		return configuration;
	}	
	
	
	/**
	 * Gets all the objects of the type StorageTest from the database
	 * @param db
	 * @return
	 */
	private static List executeTestQuery(ObjectContainer db)
	{
		return db.query(new Predicate<StorageTest> ()
				{
					@Override
					public boolean match(StorageTest item) {
						return true;
					}
				});
	}
}

/**** Dot Net Class Definition *****/
/*
using System;
using System.Collections.Generic;
using System.Text;

namespace Db4oInterfaceStorageTest
{
    public class StorageTest
    {
        private int varInt;

        public int VarInt
        {
            get { return varInt; }
            set { varInt = value; }
        }

        private string varString;

        public string VarString
        {
            get { return varString; }
            set { varString = value; }
        }

        private DateTime varDate;

        public DateTime VarDate
        {
            get { return varDate; }
            set { varDate = value; }
        }


    }
}

****************************************/

