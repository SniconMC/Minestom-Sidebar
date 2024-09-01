package com.github.sniconmc.sidebar.commands;

import com.github.sniconmc.sidebar.SidebarManager;
import com.github.sniconmc.utils.text.TextUtils;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

import java.util.List;

/**
 * Represents the command handler for the Sidebar command.
 * Provides subcommands for interacting with the Sidebar dependency.
 *
 * @see SidebarManager
 * @see TextUtils
 * @see Command
 * @see Player
 *
 * @author znopp and Wi1helm
 */
public class SidebarCommand extends Command {

    /**
     * Initializes the Sidebar command with its subcommands and their corresponding logic.
     * Currently supports the "reload" subcommand for reloading Sidebar-related configurations.
     */
    public SidebarCommand() {
        super("sidebar");

        // Sets the default executor for the Sidebar command when no subcommands are specified.
        setDefaultExecutor((commandSender, commandContext) -> {
        });

        // Argument for the "reload" subcommand
        var reloadArgument = ArgumentType.Literal("reload");

        // Adds the "reload" subcommand syntax and its corresponding execution logic
        addSyntax((commandSender, commandContext) -> {
            if (!(commandSender instanceof Player player)) {
                return;  // Command can only be executed by a player
            }

            String actionString = commandContext.get(reloadArgument);

            if (actionString.equalsIgnoreCase("reload")) {
                SidebarManager.reloadSidebars();
                player.sendMessage(TextUtils.convertStringToComponent(List.of("<green>Reloaded Sidebar</green>")));
            }
        }, reloadArgument);
    }
}
