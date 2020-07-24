package com.example.demo.utils.enums.product;
import lombok.Getter;

@Getter
public enum UnitType {
	KG("Kg"),
	PCS("Pcs");
	private String name;

	UnitType(String name){
		this.name = name;
	}
}