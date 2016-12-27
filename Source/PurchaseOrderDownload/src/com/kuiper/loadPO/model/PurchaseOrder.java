package com.kuiper.loadPO.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.mongodb.core.mapping.Document;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchaseOrder", propOrder = { "_id", "vendor_nbr", "buyer_name", "po_order_date", "ship_date",
		"must_arrive_date", "create_ts", "last_modified_ts", "last_modified_user", "last_modified_prg", "po_status",
		"store_number", "purchaseOrderLinelst" })

@Document(collection = "PurchaseOrder")
public class PurchaseOrder {

	@XmlElement(name = "PurchaseOrderNbr")
	protected int _id;

	@XmlElement(name = "Vendor_nbr")
	protected int vendor_nbr;

	@XmlElement(name = "BuyerName")
	protected String buyer_name;

	@XmlElement(name = "PurchaseOrderDate")
	protected String po_order_date;

	@XmlElement(name = "ShipDate")
	protected String ship_date;

	@XmlElement(name = "MustArriveDate")
	protected String must_arrive_date;
	
	@XmlElement(name = "StoreNum")
	protected String store_number;

	@XmlElement
	protected Date create_ts;
	
	@XmlElement
	protected Date last_modified_ts;
	
	@XmlElement
	protected String last_modified_prg;
	
	@XmlElement
	protected String last_modified_user;
	
	@XmlElement
	protected String po_status;
	


	@XmlElementWrapper (name="PO_Lines") 
	@XmlElement (name="PO_Line") 
     List<PurchaseOrderLine> purchaseOrderLinelst =new ArrayList<PurchaseOrderLine>();

	
	public PurchaseOrder() {
	}

	
	public String getStore_number() {
		return store_number;
	}


	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}


	public int get_id() {
		return _id;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public Date getCreate_ts() {
		return create_ts;
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

	public String getMust_arrive_date() {
		return must_arrive_date;
	}

	public String getPo_order_date() {
		return po_order_date;
	}

	public String getPo_status() {
		return po_status;
	}

	public String getShip_date() {
		return ship_date;
	}

	public int getVendor_nbr() {
		return vendor_nbr;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}

	public void setLast_modified_prg(String last_modified_prg) {
		this.last_modified_prg = last_modified_prg;
	}

	public void setLast_modified_ts(Date last_modified_ts) {
		this.last_modified_ts = last_modified_ts;
	}

	public void setLast_modified_user(String last_modified_user) {
		this.last_modified_user = last_modified_user;
	}

	public void setMust_arrive_date(String must_arrive_date) {
		System.out.print("MustArrive Date" + must_arrive_date);
		this.must_arrive_date = must_arrive_date;
	}

	public void setPo_order_date(String po_order_date) {
		this.po_order_date = po_order_date;
	}

	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}

	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}

	public void setVendor_nbr(int vendor_nbr) {
		this.vendor_nbr = vendor_nbr;
	}

	
	public List<PurchaseOrderLine> getPurchaseOrderLinelst() {
		return purchaseOrderLinelst;
	}

	public void setPurchaseOrderLinelst(List<PurchaseOrderLine> purchaseOrderLinelst) {
		this.purchaseOrderLinelst = purchaseOrderLinelst;
	}

}
