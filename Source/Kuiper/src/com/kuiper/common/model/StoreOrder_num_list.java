package com.kuiper.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "StoreOrderLst")
public class StoreOrder_num_list {

	private List<Integer> colst = null;

	public List<Integer> getColst() {
		return colst;
	}
	
	@XmlElement(name = "storeOrderNumber")
	public void setColst(List<Integer> colst) {
		this.colst = colst;
	}

}
