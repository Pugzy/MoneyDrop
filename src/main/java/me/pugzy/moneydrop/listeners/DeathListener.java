package me.pugzy.moneydrop.listeners;

import me.pugzy.moneydrop.MoneyDrop;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Pugzy on 23/07/2017.
 */
public class DeathListener implements Listener {

    private MoneyDrop plugin;
    private List<Material> allowed;

    public DeathListener(MoneyDrop instance) {
        this.plugin = instance;
        allowed = plugin.getDrops();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!checkPermission(event.getEntity())) { return; }
        ItemStack[] drops = event.getEntity().getInventory().getContents();
        Player player = event.getEntity();
        HashMap<Material, Integer> count = new HashMap<Material, Integer>();
        for (ItemStack item : drops) {
            if (item != null) {
                Material type = item.getType();
                if (allowed.contains(type)) {
                    player.getWorld().dropItemNaturally(player.getLocation(), item);
                    int amount = 0;
                    if (count.containsKey(type)) {
                        amount = count.get(type);
                    }
                    count.put(item.getType(), item.getAmount() + amount);
                    item.setAmount(0);
                }
            }
        }
        plugin.reportDrops(count);
    }

    private boolean checkPermission(Player player) {
        return !player.hasPermission("money.drop.exempt")
                && player.hasPermission("money.drop");
    }

}
