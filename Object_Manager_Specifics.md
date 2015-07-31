Object Manager can operate on any java or .NET db4o database, because it does not need class definitions to instantiate database objects. Instead of real classes ObjectManager in fact instantiates [Generic Objects](http://developer.db4o.com/Resources/view.aspx/Reference/Implementation_Strategies/Db4o_Reflection_API/GenericReflector), which hold a database object information in an array of fields. This approach is very powerful, however it introduces some limitations.
Java enumerations

Java enumerations (java.lang.Enum) hold the information in name and ordinal fields. However, when an enumeration is used in a persistent object it is not really necessary to save the whole enumeration to the database, as it will be available from the classloader in runtime. That is why db4o only saves the actual enumeration values together with the object, and the values are bind to the enumeration when the object is instantiated. For more information about the enumerations see [Static Fields And Enums](http://developer.db4o.com/Resources/view.aspx/Reference/Implementation_Strategies/Type_Handling/Static_Fields_And_Enums).

This approach allows to save database space and proves to be efficient. However, if you are looking at an enumeration object using ObjectManager, the enumeration will be constructed as a generic object, therefore the binding procedure won't be triggered. This will affect the following:

  * enumeration type fields will show the class name instead of the actual values, for example "com.examples.Colors" instead of "Red", "Green" etc.;
  * you won't be able to constrain queries on the enumeration field values;
  * you won't see "name" and "ordinal" field values querying for the enumeration type data.