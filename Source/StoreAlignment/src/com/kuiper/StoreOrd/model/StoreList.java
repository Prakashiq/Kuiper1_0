package com.kuiper.StoreOrd.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="StoreList")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoreList {
	
	//@XmlElementWrapper 
	@XmlElement (name="Store")
   List<Store> storeList = new ArrayList<Store>();

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}


}
