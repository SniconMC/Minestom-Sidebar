package com.github.sniconmc.sidebar.config;

import java.util.List;

public class SidebarLayout {

    private boolean numbers;
    private List<String> title;
    private List<List<String>> layout;

    public boolean hasNumbers() {
        return numbers;
    }

    public List<String> getTitle() {
        return title;
    }

    public List<List<String>> getLayout() {
        return layout;
    }
}
