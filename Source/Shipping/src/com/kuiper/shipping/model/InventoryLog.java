package com.kuiper.shipping.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement(name = "InventoryLog")
@Document(collection = "Inventory_Log")
public class InventoryLog {

	protected long _id;

	protected String action;

	protected int item_nbr;

	protected String last_modified_prg;

	protected Date last_modified_ts;

	protected String last_modified_user;

	protected int order_line_nbr;

	protected int order_nbr;

	protected int qty;
	
	protected String rcvtype;
	
	

	public String getRcvtype() {
		return rcvtype;
	}

	@XmlElement
	public void setRcvtype(String rcvtype) {
		this.rcvtype = rcvtype;
	}

	public String getAction() {
		return action;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}
	
	@XmlElement
	public void setAction(String action) {
		this.action = action;
	}

	public int getItem_nbr() {
		return item_nbr;
	}

	public String getLast_modified_prg() {
		return last_modified_prg;
	}

	public Date getLast_modified_ts() {
		return last_modified_ts;
	}

	public String getLast_modified_user() {
		return last_modified_user;
	}

	

	@XmlElement
	public void setItem_nbr(int item_nbr) {
		this.item_nbr = item_nbr;
	}
	
	@XmlElement
	public void setLast_modified_prg(String last_modified_prg) {
		this.last_modified_prg = last_modified_prg;
	}

	@XmlElement
	public void setLast_modified_ts(Date last_modified_ts) {
		this.last_modified_ts = last_modified_ts;
	}

	@XmlElement
	public void setLast_modified_user(String last_modified_user) {
		this.last_modified_user = last_modified_user;
	}

	public int getOrder_line_nbr() {
		return order_line_nbr;
	}
	
	@XmlElement
	public void setOrder_line_nbr(int order_line_nbr) {
		this.order_line_nbr = order_line_nbr;
	}

	public int getOrder_nbr() {
		return order_nbr;
	}

	@XmlElement
	public void setOrder_nbr(int order_nbr) {
		this.order_nbr = order_nbr;
	}

	public int getQty() {
		return qty;
	}

	@XmlElement
	public void setQty(int qty) {
		this.qty = qty;
	}

	
}
