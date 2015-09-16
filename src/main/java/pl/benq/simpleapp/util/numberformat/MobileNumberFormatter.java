package pl.benq.simpleapp.util.numberformat;

public class MobileNumberFormatter extends AbstractNumberFormatter {

	public MobileNumberFormatter(String number, String prefix) {
		super(number, prefix);
	}

	@Override
	public String getFormatedNumber() {
		String formattedNumber = String.format("%s %s %s", getNumber().substring(0, 3), 
				getNumber().substring(3, 6), 
				getNumber().substring(6, 9));
		
		if(getPrefix() == null)
			return formattedNumber;
		else
			return getPrefix()+" "+formattedNumber;
	}

	
}
