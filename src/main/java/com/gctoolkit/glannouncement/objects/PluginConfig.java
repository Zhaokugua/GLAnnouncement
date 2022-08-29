package com.gctoolkit.glannouncement.objects;

import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * A data container for the plugin configuration.
 * 
 * This class is used in conjunction with {@link Gson#toJson(Object)} and {@link Gson#fromJson(Reader, Type)}.
 * With {@link Gson}, it is possible to save and load configuration values from a JSON file.
 * 
 * You can set property defaults using `public Object property = (default value);`.
 * Use {@link Gson#fromJson(Reader, Type)} to load the values set from a reader/string into a new instance of this class.
 */
public final class PluginConfig {

    public List<AnnouncementItem> announcementItems;


}
