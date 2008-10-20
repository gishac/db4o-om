/**
 * 
 */
package com.db4o.objectmanager.test.api;

import db4ounit.extensions.*;

/**
 * @author German
 *
 */
public class AllTests extends Db4oTestSuite {

	protected Class[] testCases() {
		return new Class[]{
				DatabaseInspectorTestCase.class
		};
	}
	
	public static void main(String[] args) {
		new AllTests().runSoloAndClientServer();
	}

}