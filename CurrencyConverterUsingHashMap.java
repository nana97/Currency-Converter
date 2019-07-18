import java.math.*;
import java.util.*;

//Change this entire section to use an XML file. ADD, UPDATE, REMOVE, DISPLAY.
public class CurrencyConverterUsingHashMap{
    
    private static Hashtable<String, BigDecimal> currencies = new Hashtable<>();

    public CurrencyConverterUsingHashMap(){
        currencies.put("usd", new BigDecimal(1.00).setScale(5,RoundingMode.FLOOR));
        currencies.put("cad", new BigDecimal(1.30472).setScale(5,RoundingMode.FLOOR));
        currencies.put("isk", new BigDecimal(126.354).setScale(5,RoundingMode.FLOOR));
    }
    
    public static boolean currencyExists(String currency) {
    	return currencies.containsKey(currency.toLowerCase());
    }
    
    public static BigDecimal getRate(String currency) {
		return currencies.get(currency);
	}

    public static BigDecimal convert(BigDecimal amount, String convertFrom, String convertTo){
        BigDecimal solution = amount;
        BigDecimal toUSD = getRate(convertFrom);
    	BigDecimal toFinalCurrency= getRate(convertTo);
    	solution = solution.divide(toUSD,2, RoundingMode.HALF_UP).multiply(toFinalCurrency);
    	return solution.setScale(2, RoundingMode.CEILING);
    }

    public static void addCurrency(String name, BigDecimal rate) throws Exception{
    	if(currencies.containsKey(name)) {
    		throw new Exception("\nError: The currency already exists in our database. Try updating the currency.");
    	}else{
    		currencies.put(name, rate);
    	}
    }

    public static void removeCurrency(String name) throws Exception{
        if (currencies.containsKey(name)){
            currencies.remove(name);
        }else{
        	throw new Exception();
        }
    } 
    
    public static void updateCurrency(String name, BigDecimal rate) throws Exception{
        if (currencies.containsKey(name)){
        	currencies.put(name, rate);
        }else{
            throw new Exception("\nCurrency: " + name.toUpperCase() + " was not found in our database. Try adding the currency.\n");
        }
    }

    public static void displayDatabase(){
        System.out.println("Below is our current database:\n ");	
    	Enumeration <String> keys = currencies.keys();
    	Enumeration <BigDecimal> values = currencies.elements();
    	while(keys.hasMoreElements()) 
    		System.out.println(keys.nextElement().toUpperCase() + " has an exchange rate of: " + values.nextElement());	
    	System.out.println();
    }   
}