package com.kuiper.report.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Item")
public class Item {


	 @XmlElement
	public long case_upc_nbr;

	 @XmlElement 
	 
	protected float cost_amt;

	 @XmlElement 
	protected String item_desc;

	 @XmlElement 
	protected int _id;

	 public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	@XmlElement 
	protected String item_status_code;

	 @XmlElement 
	protected ItemDimension itemDimension;

	 @XmlElement 
	protected int min_ord_qty;

	 @XmlElement 
	protected float sell_amt;

	 @XmlElement  
	protected String uom;

	 @XmlElement
	protected long vendor_stock_id;

	private String last_modified_ts;
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


	public String getItem_status_code() {
		return item_status_code;
	}

	public ItemDimension getItemDimension() {
		return itemDimension;
	}

//	@XmlElement
	public void setItemDimension(ItemDimension itemDimension) {
		
		this.itemDimension = itemDimension;
	}

	public String getLast_modified_ts() {
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

//	@XmlElement(name = "CaseUpcNumber")
	public void setCase_upc_nbr(long case_upc_nbr) {
		this.case_upc_nbr = case_upc_nbr;
	}

//	@XmlElement(name = "CostAmount")
	public void setCost_amt(float cost_amt) {
		this.cost_amt = cost_amt;
	}

//	@XmlElement(name = "ItemDescription")
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}



//	@XmlElement(name = "ItemStatusCode")
	public void setItem_status_code(String item_status_code) {
		this.item_status_code = item_status_code;
	}

	public void setLast_modified_ts(String last_modified_ts) {
		this.last_modified_ts = last_modified_ts;
	}

//	@XmlElement(name = "MinOrderQty")
	public void setMin_ord_qty(int min_ord_qty) {
		this.min_ord_qty = min_ord_qty;
	}

//	@XmlElement(name = "SellAmount")
	public void setSell_amt(float sell_amt) {
		this.sell_amt = sell_amt;
	}

//	@XmlElement(name = "UnitOfMeasure")
	public void setUom(String uom) {
		this.uom = uom;
	}

//	@XmlElement(name = "VendorStockID")
	public void setVendor_stock_id(long vendor_stock_id) {
		this.vendor_stock_id = vendor_stock_id;
	}

}
