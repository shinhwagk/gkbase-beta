package models.gnote.dao.impl;

import models.gnote.dao.entity.Category;
import models.gnote.dao.entity.Content;
import play.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxu on 2016/3/11.
 */
public class ImplContent extends Content {
    public static List<Content> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Content> all = new ArrayList<>();
        try {
            conn = DB.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id,content_1,content_2,category_id,createdata,updatedata,ifnull(document_id,0) document_id from g_note.content");
            while (rs.next()) {
                Content tabc = new Content();
                tabc.setId(rs.getInt(1));
                tabc.setContent_1(rs.getString(2));
                tabc.setContent_2(rs.getString(3));
                tabc.setCategory_id(rs.getInt(4));
                tabc.setCreatedata(rs.getDate(5));
                tabc.setUpdatedata(rs.getDate(6));
                tabc.setDocument_id(rs.getInt(7));
                all.add(tabc);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }


}
