package com.kuiper.StoreOrd.model;

public class InvCnt {

//	private int item_nbr;
	private String _id;
	private long total_qty;
	
	InvCnt()
	{}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public long getTotal_qty() {
		return total_qty;
	}

	public void setTotal_qty(long total_qty) {
		this.total_qty = total_qty;
	}





}
