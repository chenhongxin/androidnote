package com.haoxitech.canzhaopin.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 * 
 */
public class Definedtypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> map;
	private List<Map<String, Object>> list;

	public Definedtypes(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Definedtypes(List<Map<String, Object>> list) {
		this.list = list;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

}
