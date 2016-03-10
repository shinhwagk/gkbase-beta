package models.gnote.dao.entity;

import java.sql.Date;

/**
 * Created by zhangxu on 2016/3/10.
 */
public class Category {
    private int id;
    private String name;
    private int father_id;
    private Date createdata;
    private Date updatedata;

    public Category(int id, String name, int father_id, Date createdata, Date updatedata) {
        this.id = id;
        this.name = name;
        this.father_id = father_id;
        this.createdata = createdata;
        this.updatedata = updatedata;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFather_id() {
        return father_id;
    }

    public Date getCreatedata() {
        return createdata;
    }

    public Date getUpdatedata() {
        return updatedata;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }

    public void setCreatedata(Date createdata) {
        this.createdata = createdata;
    }

    public void setUpdatedata(Date updatedata) {
        this.updatedata = updatedata;
    }
}
