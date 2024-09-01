package com.github.sniconmc.sidebar;

import com.github.sniconmc.sidebar.commands.SidebarCommand;
import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SidebarMain {

    public static final Logger logger = LoggerFactory.getLogger(SidebarMain.class);

    public static void init() {
        logger.info("Sidebar initialized");

        SidebarManager sidebarManager = new SidebarManager();

        MinecraftServer.getCommandManager().register(new SidebarCommand());
    }
}