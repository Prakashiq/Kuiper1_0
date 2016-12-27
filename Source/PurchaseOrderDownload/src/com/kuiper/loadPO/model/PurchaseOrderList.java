package com.kuiper.loadPO.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name="PurchaseOrderList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrderList {
	
	//@XmlElementWrapper 
	@XmlElement (name="PurchaseOrder")
   List<PurchaseOrder> purchaseOrderList = new ArrayList<PurchaseOrder>();


	public PurchaseOrderList() {
	}


	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}


	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}


}
