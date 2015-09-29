package pl.benq.simpleapp.util.billcalculate;

import pl.benq.simpleapp.model.MonthPowerUsage;
import pl.benq.simpleapp.model.Person;

public abstract class BillCalculatorStrategy {
	private Double abonament;
	private Double powerUsageCost;
	private Integer vatPercentage;

	public Integer getVatPercentage() {
		return vatPercentage;
	}

	public Double getAbonament() {
		return abonament;
	}

	public Double getPowerUsageCost() {
		return powerUsageCost;
	}

	public Double getTotalCost() {
		return abonament + powerUsageCost;
	}
	
	public void setVatPercentage(Integer vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

	protected void setAbonament(Double abonament) {
		this.abonament = abonament;
	}

	protected void setPowerUsageCost(Double powerUsageCost) {
		this.powerUsageCost = powerUsageCost;
	}

	public abstract void calculateCosts(Person person, MonthPowerUsage powerUsage);
}
