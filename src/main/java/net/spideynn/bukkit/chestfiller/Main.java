package net.spideynn.bukkit.chestfiller;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    static HashMap<Player, Boolean> playerBlockSelect = new HashMap<Player, Boolean>();

    public void onEnable() {

    }

    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.toString().equalsIgnoreCase("chestfiller")) {
            playerBlockSelect.put(((Player) sender), true);
            sender.sendMessage("Now right click a chest to add it.");
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                && event.getClickedBlock().getType() != Material.CHEST
                || event.getClickedBlock().getType() != Material.TRAPPED_CHEST) return;
        if (playerBlockSelect.containsKey(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.YELLOW + "You have selected a chest and it has been added.");
            playerBlockSelect.remove(event.getPlayer());
        }
    }
}
