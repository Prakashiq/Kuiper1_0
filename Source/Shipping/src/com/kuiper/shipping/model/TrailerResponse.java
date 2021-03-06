package com.kuiper.shipping.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeliveryResponse")

public class TrailerResponse {
	
	private static final long serialVersionUID = 1L;
	

	private String status;

	private String statusDesc;

	public String getStatus() {
		return status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	@XmlElement(name="Status")
	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name="StatusDescription")
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
