package pl.benq.simpleapp.util.numberformat;

public abstract class AbstractNumberFormatter {

	private String number;
	private String prefix;

	public AbstractNumberFormatter(String number, String prefix) {
		this.number = number;
		this.prefix = prefix;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public abstract String getFormatedNumber();
}
