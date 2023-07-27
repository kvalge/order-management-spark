package org.example.controller;

import spark.ModelAndView;
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

    public String redirectWith(Response response, String path, Map<String, Object> model) {
        response.redirect(path);
        return null;
    }
}
