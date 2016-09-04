package net.marvinlee.project.payslip;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import net.marvinlee.project.payslip.processor.GrossIncomeProcessorTest;
import net.marvinlee.project.payslip.processor.PayslipProcessorTest;

public class JUnitRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(PayslipGeneratorTest.class, 
				PayslipProcessorTest.class, GrossIncomeProcessorTest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println("Failure at:" + failure.toString());
		}

		System.out.println("JUnit tests run " + result.getRunCount() + " tests."); 
		System.out.println("Result success:" + result.wasSuccessful());

	}

}
