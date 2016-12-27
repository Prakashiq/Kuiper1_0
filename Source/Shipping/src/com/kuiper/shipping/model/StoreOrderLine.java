package com.kuiper.shipping.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order_Line", propOrder = {  "order_line_nbr", "item_nbr", "ordered_qty", "fulfilled_qty", "due_qty",  "unit_UOM", "each_cost"  })
public class StoreOrderLine {

	@XmlElement(name = "order_line_nbr")	
	protected int order_line_nbr;

	@XmlElement(name = "unit_UOM")
	protected String unit_UOM;
	
	@XmlElement(name = "each_cost")
	protected float each_cost;
	
	@XmlElement(name = "item_nbr")
	protected int item_nbr;

	@XmlElement(name = "ordered_qty")
	private int ordered_qty;
	
	@XmlElement(name = "fulfilled_qty")
	private int fulfilled_qty;
	
	@XmlElement(name = "due_qty")
	private int due_qty;
	
	public int getOrdered_qty() {
		return ordered_qty;
	}


	public void setOrdered_qty(int ordered_qty) {
		this.ordered_qty = ordered_qty;
	}


	public int getFulfilled_qty() {
		return fulfilled_qty;
	}


	public void setFulfilled_qty(int fulfilled_qty) {
		this.fulfilled_qty = fulfilled_qty;
	}


	public int getDue_qty() {
		return due_qty;
	}


	public void setDue_qty(int due_qty) {
		this.due_qty = due_qty;
	}


	public int getOrder_line_nbr() {
		return order_line_nbr;
	}


	public void setOrder_line_nbr(int order_line_nbr) {
		this.order_line_nbr = order_line_nbr;
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


	public StoreOrderLine() {

	}
}
