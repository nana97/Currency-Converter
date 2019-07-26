import java.util.*;
import java.math.*;


public class ApplicationP3{

	public static BigDecimal conversionAmount = BigDecimal.ZERO;
	public static final BigDecimal zero = new BigDecimal("0");
	public static String convertFrom;
	public static String convertTo;	
	
	public static final String FROM = "Please enter a currency to convert from: ";
	public static final String TO = "Please enter a currency to convert to: ";
	public static String action = "";
	
	public static Scanner scan = new Scanner(System.in);
	  
	public static XML XML = new XML();
	public static CurrencyConverterUsingXML Converter = new CurrencyConverterUsingXML();
	
	
	public static void main(String[] args) {		
		System.out.println("Welcome to the Currency Converter!\n");	
		System.out.println(Converter.toString());
		startMenu();	

	}
	
	public static void startMenu() {
		
		System.out.println("Press A if you would like to Add a Currency to our database.");
		System.out.println("Press U if you would like to Update a Currency in our database.");
		System.out.println("Press R if you would like to Remove a Currency from our database.");
		System.out.println("Press C if you would like to Perform a Conversion.");
		System.out.println("Press E if you would like to Exit the Application.");
		System.out.println("Press D if you would like to Display our Database.");
		System.out.println("Press I if you would like to Restore the Database to its Default Values.\n");
		System.out.println("Enter your request below: ");
		action = scan.next().toLowerCase();
		performAction();	
	}
	
	public static void performAction() {
		
		switch(action) {
		case "a": 
			addCurrencyUserInterface();
			break;
		case "u":
			updateCurrencyUserInterface();
			break;
		case "r":
			removeCurrencyUserInterface();
			break;
		case "c":
			conversionProcessor();
			break;
		case "d":
			System.out.println(Converter.toString());
			nextOption();
			break;
		case "i":
			Converter.XML.initializeFile();
			System.out.println(Converter.toString());
			nextOption();
			break;
		default:
			exit();		
		}	
	}
	
	public static void nextOption(){
		
		System.out.println("Press R if you would like Repeat your last action.");
		System.out.println("Press M if you would like to Return to the Start Menu.");
		System.out.println("Press E if you would like to Exit the Application.\n");
		System.out.println("Enter your request below: ");
		
		String choice = scan.next().toLowerCase();	
		
		switch(choice) {
		case "m": 
			startMenu();
		case "r":
			performAction();
		default:
			exit();	
		}
	}

	public static void exit() {
		
		System.out.println("Thank you for using Currency Converter!");	
		scan.close();
		System.exit(0);
	}
	
	public static void conversionProcessor() {
		
		currencyUserInterface(FROM);
		currencyUserInterface(TO);
		conversionAmountUserInterface();
		nextOption();
	}
	
	public static void currencyUserInterface(String question) {
		
		System.out.println(question);
		String currencyToConvert = scan.next();
		
		if(Converter.currencyExists(currencyToConvert)) {
			if(question.equals(FROM)) convertFrom = currencyToConvert;
			if(question.equals(TO)) convertTo = currencyToConvert;
		}
		else {
			System.out.println("Sorry, the currency is not in our database.");
			System.out.println(Converter.toString());
			nextOption();
		}
	}	
	
	public static void conversionAmountUserInterface() {
		
		System.out.println("Enter a non-negative value to convert: " );
		String b = scan.next();
		
		try { 
			conversionAmount = new BigDecimal(b);
		}
		catch (java.lang.NumberFormatException e) {
			System.out.println("Error: Non-numerical Input. ");
			conversionAmount = BigDecimal.ZERO; 
		}
		if(zero.compareTo(conversionAmount)==1) {
			System.out.println("Error: Negative value entered.");
			conversionAmount = BigDecimal.ZERO;
		}
		if(conversionAmount == BigDecimal.ZERO) nextOption();
		else System.out.println("" + conversionAmount + " "+ convertFrom.toUpperCase() + " is equivalent to " + Converter.convert(conversionAmount, convertFrom, convertTo) + " " + convertTo.toUpperCase()+"\n");
	}

	public static void addCurrencyUserInterface() {
		
		System.out.println("Please enter currency to add: ");
		String currency = scan.next();
		System.out.println("Please enter the exchange rate in USD. [Example: 1 USD = 1.31 CAD. Entry would be 1.31]: ");
		String exchangeRate = scan.next();
		
		try {
			BigDecimal exchangeRateValue = new BigDecimal(exchangeRate);
			
			if(exchangeRateValue.compareTo(zero)==1) {
				Converter.addCurrency(currency, exchangeRateValue);
				System.out.println("\nYour request was successfully processed!\n");
				System.out.println(Converter.toString());
				nextOption();
			}
			else { 
				throw new Exception("Exchange rate must be greater than 0.");
			}
		} 
		catch (Exception e) {
			System.out.println("\nError. There was a problem with your input." + e.getMessage() + "\n ");	
			nextOption();
		}			
	}	
	
	public static void updateCurrencyUserInterface() {
		
		System.out.println("Please enter currency to update: ");
		String currency = scan.next();
		System.out.println("Please enter the exchange rate in USD. [Example: 1 USD = 1.31 CAD. Entry would be 1.31]: ");
		String exchangeRate = scan.next();
		
		try {
			BigDecimal exchangeRateValue = new BigDecimal(exchangeRate);
			
			if(exchangeRateValue.compareTo(zero) ==1 && !currency.equals("usd")) {
				Converter.updateCurrency(currency, exchangeRateValue);
				System.out.println("\nYour request was successfully processed!\n");
				System.out.println(Converter.toString());
				nextOption();
			}
			else { 
				throw new Exception(" Exchange rate must be greater than 0 and currency USD cannot be changed.");
			}
		} 
		catch (Exception e) {
			System.out.println("\nError. There was a problem with your input." + e.getMessage() + "\n ");	
			nextOption();
		}			
	}
		
	public static void removeCurrencyUserInterface() {
		
		System.out.println("Enter a currency to Remove: ");
		String currency = scan.next();
		
		if(currency.equals(convertFrom)||currency.equals("usd")) {
			System.out.println("Sorry, the requested action could not be completed.");
		}
		else {
			try {
				Converter.removeCurrency(currency);
				System.out.println("\nYour request was sucessfully processed!\n");
				System.out.println(Converter);
				nextOption();
			} 
			catch (Exception e) {
				System.out.println("\nError. The currency does not exist in our database.\n");	
				nextOption();
			}	
		}
	}	
}
