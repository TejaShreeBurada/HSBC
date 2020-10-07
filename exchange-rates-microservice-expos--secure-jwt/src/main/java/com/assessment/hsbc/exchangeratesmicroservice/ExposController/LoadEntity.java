package com.assessment.hsbc.exchangeratesmicroservice.ExposController;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class LoadEntity	implements Serializable {

	private static final long serialVersionUID = 5952745511727988253L;

	private Long rateId;

	private String base;
	private Double GBP;
	private Double USD;
	private Double HKD;
	private java.util.Date date;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Double getGBP() {
		return GBP;
	}

	public void setGBP(Double gBP) {
		GBP = gBP;
	}

	public Double getUSD() {
		return USD;
	}

	public void setUSD(Double uSD) {
		USD = uSD;
	}

	public Double getHKD() {
		return HKD;
	}

	public void setHKD(Double hKD) {
		HKD = hKD;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "LoadEntity [base=" + base + ", GBP=" + GBP + ", USD=" + USD + ", HKD=" + HKD + ", date=" + date + "]";
	}

}
