package com.gctoolkit.glannouncement;


import com.gctoolkit.glannouncement.objects.AnnouncementItem;
import emu.grasscutter.server.http.Router;
import express.Express;
import express.http.Request;
import express.http.Response;
import io.javalin.Javalin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RequestHandler implements Router {
    @Override
    public void applyRoutes(Express app, Javalin handle) {
        app.get("/glannouncement/list", RequestHandler::processRequest);
//        app.post("/glannouncement/carousel", RequestHandler::requestKey);
    }
    public void applyRoutes(Javalin javalin) {

        javalin.get("/glannouncement/list", ctx -> ctx.json(PluginTemplate.getInstance().getConfiguration().announcementItems));
//        app.post("/glannouncement/carousel", RequestHandler::requestKey);
    }
    public static void processRequest(Request req, Response res) throws IOException {{
        res.type("application/json");

        List<AnnouncementItem> announcementItems=PluginTemplate.getInstance().getConfiguration().announcementItems;
        res.json(announcementItems);
    }}
}
