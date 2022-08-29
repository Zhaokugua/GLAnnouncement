package com.gctoolkit.glannouncement;

import com.gctoolkit.glannouncement.commands.ExampleCommand;
import com.gctoolkit.glannouncement.objects.PluginConfig;
import com.google.gson.Gson;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;

import com.gctoolkit.glannouncement.commands.*;
import com.gctoolkit.glannouncement.objects.*;

import java.io.*;
import java.util.stream.Collectors;

/**
 * The Grasscutter plugin template.
 * This is the main class for the plugin.
 */
public final class PluginTemplate extends Plugin {
    /* Turn the plugin into a singleton. */
    private static PluginTemplate instance;

    /**
     * Gets the plugin instance.
     *
     * @return A plugin singleton.
     */
    public static PluginTemplate getInstance() {
        return instance;
    }

    /* The plugin's configuration instance. */
    private PluginConfig configuration;

    /**
     * This method is called immediately after the plugin is first loaded into system memory.
     */
    @Override
    public void onLoad() {
        // Set the plugin instance.
        instance = this;

        // Get the configuration file.
        File config = new File(this.getDataFolder(), "config.json");

        // Load the configuration.
        try {
            if (!config.exists()){
                this.getLogger().info("[GLAnnouncement] 配置文件不存在，将创建默认配置文件");

                try (FileWriter writer = new FileWriter(config)) {
                    InputStream configStream = this.getResource("config.json");
                    if (configStream == null) {
                        this.getLogger().error("[GLAnnouncement] 写入默认配置文件失败!");
                    } else {
                        writer.write(new BufferedReader(
                                new InputStreamReader(configStream)).lines().collect(Collectors.joining("\n"))
                        );
                        writer.close();

                        this.getLogger().info("[GLAnnouncement] 成功创建了默认配置文件");
                    }
                }
            }

            Gson gson = new Gson();
            // Put the configuration into an instance of the config class.
            this.configuration = gson.fromJson(new FileReader(config), PluginConfig.class);
        } catch (IOException exception) {
            this.getLogger().error("[GLAnnouncement] 读取配置文件失败!", exception);
        }

        // Log a plugin status message.
        this.getLogger().info("[GLAnnouncement] The GLAnnouncement plugin has been loaded.");
    }

    /**
     * This method is called before the servers are started, or when the plugin enables.
     */
    @Override
    public void onEnable() {
        // Register event listeners.
//        new EventHandler<>(PlayerJoinEvent.class)
//                .priority(HandlerPriority.LOW)
//                .listener(EventListeners::onJoin)
//                .register();
//
        this.getHandle().registerCommand(new ExampleCommand());
        Grasscutter.getHttpServer().addRouter(RequestHandler.class);

        // Log a plugin status message.
        this.getLogger().info("[GLAnnouncement] The GLAnnouncement plugin has been enabled.");
    }

    /**
     * This method is called when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        // Log a plugin status message.

        this.getLogger().info("[GLAnnouncement] The GLAnnouncement plugin has been disabled.");
    }

    /**
     * Gets the plugin's configuration.
     *
     * @return A plugin config instance.
     */
    public PluginConfig getConfiguration() {
        return this.configuration;
    }
    public void setConfiguration(PluginConfig cfg) {
        this.configuration = cfg;
    }
}
