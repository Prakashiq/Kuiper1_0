package com.kuiper.shipping.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Carrier")
public class Carrier {
	
	
	protected String _id  ;
	protected String carrierName ; 
	protected float carrcost ;
	protected String contactName; 
	protected String address1  ;
	protected String address2  ;
	protected String city       ;
	protected String state ;
	protected int zip  ;
    
	private Date create_ts;

	private Date last_modified_ts;

	private String last_modified_prg;

	private String last_modified_user;
	Carrier()
	{
		
	}
	

	public Date getCreate_ts() {
		return create_ts;
	}


	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}


	public Date getLast_modified_ts() {
		return last_modified_ts;
	}


	public void setLast_modified_ts(Date last_modified_ts) {
		this.last_modified_ts = last_modified_ts;
	}


	public String getLast_modified_prg() {
		return last_modified_prg;
	}


	public void setLast_modified_prg(String last_modified_prg) {
		this.last_modified_prg = last_modified_prg;
	}


	public String getLast_modified_user() {
		return last_modified_user;
	}


	public void setLast_modified_user(String last_modified_user) {
		this.last_modified_user = last_modified_user;
	}


	public String get_id() {
		return _id;
	}

	@XmlElement (name ="CarrierId")
	public void set_id(String _id) {
		this._id = _id;
	}

	public String getCarrierName() {
		return carrierName;
	}

	@XmlElement (name ="CarrierName")
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public float getCarrcost() {
		return carrcost;
	}

	@XmlElement (name ="Carrcost")
	public void setCarrcost(float carrcost) {
		this.carrcost = carrcost;
	}

	public String getContactName() {
		return contactName;
	}

	@XmlElement (name ="ContactName")
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress1() {
		return address1;
	}

	@XmlElement (name ="Address1")	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	@XmlElement (name ="Address2")	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	@XmlElement (name ="City")	
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@XmlElement  (name ="State")	
	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	@XmlElement   (name ="Zip")	
	public void setZip(int zip) {
		this.zip = zip;
	}
	
}
