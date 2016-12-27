package com.kuiper.receive.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PO_Line", propOrder = { "po_line_nbr", "item_nbr", "ordered_qty","due_qty","received_qty", "unit_UOM", "each_cost", "lpn" })
public class PurchaseOrderLine {

	@XmlElement(name = "each_cost")
	protected float each_cost;
	
	@XmlElement(name = "item_nbr")
	protected int item_nbr;
	
	@XmlElement(name = "ordered_qty")
	protected int ordered_qty;
	
/*	
	@XmlElement(name = "OSDR_qty")
	private int OSDR_qty;
	*/
	
	@XmlElement(name = "due_qty", required = false)
	protected int due_qty;
	
	
	@XmlElement(name = "po_line_nbr")
	protected int po_line_nbr;

	
	@XmlElement(name = "received_qty", required = false)
	protected int received_qty;

	@XmlElement(name="LPN") //Preprinted License Plate Number 
	protected String lpn;

	public String getLpn() {
		return lpn;
	}

	public void setLpn(String lpn) {
		this.lpn = lpn;
	}

	public int getDue_qty() {
		return due_qty;
	}

	public void setDue_qty(int due_qty ) {
		this.due_qty = due_qty;
	}

	public int getReceived_qty() {
		return received_qty;
	}

	public void setReceived_qty(int received_qty) {
		this.received_qty = received_qty;
	}


	@XmlElement(name = "unit_UOM")
	protected String unit_UOM;

	public PurchaseOrderLine() {

	}

	public float getEach_cost() {
		return each_cost;
	}

	public int getItem_nbr() {
		return item_nbr;
	}

	public int getOrdered_qty() {
		return ordered_qty;
	}
	

	public int getPo_line_nbr() {
		return po_line_nbr;
	}

	public String getUnit_UOM() {
		return unit_UOM;
	}

	public void setEach_cost(float each_cost) {
		this.each_cost = each_cost;
	}

	public void setItem_nbr(int item_nbr) {
		this.item_nbr = item_nbr;
	}

	public void setOrdered_qty(int ordered_qty) {
		this.ordered_qty = ordered_qty;
	}

	
	public void setPo_line_nbr(int po_line_nbr) {
		this.po_line_nbr = po_line_nbr;
	}


	public void setUnit_UOM(String unit_UOM) {
		this.unit_UOM = unit_UOM;
	}
}
