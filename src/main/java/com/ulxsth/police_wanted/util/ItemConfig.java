package com.ulxsth.police_wanted.util;

import com.ulxsth.police_wanted.PoliceWantedPlugin;
import com.ulxsth.police_wanted.model.item.GameItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ItemConfig {
    private static final ItemConfig instance = new ItemConfig();

    private static final PoliceWantedPlugin plugin = PoliceWantedPlugin.getInstance();
    public static final String PATH = plugin.getDataFolder().getPath() + "/item.yml";

    private static final YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(PATH));

    private ItemConfig() {}

    public static ItemConfig getInstance() {
        return instance;
    }

    public static GameItem getItemById(String id) {
        ConfigurationSection section = config.getConfigurationSection(id);
        if(section == null) {
            throw new IllegalArgumentException("cant find: " + id);
        }

        String materialId = section.getString(id + ".material");
        String name = section.getString(id + ".name");
        int itemId = section.getInt(id + ".id");

        return new GameItem(materialId, name, itemId);
    }
}
