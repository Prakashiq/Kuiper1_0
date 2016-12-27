package com.kuiper.loadPO.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.mongodb.core.mapping.Document;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Store", propOrder = { "_id",
		 "storeName",
		 "effective_date",
		 "expiration_date",
		 "grand_opening_date",
		 "address1",
		 "address2",
		 "city",
		 "state",
		 "postalCode",
		 "last_modified_ts",
		 "last_modified_prg",
	 "last_modified_user" })

@Document(collection = "Store")
public class Store {

	@XmlElement  
	protected int _id;
	@XmlElement
	protected String storeName;
	@XmlElement
	protected String effective_date;
	@XmlElement
	protected String expiration_date;
	@XmlElement
	protected String grand_opening_date;
	@XmlElement
	protected String address1;
	@XmlElement
	protected String address2;
	@XmlElement
	protected String city;
	@XmlElement
	protected String state;
	@XmlElement
	protected String postalCode;

	@XmlElement
	protected Timestamp last_modified_ts;
	@XmlElement
	protected String last_modified_prg;
	@XmlElement
	protected String last_modified_user;

	
	public int get_id() {
		return _id;
	}
	
	public void set_id(int _id) {
		this._id = _id;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getEffective_date() {
		return effective_date;
	}
	
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	
	public String getExpiration_date() {
		return expiration_date;
	}
	
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	
	public String getGrand_opening_date() {
		return grand_opening_date;
	}
	
	public void setGrand_opening_date(String grand_opening_date) {
		this.grand_opening_date = grand_opening_date;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Timestamp getLast_modified_ts() {
		return last_modified_ts;
	}
	public void setLast_modified_ts(Timestamp last_modified_ts) {
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
	
}
