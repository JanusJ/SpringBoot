package com.jane.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Janus on 2018/9/8.
 */
//@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "tb_user") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class User implements Serializable{

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column(name = "last_name",length = 50) //这是和数据表对应的一个列
    private String lastName;

    @Column //省略默认列名就是属性名
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "add_id")
    private Integer addressId;


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
