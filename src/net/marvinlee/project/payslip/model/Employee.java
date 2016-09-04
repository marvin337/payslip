package net.marvinlee.project.payslip.model;

import java.math.BigDecimal;

public class Employee {

	
	//Input fields
	private String firstName;
	private String lastName;
	private BigDecimal annualSalary;
	private int superPercentage;
	private String payPeriod;
	
	
	//Output fields
	private String fullName;
	private BigDecimal grossIncome;
	private BigDecimal incomeTax;
	private BigDecimal netIncome;
	private BigDecimal superannuation;

	public Employee(String[] columns) {
		firstName = columns[0];
		lastName = columns[1];
		annualSalary = new BigDecimal(columns[2]);
		superPercentage = Integer.parseInt(columns[3]);
		payPeriod = columns[4]; 
	}
	
	public Employee() {
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}
	public int getSuperPercentage() {
		return superPercentage;
	}
	public void setSuperPercentage(int superPercentage) {
		this.superPercentage = superPercentage;
	}
	public String getPayPeriod() {
		return payPeriod;
	}
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public BigDecimal getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
	}
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}
	public BigDecimal getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}
	public BigDecimal getSuperannuation() {
		return superannuation;
	}
	public void setSuperannuation(BigDecimal superannuation) {
		this.superannuation = superannuation;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + "," + annualSalary
				+ "," + payPeriod + "," + grossIncome + "," + incomeTax
				+ "," + netIncome + "," + superannuation;
	}
	
	
}
