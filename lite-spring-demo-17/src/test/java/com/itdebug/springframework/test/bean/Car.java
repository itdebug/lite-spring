package com.itdebug.springframework.test.bean;

import com.itdebug.springframework.beans.factory.annotation.Value;
import com.itdebug.springframework.steretype.Component;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/26
 * @描述
 */
@Component
public class Car {

	@Value("${brand}")
	private String brand;


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car{" +
				"brand='" + brand + '\'' +
				'}';
	}
}
