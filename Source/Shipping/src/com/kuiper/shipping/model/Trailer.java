package com.kuiper.shipping.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Trailer")
public class Trailer {

	protected int storeOrderNum;
	
	protected int trailer_name;

	protected String trailer_num;

	protected int truck_qty;
	
	protected String carrier_id;

	Trailer() {

	}

	
	public String getCarrier_id() {
		return carrier_id;
	}


	@XmlElement(name = "CarrierId")
	public void setCarrier_id(String carrier_id) {
		this.carrier_id = carrier_id;
	}


	public int getStoreOrderNum() {
		return storeOrderNum;
	}

	@XmlElement(name = "StoreOrderNum")
	public void setStoreOrderNum(int storeOrderNum) {
		this.storeOrderNum = storeOrderNum;
	}


	public String getTrailer_num() {
		return trailer_num;
	}

	public int getTruck_qty() {
		return truck_qty;
	}

	public int getTrailer_name() {
		return trailer_name;
	}

	@XmlElement(name = "TrailerName")
	public void setTrailer_name(int trailer_name) {
		this.trailer_name = trailer_name;
	}

	@XmlElement(name = "TrailerId")
	public void setTrailer_num(String trailer_num) {
		this.trailer_num = trailer_num;
	}

	@XmlElement(name = "TruckQty")
	public void setTruck_qty(int truck_qty) {
		this.truck_qty = truck_qty;
	}

}
