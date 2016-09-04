package net.marvinlee.project.payslip.model;

import java.math.BigDecimal;

public class IncomeTaxTier {
	
	private BigDecimal tierStart;
	private BigDecimal tierCeiling;
	private BigDecimal tierTax;
	private BigDecimal taxPerDollar;
	
	public IncomeTaxTier(String[] columns) {
		tierStart = new BigDecimal(columns[0]);
		tierCeiling = new BigDecimal(columns[1]);
		tierTax =  new BigDecimal(columns[2]);
		taxPerDollar =  new BigDecimal(columns[3]);
	}
	
	public BigDecimal getTierStart() {
		return tierStart;
	}
	public void setTierStart(BigDecimal tierStart) {
		this.tierStart = tierStart;
	}
	public BigDecimal getTierCeiling() {
		return tierCeiling;
	}
	public void setTierCeiling(BigDecimal tierCeiling) {
		this.tierCeiling = tierCeiling;
	}
	public BigDecimal getTierTax() {
		return tierTax;
	}
	public void setTierTax(BigDecimal tierTax) {
		this.tierTax = tierTax;
	}
	public BigDecimal getTaxPerDollar() {
		return taxPerDollar;
	}
	public void setTaxPerDollar(BigDecimal taxPerDollar) {
		this.taxPerDollar = taxPerDollar;
	}

	@Override
	public String toString() {
		return "IncomeTaxTier [tierStart=" + tierStart + ", tierCeiling=" + tierCeiling + ", tierTax=" + tierTax
				+ ", taxPerDollar=" + taxPerDollar + "]";
	}
	
	

}
