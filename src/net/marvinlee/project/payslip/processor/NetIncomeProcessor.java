package net.marvinlee.project.payslip.processor;
 

import net.marvinlee.project.payslip.model.Employee;

public class NetIncomeProcessor extends AbstractProcessor{

	@Override
	public void process(Employee employee) {

		employee.setNetIncome(employee.getGrossIncome().subtract(employee.getIncomeTax()));
		
	}

}
