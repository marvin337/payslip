package net.marvinlee.project.payslip.processor;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import net.marvinlee.project.payslip.model.Employee;

public class PayslipProcessorTest {
	
	String[][] dataArray = new String[][]{{"First One","Last One","50000","8","01 January - 31 January"},
			{"First Two","Last Two","100000","10","01 January - 31 January"}};


			
	/**
	 * Test processing empty data
	 * @throws Exception
	 */
	@Test
	public void testZeroData() throws Exception { 
		assertEquals(0, PayslipProcessor.process(null, null));
	}
	
	/**
	 * Test processing two records with a dummy processor
	 * @throws Exception
	 */
	@Test
	public void testHasTwoRecord() throws Exception { 

		List<Employee> dataList = new LinkedList<>();
		Employee employee = new Employee(dataArray[0]);
		dataList.add(employee);

		employee = new Employee(dataArray[1]);
		dataList.add(employee);
		
		// Initialize PayslipProcessor with a test Processor
		List<AbstractProcessor> processorList = new LinkedList<>();
		processorList.add(new DummyProcessor());
		
		PayslipProcessor payslipProcessor = new PayslipProcessor(processorList);
		assertEquals(2, payslipProcessor.getInstance().process(null, dataList));
	}

	/**
	 * Test processor been through all steps of processing
	 * @throws Exception
	 */
	@Test
	public void testAllProcessingSteps() throws Exception { 

		List<Employee> dataList = new LinkedList<>();
		Employee employee = new Employee(dataArray[0]);
		dataList.add(employee); 
		
		// Initialize properties
		Properties testProperties = new Properties();
		
		// Initialize PayslipProcessor with a test Processor
		List<AbstractProcessor> processorList = new LinkedList<>();
		processorList.add(new TestProcessor());
		
		PayslipProcessor payslipProcessor = new PayslipProcessor(processorList);
		assertEquals(1, payslipProcessor.getInstance().process(testProperties, dataList));
		assertEquals("tested", testProperties.getProperty("preProcessList"));
		assertEquals("tested", testProperties.getProperty("processList"));
		assertEquals("tested", testProperties.getProperty("postProcessList"));
		assertEquals("Passed preProcess,Passed process,Passed postProcess", employee.getPayPeriod());
	}

}
