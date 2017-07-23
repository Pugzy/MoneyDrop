package me.pugzy.moneydrop.utils;

import me.pugzy.moneydrop.MoneyDrop;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pugzy on 23/07/2017.
 */
public class Parser {

    private MoneyDrop plugin;
    private FileConfiguration config;

    public Parser(MoneyDrop plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public List<Material> getDrops() {
        List<String> configDrops = config.getStringList("drops");
        List<Material> drops = new ArrayList<Material>();
        for (String string : configDrops) {
            drops.add(Material.getMaterial(string));
        }
        return drops;
    }
}
