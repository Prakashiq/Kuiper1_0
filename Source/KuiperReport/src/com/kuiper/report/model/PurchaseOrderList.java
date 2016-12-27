package com.kuiper.report.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class PurchaseOrderList {
	 List<PurchaseOrder> purchaseOrderlst = null;

	 public PurchaseOrderList()
	 {
		 
	 }

	public List<PurchaseOrder> getPurchaseOrderlst() {
		return purchaseOrderlst;
	}
	
	@XmlElementWrapper 
	@XmlAnyElement
	public void setPurchaseOrderlst(List<PurchaseOrder> purchaseOrderLst) {
		this.purchaseOrderlst = purchaseOrderLst;
	}
}
