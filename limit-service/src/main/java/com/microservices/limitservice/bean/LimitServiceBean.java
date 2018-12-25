package com.microservices.limitservice.bean;

public class LimitServiceBean {

	private int minimum;
	private int maximum;
	public LimitServiceBean() {
		super();
	}
	public LimitServiceBean(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	@Override
	public String toString() {
		return "LimitServiceBean [minimum=" + minimum + ", maximum=" + maximum + "]";
	}
	
	
}
