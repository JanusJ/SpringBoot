package com.jane.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="tb_address")
@Entity
public class Address implements Serializable {

	private Integer id;
	private String province;
	private String city;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
