package com.itdebug.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/7
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class PropertyValues {

	private final List<PropertyValue> propertyValueList = new ArrayList<>();

	public void addPropertyValue(PropertyValue pv) {
		propertyValueList.add(pv);
	}

	public PropertyValue[] getPropertyValues() {
		return propertyValueList.toArray(new PropertyValue[0]);
	}

	public PropertyValue getPropertyValue(String propertyName) {
		for (PropertyValue pv : this.propertyValueList) {
			if (pv.getName().equals(propertyName)) {
				return pv;
			}
		}
		return null;
	}
}
