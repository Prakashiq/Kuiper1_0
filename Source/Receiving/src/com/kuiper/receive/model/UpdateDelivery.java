package com.kuiper.receive.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UpdateDelivery")
public class UpdateDelivery {

	protected int delivery_num;
	protected String po_status;
	
	public int getDelivery_num() {
		return delivery_num;
	}
	
	@XmlElement(name = "Delivery")
	public void setDelivery_num(int delivery_num) {
		this.delivery_num = delivery_num;
	}
	
	public String getPo_status() {
		return po_status;
	}
	
	@XmlElement(name = "PoStatus")
	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}
	
}
