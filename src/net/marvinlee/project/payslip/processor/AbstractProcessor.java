package net.marvinlee.project.payslip.processor;

import java.util.List;
import java.util.Properties;

import net.marvinlee.project.payslip.model.Employee;

public abstract class AbstractProcessor {

	public void processList(Properties systemProperties, List<Employee> employees) throws Exception{
		preProcessList(systemProperties, employees);
		for(Employee employee: employees){
			preProcess(employee);
			process(employee);
			postProcess(employee);
		}
		postProcessList(systemProperties, employees);
	}

	protected void preProcessList(Properties systemProperties, List<Employee> employees) throws Exception { 
		// Do nothing, for override implementation
	}

	protected void preProcess(Employee employee)  { 
		// Do nothing, for override implementation
	}
	
	public abstract void process(Employee employee);
	
	protected void postProcess(Employee employee) { 
		// Do nothing, for override implementation
	}
	
	protected void postProcessList(Properties systemProperties, List<Employee> employees) {
		// Do nothing, for override implementation
	}

}
