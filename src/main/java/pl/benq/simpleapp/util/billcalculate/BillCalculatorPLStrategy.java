package pl.benq.simpleapp.util.billcalculate;

import org.springframework.stereotype.Component;

import pl.benq.simpleapp.model.MonthPowerUsage;
import pl.benq.simpleapp.model.Person;
import pl.benq.simpleapp.model.Suite;

@Component("billCalculatorPLStrategy")
public class BillCalculatorPLStrategy extends BillCalculatorStrategy {
	
	@Override
	public void calculateCosts(Person person, MonthPowerUsage powerUsage) {
		Suite suite = person.getSuite();
		super.setAbonament(suite.getAbonament());
		Double costPerKWh = powerUsage.getUsageKWh()*suite.getCostPerKWH()*getVatDecimalValue();
		setPowerUsageCost(costPerKWh);
	}
	
	private Double getVatDecimalValue(){
		return 100.0+super.getVatPercentage()/100;
	}
}
