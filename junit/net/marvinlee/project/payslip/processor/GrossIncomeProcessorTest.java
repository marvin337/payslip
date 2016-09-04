package net.marvinlee.project.payslip.processor;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import net.marvinlee.project.payslip.model.Employee;

/**
 * An example to test processor classes under net.marvinlee.project.payslip.processor package.
 * For this instance of test, it is against GrossIncomeProcessor to calculate 
 * gross income from an employee's annual salary.
 * 
 * @author Marvin
 *
 */
public class GrossIncomeProcessorTest extends GrossIncomeProcessor {
	
	/**
	 * Test processing gross income
	 * @throws Exception
	 */
	@Test
	public void testGrossIncome() throws Exception { 
		Employee employee = new Employee();
		employee.setAnnualSalary(new BigDecimal(120000));
		process(employee);
		assertEquals(new BigDecimal(10000), employee.getGrossIncome());
	}
}
