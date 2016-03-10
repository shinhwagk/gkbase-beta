package models.gnote.dao.impl;

import play.db.Database;

import javax.inject.Inject;

/**
 * Created by zhangxu on 2016/3/10.
 */
public interface DML {
    @Inject
    Database db = null;
}
