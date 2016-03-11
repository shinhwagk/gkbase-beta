package models.gnote.dao.entity;

import java.sql.Date;

/**
 * Created by zhangxu on 2016/3/10.
 */
public class Content {

    private int id;
    private String content_1;
    private String content_2;
    private int category_id;
    private Date createdata;
    private Date updatedata;
    private int document_id;

    public Content() {

    }

    public Content(int id, String content_1, String content_2, int category_id, Date createdata, Date updatedata, int document_id) {
        this.id = id;
        this.content_1 = content_1;
        this.content_2 = content_2;
        this.category_id = category_id;
        this.createdata = createdata;
        this.updatedata = updatedata;
        this.document_id = document_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent_1() {
        return content_1;
    }

    public void setContent_1(String content_1) {
        this.content_1 = content_1;
    }

    public String getContent_2() {
        return content_2;
    }

    public void setContent_2(String content_2) {
        this.content_2 = content_2;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getCreatedata() {
        return createdata;
    }

    public void setCreatedata(Date createdata) {
        this.createdata = createdata;
    }

    public Date getUpdatedata() {
        return updatedata;
    }

    public void setUpdatedata(Date updatedata) {
        this.updatedata = updatedata;
    }

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }
}

