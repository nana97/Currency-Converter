import java.math.*;


public class CurrencyConverterforGUI{
    
    
    public static XML XML = new XML();
    
    public CurrencyConverterforGUI(){
    }
    
    public static boolean currencyExists(String currency) {
    	return XML.exists(currency);
    }
    

	public static BigDecimal getRate(String currency) {
		return new BigDecimal(XML.getRate(currency));
	}

    public static BigDecimal convert(BigDecimal amount, String convertFrom, String convertTo)throws Exception{
    	if(currencyExists(convertFrom) && currencyExists(convertTo)) {
	        BigDecimal solution = amount;
	        BigDecimal toUSD = getRate(convertFrom);
	    	BigDecimal toFinalCurrency= getRate(convertTo);
	    	solution = solution.divide(toUSD,2, RoundingMode.HALF_UP).multiply(toFinalCurrency);
	    	return solution.setScale(2, RoundingMode.CEILING);
    	}else {
    		throw new Exception("Error: One of the Currencies does not exist in our database.");
    	}
    }

   
    public static void addCurrency(String currency, BigDecimal rate) throws Exception{
    	if(currencyExists(currency)) {
    		throw new Exception("Error: The Currency already exists in our database. ");
    	}else if (rate.compareTo(BigDecimal.ZERO)<=0) {
    		throw new Exception("Error: Please enter a Numerical rate greater than 0. ");
    	}else{
    		XML.addEntry(currency, rate.toString());
    	}
    }

    
    
    public static void removeCurrency(String currency) throws Exception{
        if (!currencyExists(currency)){
        	throw new Exception("Error: The currency does not exist.");
        }else if (currency.equals("usd")) {
    		throw new Exception("Error: Cannot delete USD currency");
        }else{
        	XML.deleteEntry(currency);
        }
    } 
    
    public static void updateCurrency(String currency, BigDecimal rate) throws Exception{
        if(!currencyExists(currency)){
            throw new Exception("Error: The Currency does not exist in our database. ");
        }else if (currency.equals("usd")) {
    		throw new Exception("Error: Cannot modify USD currency");
        }else if (rate.compareTo(BigDecimal.ZERO)<=0) {
    		throw new Exception("Error: Please enter a Rate greater than 0.");
        }else {
        	XML.modifyEntry(currency, rate.toString());
        }
    }

    
    public String toString() {
    	return XML.toString();
    }
}