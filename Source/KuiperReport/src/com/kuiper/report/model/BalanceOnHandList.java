package com.kuiper.report.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class BalanceOnHandList {

	@XmlElementWrapper(name = "BohList")
	@XmlElement(name = "Boh")
	protected List<BalanceOnHand> bohList;

	public List<BalanceOnHand> getBohList() {
		return bohList;
	}

	public void setBohList(List<BalanceOnHand> bohList) {
		this.bohList = bohList;
	}

}
