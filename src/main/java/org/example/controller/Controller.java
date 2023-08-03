package org.example.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class Controller {

    public String render(String templatePath, Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public String redirect(Response response, String path) {
        response.redirect(path);
        return null;
    }

    String redirectWithAttribute(Request request, Response response, String path, Object attr) {
        request.session().attribute("attribute", attr);
        return redirect(response, path);
    }
}
