package com.kuiper.common.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PoSummaryCnt")
public class PoSummaryCnt {

	String po_status;
	long total;
	
	public String getPo_status() {
		return po_status;
	}
	
	@XmlElement(name="po_status")
	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}
	
	public long getTotal() {
		return total;
	}
	
	@XmlElement(name="total")
	public void setTotal(long total) {
		this.total = total;
	}

	
}
