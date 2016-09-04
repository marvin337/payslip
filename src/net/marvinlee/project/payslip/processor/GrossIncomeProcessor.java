package net.marvinlee.project.payslip.processor;
 

import java.math.BigDecimal;

import net.marvinlee.project.payslip.model.Employee;

public class GrossIncomeProcessor extends AbstractProcessor{
 

	@Override
	public void process(Employee employee) { 
		
		employee.setGrossIncome(employee.getAnnualSalary().divide(
				new BigDecimal(12),0, BigDecimal.ROUND_HALF_EVEN));
		
	}

}
