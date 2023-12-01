package com.itdebug.springframework.test.bean;

import com.itdebug.springframework.beans.factory.annotation.Value;
import com.itdebug.springframework.steretype.Component;

import java.time.LocalDate;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/11/26
 * @描述
 */
@Component
public class Car {

	private int price;

	private LocalDate produceDate;

	@Value("${brand}")
	private String brand;


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(LocalDate produceDate) {
		this.produceDate = produceDate;
	}

	@Override
	public String toString() {
		return "Car{" +
				"price=" + price +
				", produceDate=" + produceDate +
				", brand='" + brand + '\'' +
				'}';
	}
}
