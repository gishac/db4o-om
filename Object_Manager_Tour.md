Upon starting Object Manager, you will see the Connection Manager:

![http://farm3.static.flickr.com/2158/2489848024_9d8ef5fd51_o.jpg](http://farm3.static.flickr.com/2158/2489848024_9d8ef5fd51_o.jpg)

At this point, you can either:

  * Open a db4o database file
  * Open a connection to a db4o database server

In order to open a db4o database file, simply click Browse and choose the db4o database file to open, then click Connect.

If you just want to try ObjectManager, you can create a demo database by clicking File - Create Demo Db. This will create and open a database for you to use.
Stored Classes Tree

![http://farm4.static.flickr.com/3256/2489031829_cc44ba9ac9_o.jpg](http://farm4.static.flickr.com/3256/2489031829_cc44ba9ac9_o.jpg)

Once you click connect, the main ObjectManager window will open. All of the classes that have been stored will be displayed on the left in a tree:

### Querying for objects ###

To view the objects stored in your db4o database, you must query for it. The query editor is at the top of the main window and looks like this:

![http://farm3.static.flickr.com/2379/2489031683_f793551faf.jpg](http://farm3.static.flickr.com/2379/2489031683_f793551faf.jpg)

To quickly see all of the objects of a certain class, just double click the class name in the stored classes tree. This will automatically build your query for you, and you just have to click the Submit button to the right of the query text area.

### Query Language ###

The Object Manager query language, Sql4o, is based on the SQL query language, so if you know SQL, you should have no problems with Sql4o.

Syntax:

SELECT select\_expr, ...

FROM object\_type

WHERE where\_condition

For example, to query all Contacts in the Demo Db:

FROM demo.objectmanager.model.Contact

To query all contacts with age 50.

FROM demo.objectmanager.model.Contact where age 50
Query Results

After executing a query, you will see the query results in table:

![http://farm4.static.flickr.com/3250/2489031911_1c9928e076_o.jpg](http://farm4.static.flickr.com/3250/2489031911_1c9928e076_o.jpg)

From this table, you can edit all primitive fields, Dates, and Strings by double clicking on a cell.

You can also open an object up in a tree view so that you can descend further into the object:

![http://farm3.static.flickr.com/2215/2489848400_d5caf27a93_o.jpg](http://farm3.static.flickr.com/2215/2489848400_d5caf27a93_o.jpg)

You can also edit fields from this view.

### Database Management ###

The following management features are currently available:

  * Backup
  * Defragment
  * Refactoring (Schema Evolution)

Back-up is available from "Manage-Backup..." menu. It will ask you for a location to store the back-up, once that is chosen, the back-up copy of your database will be created. The backed-up copy is a fully functional database and can be used by replacing your current database.

Defragment is available from "Manage-Defragment" menu or through "Ctrl-Shift-D" shortcut. This will execute the db4o defragmentation process on your database. For more information about the defragment see Defragment.

Refactoring allows you to change the structure of persistent classes in a db4o database. Object Manager helps you to automate the simplest refactoring: renaming of class fields.

In order to rename a class field double-click the class in a "Stored Classes" tree - class view will open on the right panel. Click "Edit" button at the bottom and change field names as necessary.

![http://farm3.static.flickr.com/2117/2489848146_d8e8f7d917_o.jpg](http://farm3.static.flickr.com/2117/2489848146_d8e8f7d917_o.jpg)

When the renaming is done, click "Stop Editing" button at the bottom. Remember to change your application classes accordingly.