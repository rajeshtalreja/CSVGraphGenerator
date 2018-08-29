package com.csvgenerator.csvprocessor;

public class TradeTime {

	private int hr;
	private int min;
	private double sec;
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public double getSec() {
		return sec;
	}
	public void setSec(double sec) {
		this.sec = sec;
	}
	/**
	 * 
	 * @param time
	 */
	public void createTradeTime(String time){
		String[] split = time.split(":");
		this.hr = Integer.parseInt(split[0]);
		this.min = Integer.parseInt(split[1]);
		this.sec = Double.parseDouble(split[2]);
	}
	
	
}
