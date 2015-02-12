package com.totoro.simple.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Chen on 2015/2/12.
 */
@Entity
@Table(name = "tb_item")
public class TItem implements Serializable {

    private String id;

    private String name;

    private float price;

    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PRICE", scale = 10, precision = 2)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
