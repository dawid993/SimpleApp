package pl.benq.simpleapp.util.numberformat;

public class HomeNumberFormatter extends AbstractNumberFormatter {

	public HomeNumberFormatter(String number, String prefix) {
		super(number, prefix);
	}

	@Override
	public String getFormatedNumber() {
		String formattedNumber = String.format("%s %s %s", getNumber().substring(0, 3), 
				getNumber().substring(3, 5), 
				getNumber().substring(5, 7));
		
		if(getPrefix() == null)
			return formattedNumber;
		else
			return getPrefix()+" "+formattedNumber;
	}	
}
