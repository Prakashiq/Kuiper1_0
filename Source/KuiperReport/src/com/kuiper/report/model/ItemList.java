package com.kuiper.report.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="ItemList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemList {
	
	@XmlElementWrapper(name = "ItemList")
	@XmlElement(name = "Item")
	private List<Item> itemList = null;

	public List<Item> getItemlst() {
		return itemList;
	}

	public ItemList() {

	}


	public void setItemlst(List<Item> itemList) {
		System.out.print("Set itemList");
		this.itemList = itemList;
	}
}
