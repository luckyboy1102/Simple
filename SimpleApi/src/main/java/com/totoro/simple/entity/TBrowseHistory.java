package com.totoro.simple.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 浏览记录实体
 * Created by Chen on 2015/2/13.
 */
@Entity
@Table(name = "tb_browse_history")
public class TBrowseHistory implements Serializable {

    private String id;

    private String userId;

    private String itemId;

    private Date browseTime;

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

    @Column(name = "USERID", length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "ITEMID", length = 32)
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }
}
