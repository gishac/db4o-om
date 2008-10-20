/**
 * 
 */
package com.db4o.objectmanager.test.api;

import com.db4o.query.Query;
import db4ounit.Assert;
import db4ounit.extensions.AbstractDb4oTestCase;

/**
 * @author German
 *
 */
public class DatabaseInspectorTestCase extends AbstractDb4oTestCase {

	public static class Data {
		public Data _prev;
		public String _name;
		public int _order;

		public Data(Data prev) {
			this._prev = prev;
		}

		public Data get_prev() {
			return _prev;
		}

		public void set_prev(Data _prev) {
			this._prev = _prev;
		}

		public String getName() {
			return _name;
		}

		public void setName(String name) {
			this._name = name;
		}

		public int getOrder() {
			return _order;
		}

		public void setOrder(int order) {
			this._order = order;
		}
	}
	
	protected void store() throws Exception {
		Data a=new Data(null);
		Data b=new Data(a);
		store(b);
	}
	
	public void testNullIdentity() {
		Query query = newQuery(Data.class);
		query.descend("_prev").constrain(null).identity();
		Assert.areEqual(1,query.execute().size());
	}
}
