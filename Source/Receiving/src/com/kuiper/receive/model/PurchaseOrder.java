package com.kuiper.receive.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement(name = "PurchaseOrder")
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "PurchaseOrder", propOrder = { "PurchaseOrderNbr", "Vendor_nbr", "BuyerName", "PurchaseOrderDate", 
//		"ShipDate","MustArriveDate", "PO_Line_Lst" })
@Document(collection = "PurchaseOrder")
public class PurchaseOrder {

	// @XmlElement(name="PurchaseOrderNbr")
	protected int _id;

	// @XmlElement(name="Vendor_nbr")
	protected int vendor_nbr;

	// @XmlElement(name="BuyerName")
	protected String buyer_name;

	// @XmlElement(name="PurchaseOrderDate")
	protected String po_order_date;

	// @XmlElement(name="ShipDate")
	protected String ship_date;

	// @XmlElement(name="MustArriveDate")
	protected String must_arrive_date;
	
	protected String store_number;

	private Date create_ts;

	private Date last_modified_ts;

	private String last_modified_prg;

	private String last_modified_user;

	protected String po_status;
	

	private List<PurchaseOrderLine> purchaseOrderLinelst = null;

	private Delivery delivery;

	public PurchaseOrder() {
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

	@XmlElement(name = "PurchaseOrderNbr")
	public void set_id(int _id) {
		this._id = _id;
	}

	@XmlElement(name = "BuyerName")
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

	@XmlElement(name = "MustArriveDate")
	public void setMust_arrive_date(String must_arrive_date) {
		System.out.print("MustArrive Date" + must_arrive_date);
		this.must_arrive_date = must_arrive_date;
	}

	@XmlElement(name = "PurchaseOrderDate")
	public void setPo_order_date(String po_order_date) {
		this.po_order_date = po_order_date;
	}

	public void setPo_status(String po_status) {
		this.po_status = po_status;
	}

	@XmlElement(name = "ShipDate")
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}

	@XmlElement(name = "Vendor_nbr")
	public void setVendor_nbr(int vendor_nbr) {
		this.vendor_nbr = vendor_nbr;
	}

//	public List<PurchaseOrderLine> getPurchaseOrderLines() {
//		return purchaseOrderLinelst;
//	}
//
//
//	public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLinelst) {
//		this.purchaseOrderLinelst = purchaseOrderLinelst;
//	}
	
	

	public String getStore_number() {
		return store_number;
	}

	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}

	public List<PurchaseOrderLine> getPurchaseOrderLinelst() {
		return purchaseOrderLinelst;
	}

	@XmlElementWrapper(name = "PO_Line_Lst")
	@XmlElement(name = "PO_Line")
	public void setPurchaseOrderLinelst(List<PurchaseOrderLine> purchaseOrderLinelst) {
		this.purchaseOrderLinelst = purchaseOrderLinelst;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
}
