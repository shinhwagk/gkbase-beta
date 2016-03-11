package controllers;

import play.data.FormFactory;
import play.db.Database;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class Application extends Controller {
    @Inject
    Database db;

    @Inject
    FormFactory formFactory;

    public Result index() {
//        Form<Category> form = formFactory.form(Category.class).bindFromRequest();
        return ok(views.html.index.render());
    }

}
