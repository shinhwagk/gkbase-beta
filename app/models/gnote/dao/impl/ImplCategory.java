package models.gnote.dao.impl;

import models.gnote.dao.entity.Category;
import play.db.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxu on 2016/3/10.
 */
public class ImplCategory extends Category {

    String insert = "insert into category(name,father_id,createdate,updatedate) values('',?,now(),now())";

    public void add(int father_id) {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = null;
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, father_id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {

    }

    public void update(Category tab) {

    }

    public Category byKey(int id) {
        return null;
    }

    public static List<Category> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Category> all = new ArrayList<>();
        try {
            conn = DB.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id,name,father_id from g_note.category");
            while (rs.next()) {
                System.out.println("abc");
                Category tabc = new Category();
                tabc.setId(rs.getInt(1));
                tabc.setName(rs.getString(2));
                tabc.setFather_id(rs.getInt(3));
                all.add(tabc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public static void main(String[] args) {
        ImplCategory a = new ImplCategory();
        a.setId(1);
        System.out.println(a.getId());
    }
}
