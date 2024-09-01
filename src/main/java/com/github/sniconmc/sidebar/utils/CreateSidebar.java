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

        for (List<String> text : layout.getLayout()) {
            int index = layout.getLayout().size() - layout.getLayout().indexOf(text);
            String id = "row" + index;

            // Text
            Component content = TextUtils.convertStringToComponent(layout.getLayout().get(index));

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
