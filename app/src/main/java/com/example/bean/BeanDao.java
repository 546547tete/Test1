package com.example.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BeanDao {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String type;
    private boolean chick;
    @Generated(hash = 1507125197)
    public BeanDao(Long id, String name, String type, boolean chick) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.chick = chick;
    }
    @Generated(hash = 1438189893)
    public BeanDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public boolean getChick() {
        return this.chick;
    }
    public void setChick(boolean chick) {
        this.chick = chick;
    }

}
