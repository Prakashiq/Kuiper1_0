package com.kuiper.loadItem.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="ItemList")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemList {
	
	//@XmlElementWrapper 
	@XmlElement (name="Item")
   List<Item> itemList = new ArrayList<Item>();


	public ItemList() {
	}

	public List<Item> getItemlst() {
		return itemList;
	}

	public void setItemlst(List<Item> itemList) {
		this.itemList = itemList;
	}
}
