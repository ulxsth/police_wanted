package com.ulxsth.police_wanted.util;

import com.ulxsth.police_wanted.PoliceWantedPlugin;
import com.ulxsth.police_wanted.model.item.GameItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ItemConfig {
    private static final PoliceWantedPlugin plugin = PoliceWantedPlugin.getInstance();
    public static final String PATH = plugin.getDataFolder().getPath() + "/item.yml";

    private static YamlConfiguration config;

    private ItemConfig() {}

    public static void initConfig() {
        if(!new File(PATH).exists()) {
            plugin.saveResource("item.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(new File(PATH));
    }

    public static GameItem getItemById(String id) {
        initConfig();
        ConfigurationSection section = config.getConfigurationSection(id);
        if(section == null) {
            throw new IllegalArgumentException("cant find: " + id);
        }

        String materialId = section.getString("material");
        String name = section.getString("name");
        int itemId = section.getInt("id");

        return new GameItem(materialId, name, itemId);
    }
}
