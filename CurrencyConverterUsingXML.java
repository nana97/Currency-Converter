import java.math.*;


public class CurrencyConverterUsingXML{
    
    
    public XML XML = new XML();
    
   
    
    public  boolean currencyExists(String currency) {
    	return XML.exists(currency);
    }
    

	public  BigDecimal getRate(String currency) {
		return new BigDecimal(XML.getRate(currency));
	}

    public  BigDecimal convert(BigDecimal amount, String convertFrom, String convertTo){
        BigDecimal solution = amount;
        BigDecimal toUSD = getRate(convertFrom);
    	BigDecimal toFinalCurrency= getRate(convertTo);
    	solution = solution.divide(toUSD,2, RoundingMode.HALF_UP).multiply(toFinalCurrency);
    	return solution.setScale(2, RoundingMode.CEILING);
    }

    public  void addCurrency(String currency, BigDecimal rate) throws Exception{
    	if(currencyExists(currency)) {
    		throw new Exception("\nError: The currency already exists in our database. Try updating the currency.");
    	}else{
    		XML.addEntry(currency, rate.toString());
    	}
    }

    public  void removeCurrency(String currency) throws Exception{
        if (currencyExists(currency)){
            XML.deleteEntry(currency);
        }else{
        	throw new Exception();
        }
    } 
    
    public  void updateCurrency(String currency, BigDecimal rate) throws Exception{
        if (currencyExists(currency)){
        	XML.modifyEntry(currency, rate.toString());
        }else{
            throw new Exception("\nCurrency: " + currency.toUpperCase() + " was not found in our database. Try adding the currency.\n");
        }
    }

    @Override
    public String toString() {
    	return XML.toString();
    }
}