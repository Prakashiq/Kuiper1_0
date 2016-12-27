package com.kuiper.shipping.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UpdateDelivery")
public class UpdateTrailer {

	protected int order_num;
	protected String order_status;
	public int getOrder_num() {
		return order_num;
	}
	@XmlElement(name = "OrderNumber")
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getOrder_status() {
		return order_status;
	}
	@XmlElement(name = "OrderStatus")
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	

	
	
	
	
}
