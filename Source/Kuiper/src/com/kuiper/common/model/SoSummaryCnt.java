package com.kuiper.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SummaryCnt")
public class SoSummaryCnt {

	String order_status;
	long total;
	
	
	
	public String getOrder_status() {
		return order_status;
	}

	@XmlElement(name="order_status")
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public long getTotal() {
		return total;
	}
	
	@XmlElement(name="total")
	public void setTotal(long total) {
		this.total = total;
	}

	
}
