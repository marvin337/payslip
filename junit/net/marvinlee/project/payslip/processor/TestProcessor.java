package net.marvinlee.project.payslip.processor;

import java.util.List;
import java.util.Properties;

import net.marvinlee.project.payslip.model.Employee;

public class TestProcessor extends AbstractProcessor{


	@Override
	protected void preProcessList(Properties systemProperties, List<Employee> employees) throws Exception {
		super.preProcessList(systemProperties, employees);
		systemProperties.put("preProcessList","tested");
	}

	@Override
	public void processList(Properties systemProperties, List<Employee> employees) throws Exception {
		super.processList(systemProperties, employees);
		systemProperties.put("processList","tested");
	}
	
	@Override
	protected void preProcess(Employee employee) {
		super.preProcess(employee);
		employee.setPayPeriod("Passed preProcess");
	}
	
	@Override
	public void process(Employee employee) {
		employee.setPayPeriod(employee.getPayPeriod() + ",Passed process");
	}

	@Override
	protected void postProcess(Employee employee) {
		employee.setPayPeriod(employee.getPayPeriod() + ",Passed postProcess");
		super.postProcess(employee);
	}

	@Override
	protected void postProcessList(Properties systemProperties, List<Employee> employees) {
		super.postProcessList(systemProperties, employees);
		systemProperties.put("postProcessList","tested");
	}

}
