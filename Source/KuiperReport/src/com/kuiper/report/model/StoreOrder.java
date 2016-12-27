package com.kuiper.report.model;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement
@Document(collection = "StoreOrder")
public class StoreOrder {

	protected int _id;

	protected int store_nbr;

	protected String store_name;

	protected String order_date;

	protected String ship_date;

	private String create_ts;

	private String last_modified_ts;

	private String last_modified_prg;

	private String last_modified_user;

	protected String order_status;

	private List<StoreOrderLine> storeOrderLinelst = null;
	
	private Trailer trailer;
	
	private Carrier carrier;

	public Carrier getCarrier() {
		return carrier;
	}

	@XmlElement
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	@XmlElement
	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public StoreOrder() {
	}

	public int getStore_nbr() {
		return store_nbr;
	}

	@XmlElement
	public void setStore_nbr(int store_nbr) {
		this.store_nbr = store_nbr;
	}

	public String getStore_name() {
		return store_name;
	}

	@XmlElement
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getOrder_date() {
		return order_date;
	}

	@XmlElement
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}


	@XmlElement
	public void set_id(int _id) {
		this._id = _id;
	}
	
	@XmlElement
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}


	public List<StoreOrderLine> getStoreOrderLinelst() {
		return storeOrderLinelst;
	}

	@XmlElementWrapper(name = "Orders")
	@XmlElement
	public void setStoreOrderLinelst(List<StoreOrderLine> storeOrderLinelst) {
		this.storeOrderLinelst = storeOrderLinelst;
	}

	public int get_id() {
		return _id;
	}

	public String getCreate_ts() {
		return create_ts;
	}

	public String getLast_modified_prg() {
		return last_modified_prg;
	}

	public String getLast_modified_ts() {
		return last_modified_ts;
	}

	public String getLast_modified_user() {
		return last_modified_user;
	}

	public String getShip_date() {
		return ship_date;
	}

	public void setCreate_ts(String create_ts) {
		this.create_ts = create_ts;
	}

	public void setLast_modified_prg(String last_modified_prg) {
		this.last_modified_prg = last_modified_prg;
	}

	public void setLast_modified_ts(String last_modified_ts) {
		this.last_modified_ts = last_modified_ts;
	}

	public void setLast_modified_user(String last_modified_user) {
		this.last_modified_user = last_modified_user;
	}

}
