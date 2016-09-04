package net.marvinlee.project.payslip;


import java.io.File;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for PaySlipGenerator.
 * TODO: Other testcases to be covered, invalid file content etc.
 * @author Marvin
 *
 */
public class PayslipGeneratorTest {

	static String baseDir = "C:/payslip/";
	static String inputFilePath = baseDir + "/input/input-data.csv";
	static String systemPropertiesPath = baseDir + "/config/payslip.properties";
	
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Fail test for capturing of null input in readInputFile
	 * @throws Exception
	 */
	@Test(expected=NullPointerException.class)
	public void failNoInputFileNullPointer() throws Exception {

		PayslipGenerator payslipGenerator = new PayslipGenerator(null, null);  
		payslipGenerator.readInputFile(); 
	}

	/**
	 * Success test for readInputFile
	 * @throws Exception
	 */
	@Test
	public void testReadInputFile() throws Exception {

		PayslipGenerator payslipGenerator = new PayslipGenerator(
				new File(inputFilePath), new File(systemPropertiesPath));  
		payslipGenerator.readInputFile(); 
	}
	
	/**
	 * Fail test for capturing of null file in writeOutputFile
	 * @throws Exception
	 */
	@Test(expected=NullPointerException.class)
	public void failNoOutputFileNullPointer() throws Exception {

		PayslipGenerator payslipGenerator = new PayslipGenerator(null, null);  
		payslipGenerator.writeOutputFile(); 
	}
	
	/**
	 * Fail test for capturing of null data list in writeOutputFile
	 * @throws Exception
	 */
	@Test(expected=NullPointerException.class)
	public void failNoDataOutputFileNullPointer() throws Exception {

		PayslipGenerator payslipGenerator = new PayslipGenerator(
				new File(inputFilePath), new File(systemPropertiesPath));   
		payslipGenerator.writeOutputFile(); 
	}


	/**
	 * Success test for writeOutputFile
	 * @throws Exception
	 */
	@Test
	public void testPayslipGeneratorSuccess() throws Exception {

		PayslipGenerator payslipGenerator = new PayslipGenerator(
				new File(inputFilePath), new File(systemPropertiesPath));  
		payslipGenerator.readInputFile();   
		payslipGenerator.writeOutputFile(); 
	}

}
