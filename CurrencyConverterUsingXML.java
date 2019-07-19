import java.math.*;

//Change this entire section to use an XML file. ADD, UPDATE, REMOVE, DISPLAY.
public class CurrencyConverterUsingXML{
    
    
    public static XML XML = new XML();
    
    public CurrencyConverterUsingXML(){
    }
    
    public static boolean currencyExists(String currency) {
    	return XML.exists(currency);
    }
    

	public static BigDecimal getRate(String currency) {
		return new BigDecimal(XML.getRate(currency));
	}

    public static BigDecimal convert(BigDecimal amount, String convertFrom, String convertTo){
        BigDecimal solution = amount;
        BigDecimal toUSD = getRate(convertFrom);
    	BigDecimal toFinalCurrency= getRate(convertTo);
    	solution = solution.divide(toUSD,2, RoundingMode.HALF_UP).multiply(toFinalCurrency);
    	return solution.setScale(2, RoundingMode.CEILING);
    }

    public static void addCurrency(String currency, BigDecimal rate) throws Exception{
    	if(currencyExists(currency)) {
    		throw new Exception("\nError: The currency already exists in our database. Try updating the currency.");
    	}else{
    		XML.addEntry(currency, rate.toString());
    	}
    }

    public static void removeCurrency(String currency) throws Exception{
        if (currencyExists(currency)){
            XML.deleteEntry(currency);
        }else{
        	throw new Exception();
        }
    } 
    
    public static void updateCurrency(String currency, BigDecimal rate) throws Exception{
        if (currencyExists(currency)){
        	XML.modifyEntry(currency, rate.toString());
        }else{
            throw new Exception("\nCurrency: " + currency.toUpperCase() + " was not found in our database. Try adding the currency.\n");
        }
    }

    public static void displayDatabase(){
    	System.out.println("\nBelow is our Current Database: \n");
        XML.printFile();
    }
}