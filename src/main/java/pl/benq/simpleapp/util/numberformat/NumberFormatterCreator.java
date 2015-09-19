package pl.benq.simpleapp.util.numberformat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFormatterCreator implements AbstractNumberCreator {

	public static final int PREFIX_LENGTH = 5;
	public static final int MOBILE_TYPE_NUMBER_LENGTH = 9;
	public static final int HOME_TYPE_NUMBER_LENGTH = 7;

	public AbstractNumberFormatter create(String rawNumber) {

		String prefix = null;
		if (isPrefixed(rawNumber)) {
			prefix = getPrefixFromNumber(rawNumber);
			rawNumber = removePrefixFromNumber(rawNumber);
		}
		Integer numberLength = rawNumber.length();
		
		switch(numberLength){
			case MOBILE_TYPE_NUMBER_LENGTH:
					return new MobileNumberFormatter(rawNumber,prefix);
			case HOME_TYPE_NUMBER_LENGTH:
				return new HomeNumberFormatter(rawNumber,prefix);
			default:
				return null;				
		}
	}

	private String getPrefixFromNumber(String rawNumber) {
		return rawNumber.substring(0, PREFIX_LENGTH);
	}

	private String removePrefixFromNumber(String number) {
		return number.substring(PREFIX_LENGTH);
	}

	private boolean isPrefixed(String number) {
		String prefix = "\\(\\+\\d{2}\\)";
		Pattern prefixPattern = Pattern.compile(prefix);
		Matcher matcher = prefixPattern.matcher(number);

		if (matcher.find())
			return true;
		else
			return false;
	}
}
