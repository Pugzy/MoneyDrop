package me.pugzy.moneydrop;

import lombok.Getter;
import me.pugzy.moneydrop.listeners.DeathListener;
import me.pugzy.moneydrop.utils.Parser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pugzy on 23/07/2017.
 */
public class MoneyDrop extends JavaPlugin {

    @Getter private List<Material> drops = new ArrayList<Material>();
    private boolean debug = false;
    private Parser parser = new Parser(this);

    @Override
    public void onEnable() {
        getLogger().info("MoneyDrop enabled.");
        drops = parser.getDrops();
        if (!drops.isEmpty()) generateDrops();
        Bukkit.getPluginManager().registerEvents(new DeathListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("MoneyDrop disabled.");
    }

    private void generateDrops() {
        drops.add(Material.GOLD_NUGGET);
        drops.add(Material.GOLD_INGOT);
        drops.add(Material.GOLD_BLOCK);
    }

    public void reportDrops(HashMap<Material, Integer> count) {
        if (debug) {
            Bukkit.broadcastMessage(count.toString());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("money.drop.debug")) { return false; };
        if (command.getName().toLowerCase().equals("moneydrop")) {
            if (args.length == 0) { return false; };
            if (args[0].equals("debug")) {
                debug = !debug;
                sender.sendMessage(ChatColor.AQUA + "MoneyDrop > " + ChatColor.GRAY + "Debug mode " + debug + ".");
                return true;
            }
        }
        return false;
    }

}
