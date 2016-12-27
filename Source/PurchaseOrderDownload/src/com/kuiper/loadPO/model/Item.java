package com.kuiper.loadPO.model;

import java.sql.Timestamp;
import java.util.Date;


import org.springframework.data.mongodb.core.mapping.Document;


 
@Document(collection = "Item")
public class Item {
	
	protected long case_upc_nbr;
	protected float cost_amt;
	protected String item_desc;
	protected int _id;
	protected String item_status_code;
	protected ItemDimension itemDimension;
	protected int min_ord_qty;
	protected float sell_amt;
	protected String uom;
	protected long vendor_stock_id;
	
	private Timestamp last_modified_ts;
	private String last_modified_prg;
	private String last_modified_user;

	public Item() {
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

	public long getCase_upc_nbr() {
		return case_upc_nbr;
	}

	public float getCost_amt() {
		return cost_amt;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public int getItem_nbr() {
		return _id;
	}

	public String getItem_status_code() {
		return item_status_code;
	}

	public ItemDimension getItemDimension() {
		return itemDimension;
	}

	
	public void setItemDimension(ItemDimension itemDimension) {
		System.out.print("Set Item Dim");
		this.itemDimension = itemDimension;
	}

	public Date getLast_modified_ts() {
		return last_modified_ts;
	}

	public int getMin_ord_qty() {
		return min_ord_qty;
	}

	public float getSell_amt() {
		return sell_amt;
	}

	public String getUom() {
		return uom;
	}

	public long getVendor_stock_id() {
		return vendor_stock_id;
	}

	public void setCase_upc_nbr(long case_upc_nbr) {
		this.case_upc_nbr = case_upc_nbr;
	}

	public void setCost_amt(float cost_amt) {
		this.cost_amt = cost_amt;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public void setItem_nbr(int _item_nbr) {
		this._id = _item_nbr;
	}

	public void setItem_status_code(String item_status_code) {
		this.item_status_code = item_status_code;
	}

	public void setLast_modified_ts(Timestamp last_modified_ts) {
		this.last_modified_ts = last_modified_ts;
	}

	public void setMin_ord_qty(int min_ord_qty) {
		this.min_ord_qty = min_ord_qty;
	}

	public void setSell_amt(float sell_amt) {
		this.sell_amt = sell_amt;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	
	public void setVendor_stock_id(long vendor_stock_id) {
		this.vendor_stock_id = vendor_stock_id;
	}

}
