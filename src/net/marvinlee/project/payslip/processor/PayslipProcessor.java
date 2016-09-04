package net.marvinlee.project.payslip.processor;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import net.marvinlee.project.payslip.model.Employee;

public class PayslipProcessor {
	
	private static PayslipProcessor payslipProcessor = null;
	private List<AbstractProcessor> processors = null;
	
	/**
	 * Initializes the processors. Ideally this can be configured in a configuration file and
	 * loads the class dynamically.
	 */
	public PayslipProcessor() { 
		this.processors = new LinkedList<AbstractProcessor>();
		this.processors.add(new GrossIncomeProcessor());
		this.processors.add(new IncomeTaxProcessor());
		this.processors.add(new NetIncomeProcessor());
		this.processors.add(new SuperannuationProcessor());
	}
	
	/**
	 * NOTE : This constructor method is used for tests only
	 * @param processors
	 */
	protected PayslipProcessor(List<AbstractProcessor> processors) { 
		getInstance().processors = processors;
	}


	public synchronized static PayslipProcessor getInstance() {
		if (payslipProcessor == null){
			payslipProcessor = new PayslipProcessor();
		}
		return payslipProcessor;
	}
	
	public static int process(Properties systemProperties, List<Employee> employees) throws Exception{
		if (employees != null && !employees.isEmpty()){
			for(AbstractProcessor processor : getInstance().processors){
				processor.processList(systemProperties, employees);
			}
			return employees.size();
		}
		else
		{
			return 0; 
		}
	}



}
