package com.github.sniconmc.sidebar.utils;

import com.github.sniconmc.sidebar.config.SidebarLayout;
import com.github.sniconmc.utils.text.TextUtils;
import net.kyori.adventure.text.Component;
import net.minestom.server.scoreboard.Sidebar;
import org.w3c.dom.Text;

import java.util.List;

public class CreateSidebar {

    public Sidebar build(SidebarLayout layout) {

        Component title = TextUtils.convertStringToComponent(layout.getTitle());

        Sidebar sidebar = new Sidebar(title);

        // Get the layout list once to avoid multiple method calls
        List<List<String>> layoutList = layout.getLayout();

        // Loop in reverse order
        for (int i = layoutList.size() - 1; i >= 0; i--) {
            List<String> text = layoutList.get(i);  // Get the current text list

            int index = layoutList.size() - i;  // Calculate the index correctly
            String id = "row" + index;

            // Text
            Component content = TextUtils.convertStringToComponent(text); // Adjust as needed

            // Create the line
            Sidebar.ScoreboardLine line1 = new Sidebar.ScoreboardLine(
                    id,
                    content,
                    index,
                    Sidebar.NumberFormat.blank()
            );

            sidebar.createLine(line1);
        }

        return sidebar;
    }
}

