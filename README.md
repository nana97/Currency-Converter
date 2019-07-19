# Currency-Converter
This repository includes different versions of a Java Currency Converter Application that allows a user to convert from one currency to another. 

Both versions allow the user to:

	Add a Currency to the Database
	Modify the Exchange Rate of an Existing Currency
	Delete a Currency from the Database
	Perform a Conversion
	Display the Database
	Exit the Application at any time
	
These applications are centered around a Console Menu.

The First Version uses a HashMap: 
 
This version uses maps the Currency keys to Exchange Rate values. So the Application only allows the User to access modifications to the original data in the same session.

	To use this Application run the files Application1 along with the object CurrencyConverterUsingHashMap and XML..



The Second Version uses an XML file:

It allows the User to perform all of the aforementioned operations, with the added option of Resetting the Database.
By using an XML file to save a Currency Database, this application allows the user to freely add and modify currencies between sessions. 

	To use this xml version, please change the xmlFilePath variable in the XML object to one on your machine. To run this Application run the files Application2 along with the objects CurrencyConverterUsingHashMap and XML.


