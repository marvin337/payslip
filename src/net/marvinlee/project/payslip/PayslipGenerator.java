package net.marvinlee.project.payslip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.marvinlee.project.payslip.model.Employee;
import net.marvinlee.project.payslip.processor.PayslipProcessor;

public class PayslipGenerator {

	
	private File inputFile = null;
	private File configFile = null;
	Properties systemProperties = new Properties();
	private LinkedList<Employee> dataList = null;

	public PayslipGenerator(File inputFile, File configFile) { 
		this.inputFile = inputFile;
		this.configFile = configFile;
	}

	public static void main(String[] args){
		String homeDir = "C://payslip/"; 
		if (args == null || args.length != 1)
		{
			System.out.println("Home directory not provided, using default " + homeDir); 
		} else {
			homeDir = args[0];	
		}  
		System.out.println("Using home directory:" + homeDir);
		
		
		File inputFile = new File(homeDir + "/input/input-data.csv");
		checkFileExist("Input file ", inputFile);
		File configFile = new File(homeDir + "/config/payslip.properties");
		checkFileExist("System configuration file ", configFile);

		PayslipGenerator payslipGenerator = new PayslipGenerator(inputFile, configFile);
		try {
			payslipGenerator.readInputFile();
		} catch (Exception e) { 
			System.err.println("Error reading input file, please correct data.\n Cause: " + e.getMessage()); 
			return;
		}
		
		try {
			payslipGenerator.generateData();
		} catch (Exception e) {
			System.err.println("Error generating data.\n Cause: " + e.getMessage());
			e.printStackTrace();
			return;
		}

	}

	private static boolean checkFileExist(String fileType, File file){ 
		if (!file.exists())
		{
			System.err.println(fileType + "'" + file + "' not found!");
			return false;
		}
		return true;
	}

	protected void readInputFile() throws IOException, Exception {

		// Read system properties file
		try (FileInputStream input = new FileInputStream(configFile)){ 
			systemProperties.load(input);
		}
		
		// Validate input CSV file and parameters, all records have to be formatted correctly to continue
		String line=null;
		int lineCount = 1;
		dataList = new LinkedList<Employee>();
		String dataPattern = systemProperties.getProperty("inputfile.regex.pattern"); 
		Pattern pattern = Pattern.compile(dataPattern);
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))){
			while((line=br.readLine())!=null){ 
				String[] columns = line.split(",");
				if (columns.length != 5)
					throw new Exception("Line number " + lineCount 
							+ " does not match the required 5 columns width : " + line); 
				Matcher matcher = pattern.matcher(line);
				if (!matcher.find())
					throw new Exception("Line number " + lineCount 
							+ " does not match the data type with : " + line); 
				Employee employee = new Employee(columns);
				dataList.add(employee);
				lineCount++;
			}
		}
		
	}


	private void generateData() throws Exception {
		// Runs processing with list of data, for each processor, runs calculation
		PayslipProcessor.process(systemProperties,dataList);

		// Output processed data
		writeOutputFile();
	}

	protected void writeOutputFile() throws IOException {

		// Ideally should be an input parameter to determine the output file
		File file = new File(inputFile.getAbsolutePath() + ".out");  
		file.createNewFile(); 

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		try(BufferedWriter bw = new BufferedWriter(fw)){
			for(Employee employee : dataList){
				bw.write(employee+"\n");
			} 
		}

	}

}
