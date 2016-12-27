package com.kuiper.shipping.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="StoreOrderList")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoreOrderList {
	
	//@XmlElementWrapper 
	@XmlElement (name="StoreOrder")
   List<StoreOrder> storeOrderList = new ArrayList<StoreOrder>();


	public StoreOrderList() {
	}


	public List<StoreOrder> getStoreOrderList() {
		return storeOrderList;
	}


	public void setStoreOrderList(List<StoreOrder> storeOrderList) {
		this.storeOrderList = storeOrderList;
	}

}
