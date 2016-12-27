package com.kuiper.report.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemDimension", propOrder = { "height", "width", "depth", "uom" })

public class ItemDimension {
	 @XmlElement
	protected float height;
	 @XmlElement
	protected float width;
	 @XmlElement
	protected float depth;
	 @XmlElement
	protected String uom;

	public ItemDimension() {
	}

	public float getHeight() {
		return height;
	}

	//@XmlElement
	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	//@XmlElement
	public void setWidth(float width) {
		this.width = width;
	}

	public float getDepth() {
		return depth;
	}

	//@XmlElement
	public void setDepth(float depth) {
		this.depth = depth;
	}

	public String getUom() {
		return uom;
	}

	//@XmlElement
	public void setUom(String uom) {
		this.uom = uom;
	}
}
