package controllers;

import models.gnote.dao.impl.ImplCategory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by zhangxu on 2016/3/10.
 */
public class GNote extends Controller {
    public Result index() {
        ImplCategory a= new ImplCategory();
        return ok(views.html.note.index.render(a.findAll()));
    }
}

