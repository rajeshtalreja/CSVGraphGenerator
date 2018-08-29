package com.csvgenerator.csvprocessor;

public class CSVRecord implements Comparable<CSVRecord>{
	
	private TradeTime time;
	private String ticker;
	private double price;
	private int size;
	public TradeTime getTime() {
		return time;
	}
	public void setTime(TradeTime time) {
		this.time = time;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public int compareTo(CSVRecord o) {
		
		if(this.time.getHr() < o.getTime().getHr()){
			return -1;
		}
		if(this.time.getHr() > o.getTime().getHr()){
			return 1;
		}
		if(this.time.getMin() < o.getTime().getMin()){
			return -1;
		}
		if(this.time.getMin() > o.getTime().getMin()){
			return 1;
		}
		
		if(this.time.getSec() < o.getTime().getSec()){
			return -1;
		}
		if(this.time.getSec() > o.getTime().getSec()){
			return 1;
		}
		
		return 0;
	}

}
