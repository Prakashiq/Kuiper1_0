package com.kuiper.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PONumbers")
public class Po_num_list {

	private List<Integer> polst = null;

	public List<Integer> getPolst() {
		return polst;
	}
	
	@XmlElement(name = "Po_number")
	public void setPolst(List<Integer> polst) {
		this.polst = polst;
	}

}
