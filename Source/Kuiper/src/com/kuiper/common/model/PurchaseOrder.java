package com.kuiper.common.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "PurchaseOrder")
public class PurchaseOrder {

	protected int _id;

	protected int vendor_nbr;

	protected String buyer_name;


	protected String po_order_date;

	protected String ship_date;

	protected String must_arrive_date;

	private Date create_ts;

	private Date last_modified_ts;

	private String last_modified_prg;

	private String last_modified_user;
	
	protected String po_status;

	private List<PurchaseOrderLine> purchaseOrderLinelst = null;

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

	public List<PurchaseOrderLine> getPurchaseOrderLines() {
		return purchaseOrderLinelst;
	}
	
	public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLinelst) {
		this.purchaseOrderLinelst  = purchaseOrderLinelst;
	}

}
