package com.kuiper.StoreOrd.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "StoreOrder_ex")
public class StoreOrder_ex {
	
	String Error_desc;
	StoreOrder storeOrder ;
	
	public String getError_desc() {
		return Error_desc;
	}
	public void setError_desc(String error_desc) {
		Error_desc = error_desc;
	}
	public StoreOrder getStoreOrder() {
		return storeOrder;
	}
	public void setStoreOrder(StoreOrder storeOrder) {
		this.storeOrder = storeOrder;
	}

}
