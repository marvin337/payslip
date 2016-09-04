package net.marvinlee.project.payslip.processor;
 

import java.math.BigDecimal;

import net.marvinlee.project.payslip.model.Employee;

public class SuperannuationProcessor extends AbstractProcessor{

	@Override
	public void process(Employee employee) {
		BigDecimal superPercentage = new BigDecimal(employee.getSuperPercentage())
				.divide(new BigDecimal(100));
		
		employee.setSuperannuation(employee.getGrossIncome().multiply(superPercentage)
				.setScale(0, BigDecimal.ROUND_HALF_EVEN));
	}

}
