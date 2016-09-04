package net.marvinlee.project.payslip.processor;
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.marvinlee.project.payslip.model.Employee;
import net.marvinlee.project.payslip.model.IncomeTaxTier;

public class IncomeTaxProcessor extends AbstractProcessor{

	private LinkedList<IncomeTaxTier> tierList = null;

	@Override
	protected void preProcessList(Properties systemProperties, List<Employee> employees) throws Exception {
		super.preProcessList(systemProperties, employees);
		
		System.out.println("Getting income tax calculation formula");
		// Get the tax table
		String taxFormulaTableFilePath = systemProperties.getProperty("incometax.config.file");
		if (taxFormulaTableFilePath == null){
			throw new Exception("Unable to get income tax calculation formula file.");
		}
		File taxFormulaTableFile = new File(taxFormulaTableFilePath);
		if (!taxFormulaTableFile.exists()){
			throw new Exception("Income tax calculation formula file not found.");
		}
		
		parseTaxFormulaTable(systemProperties, taxFormulaTableFile);
		
	}

	private void parseTaxFormulaTable(Properties systemProperties, File taxFormulaTableFile) throws Exception {
		String line = null;
		int lineCount = 1;
		String dataPattern = systemProperties.getProperty("taxfile.regex.pattern"); 
		Pattern pattern = Pattern.compile(dataPattern);
		tierList = new LinkedList<IncomeTaxTier>();
		try (BufferedReader br = new BufferedReader(new FileReader(taxFormulaTableFile))){
			while((line=br.readLine())!=null){ 
				String[] columns = line.split("\t");
				if (columns.length != 4)
					throw new Exception("Line number " + lineCount 
							+ " does not match the required 4 columns with : " + line); 
				Matcher matcher = pattern.matcher(line);
				if (!matcher.find())
					throw new Exception("Line number " + lineCount 
							+ " does not match the data type with : " + line); 
				IncomeTaxTier incomeTaxTier = new IncomeTaxTier(columns);
				tierList.add(incomeTaxTier);
				lineCount++;
			}
		}
	}

	@Override
	public void process(Employee employee) {
		BigDecimal annualSalary = employee.getAnnualSalary();
		BigDecimal totalTax = new BigDecimal(0);
		for (IncomeTaxTier tier : tierList){
			if ((annualSalary.compareTo(tier.getTierStart()) >=0) && 
					(annualSalary.compareTo(tier.getTierCeiling()) <=0)){
				
				System.out.println(" Annual salary:" + annualSalary + " match tier:" + tier);
				// Single tier tax
				totalTax = totalTax.add(tier.getTierTax());
				System.out.println("\t\tTier Tax:" + tier.getTierTax());
				
				// Tier tax per dollars in cents
				BigDecimal tierDollars = annualSalary.subtract(tier.getTierStart().subtract(new BigDecimal(1)));
				System.out.println("\t\tTierDollars:" + tierDollars);
				BigDecimal taxPerDollar = tier.getTaxPerDollar().divide(new BigDecimal(100));
				System.out.println("\t\tTaxPerDollar:" + taxPerDollar);
				BigDecimal taxOnRemainingDollars = tierDollars.multiply(taxPerDollar);
				System.out.println("\t\tTaxOnRemainingDollars:" + taxOnRemainingDollars);
				totalTax = totalTax.add(taxOnRemainingDollars);
				totalTax = totalTax.divide(new BigDecimal(12), 0, BigDecimal.ROUND_HALF_EVEN);
				System.out.println("Total tax:" + totalTax);
			}
		}
		employee.setIncomeTax(totalTax);
	}

}
