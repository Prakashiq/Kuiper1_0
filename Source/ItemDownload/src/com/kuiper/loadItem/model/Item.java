package com.kuiper.loadItem.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.data.mongodb.core.mapping.Document;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {"case_upc_nbr", "cost_amt", "item_desc", 
		"_id", "item_status_code", "min_ord_qty", "sell_amt", "uom", "vendor_stock_id",
		"itemDimension", "last_modified_ts", "last_modified_prg", "last_modified_user"})

@Document(collection = "Item")
public class Item {


	@XmlElement (name="CaseUpcNbr")
	protected long case_upc_nbr;

	@XmlElement (name="CostAmount")
	protected float cost_amt;

    @XmlElement  (name="ItemDescription")
	protected String item_desc;

	@XmlElement  (name="ItemNumber")
	protected int _id;

	@XmlElement  (name="ItemStatusCode")
	protected String item_status_code;
	
	@XmlElement (name="MinOrdQty")
	protected int min_ord_qty;

	@XmlElement (name="SellAmt")
	protected float sell_amt;

	@XmlElement (name="UnitOfMeasure")
	protected String uom;

	@XmlElement (name="VendorStockId")
	protected long vendor_stock_id;
	

	@XmlElement 
	protected ItemDimension itemDimension;
	
	@XmlElement 
	protected Timestamp last_modified_ts;
	@XmlElement 
	protected String last_modified_prg;
	@XmlElement 
	protected String last_modified_user;
	
	
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

	public ItemDimension getItemDimension() {
		return itemDimension;
	}

	public void setItemDimension(ItemDimension itemDimension) {
		this.itemDimension = itemDimension;
	}


	
	public int getMin_ord_qty() {
		return min_ord_qty;
	}

	public void setMin_ord_qty(int min_ord_qty) {
		this.min_ord_qty = min_ord_qty;
	}

	public float getSell_amt() {
		return sell_amt;
	}

	public void setSell_amt(float sell_amt) {
		this.sell_amt = sell_amt;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public long getVendor_stock_id() {
		return vendor_stock_id;
	}

	public void setVendor_stock_id(long vendor_stock_id) {
		this.vendor_stock_id = vendor_stock_id;
	}

	public float getCost_amt() {
		return cost_amt;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getItem_status_code() {
		return item_status_code;
	}

	public void setItem_status_code(String item_status_code) {
		this.item_status_code = item_status_code;
	}

	public void setCost_amt(float cost_amt) {
		this.cost_amt = cost_amt;
	}

	public Item() {
	}

	public long getCase_upc_nbr() {
		return case_upc_nbr;
	}

	public void setCase_upc_nbr(long case_upc_nbr) {
		this.case_upc_nbr = case_upc_nbr;
	}

	
	

}
