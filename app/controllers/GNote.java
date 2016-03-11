package controllers;

import models.gnote.dao.entity.Category;
import models.gnote.dao.entity.Content;
import models.gnote.dao.impl.ImplCategory;
import models.gnote.dao.impl.ImplContent;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangxu on 2016/3/10.
 */
public class GNote extends Controller {
    public Result index(int id) {
        ImplCategory a = new ImplCategory();
        ImplContent b = new ImplContent();
        List<Category> topNav = a.findAll().stream().filter(p -> p.getFather_id() == 0).collect(Collectors.toList());
        List<Category> list = a.findAll().stream().filter(p -> p.getFather_id() == id).collect(Collectors.toList());
        List<Content> Content_list = b.findAll().stream().filter(p -> p.getCategory_id() == id).collect(Collectors.toList());

        return ok(views.html.note.index.render(topNav, list,Content_list));
    }
}

