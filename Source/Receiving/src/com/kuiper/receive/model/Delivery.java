package com.kuiper.receive.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Delivery")
public class Delivery {

	protected String Carrier_num;

	protected int delivery_num;

	protected int po_num;

	protected String trailer_num;

	protected int Truck_qty;

	protected String Vendor_name;

	Delivery() {

	}

	public String getCarrier_num() {
		return Carrier_num;
	}

	public int getDelivery_num() {
		return delivery_num;
	}

	public int getPo_num() {
		return po_num;
	}

	public String getTrailer_num() {
		return trailer_num;
	}

	public int getTruck_qty() {
		return Truck_qty;
	}

	public String getVendor_name() {
		return Vendor_name;
	}

	@XmlElement(name = "Carrier")
	public void setCarrier_num(String carrier_num) {
		Carrier_num = carrier_num;
	}
	
	@XmlElement(name = "Delivery")
	public void setDelivery_num(int delivery_num) {
		this.delivery_num = delivery_num;
	}
	
	@XmlElement(name = "PoNumber")
	public void setPo_num(int po_num) {
		this.po_num = po_num;
	}
	
	@XmlElement(name = "Trailer")
	public void setTrailer_num(String trailer_num) {
		this.trailer_num = trailer_num;
	}
	
	@XmlElement(name = "TruckQty")
	public void setTruck_qty(int truck_qty) {
		Truck_qty = truck_qty;
	}
	
	@XmlElement(name = "VendorName")
	public void setVendor_name(String vendor_name) {
		Vendor_name = vendor_name;
	}
}
