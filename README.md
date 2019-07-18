# Currency-Converter
This repository includes 2 Different Object Oriented Currency Converters applications that allow the user to convert from one Currency to another. 

Both have the same basic functionality. They both allow the user to:

	Add a Currency to the Database
	Modify the Exchange Rate of an Exising Currency
	Delete a Currency from the Database
	Perform a Conversion
	Display the Database
	Exit the Application at any time
  
The functionality is very simple. The applications start with a Console Menu that responds to the user input requests. It gives the user the 6 aforementioned options. `

There is a small difference between the two applications.

Application P2 uses a HashMap to map the Currency keys to Exchange Rate values. So this limits the Application by only allowing the user to use user input currency additions and modifications in one session. This Apllication uses the Application and Currency Converter Objects.

Application P3 uses an XML file to save a Currency Database. This allows the user to freely add and modify currencies in between sessions. To use this xml version, please change the xmlFilePath variable in the XML object to one on your machine. This Application uses the Application, Currency Converter, and XML Objects.
