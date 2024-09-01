package com.github.sniconmc.sidebar;

import com.github.sniconmc.sidebar.config.SidebarLayout;
import com.github.sniconmc.sidebar.utils.CreateSidebar;
import com.github.sniconmc.sidebar.utils.LoadSidebar;
import com.github.sniconmc.utils.placeholder.PlaceholderReplacer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SidebarManager {

    // Load new Gson
    private static Gson gson = new Gson();

    private static final File dataFolder = new File("resources/sidebar");

    private static Map<String, String> dataFileJSONData;
    private static Map<Player, String> currentSidebar = new HashMap<>();

    public SidebarManager() {

        gson = new GsonBuilder().setPrettyPrinting().create();

        dataFileJSONData = new LoadSidebar().load(dataFolder);
    }

    public static void reloadSidebars(){
        dataFileJSONData = new LoadSidebar().load(dataFolder);

        for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
            setSidebar(player, currentSidebar.get(player));
        }
    }

    public static void setSidebar(Player player, String sidebarName) {

        String data = dataFileJSONData.get(sidebarName);

        if (data == null) {
            SidebarMain.logger.error("Sidebar not found: {}", sidebarName);
            return;
        }

        String placeholderReplacedJson = PlaceholderReplacer.replacePlaceholders(player, data);

        try {

            SidebarLayout layout = gson.fromJson(placeholderReplacedJson, SidebarLayout.class);

            Sidebar sidebar = new CreateSidebar().build(layout);

            sidebar.addViewer(player);
            currentSidebar.put(player, sidebarName);
        } catch (JsonSyntaxException | JsonIOException e) {
            // Handle Gson-specific errors
            SidebarMain.logger.error("Error parsing JSON in: {}", sidebarName);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            SidebarMain.logger.error("Unexpected error in: {}, {}", sidebarName,  e.toString());
        }

    }

}
