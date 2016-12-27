package com.kuiper.common.model;

public class PurchaseOrderLine {

	protected int po_line_nbr;

	protected float ordered_qty;

	protected String unit_UOM;

	protected float each_cost;

	protected int item_nbr;

	public int getPo_line_nbr() {
		return po_line_nbr;
	}

	public void setPo_line_nbr(int po_line_nbr) {
		this.po_line_nbr = po_line_nbr;
	}

	public float getOrdered_qty() {
		return ordered_qty;
	}

	public void setOrdered_qty(float ordered_qty) {
		this.ordered_qty = ordered_qty;
	}

	public String getUnit_UOM() {
		return unit_UOM;
	}

	public void setUnit_UOM(String unit_UOM) {
		this.unit_UOM = unit_UOM;
	}

	public float getEach_cost() {
		return each_cost;
	}

	public void setEach_cost(float each_cost) {
		this.each_cost = each_cost;
	}

	public int getItem_nbr() {
		return item_nbr;
	}

	public void setItem_nbr(int item_nbr) {
		this.item_nbr = item_nbr;
	}

	public PurchaseOrderLine() {

	}
}
