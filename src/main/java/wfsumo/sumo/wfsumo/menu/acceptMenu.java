package wfsumo.sumo.wfsumo.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import wfsumo.sumo.wfsumo.Sumo;
import wfsumo.sumo.wfsumo.sumoUtils.startGame;
import wfsumo.sumo.wfsumo.utils.CC;
import wfsumo.sumo.wfsumo.utils.InventoryCreator;

public class acceptMenu implements Listener {

    public static void openAcceptMenu(Player j, Player sentFrom) {
        String title = Sumo.getInstance().configFile.getString("SETTINGS.DUEL-MENU.TITLE");
        Inventory inv = InventoryCreator.createInventory(title, 3);

        ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName(CC.translate("&c."));
        empty.setItemMeta(emptyMeta);
        ItemStack sender = new ItemStack(Material.ANVIL);
        ItemMeta senderM = sender.getItemMeta();
        senderM.setDisplayName(CC.translate("&c&l" + sentFrom.getDisplayName()));
        sender.setItemMeta(senderM);
        ItemStack accept = new ItemStack(Material.NETHER_STAR);
        ItemMeta acceptM = accept.getItemMeta();
        acceptM.setDisplayName(CC.translate("&aACCEPT"));
        accept.setItemMeta(acceptM);

        inv.setItem(0, sender);
        inv.setItem(1, empty);
        inv.setItem(2, empty);
        inv.setItem(3, empty);
        inv.setItem(4, accept);
        inv.setItem(5, empty);
        inv.setItem(6, empty);
        inv.setItem(7, empty);
        inv.setItem(8, empty);
        j.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack item = event.getCurrentItem();
        String title = Sumo.getInstance().configFile.getString("SETTINGS.DUEL-MENU.TITLE");
        if (inv != null) {
            if (inv.getName().equals(title)) {
                if (item.hasItemMeta() && item.getItemMeta().getDisplayName().equals(CC.translate(  "ACCEPT"))) {
                    String senderName = inv.getItem(0).getItemMeta().getDisplayName();
                    Player challenger = Bukkit.getPlayer(ChatColor.stripColor(senderName));
                    player.closeInventory();
                    player.sendMessage(CC.translate( Sumo.prefix + "You have accepted the invite from &6&l" + challenger.getDisplayName()));
                    challenger.sendMessage(CC.translate( Sumo.prefix + "&6&l" + player.getDisplayName() + "&r&7 has accepted your SUMO request"));
                    startGame.startSumoGame(challenger, player);
                }

                event.setCancelled(true);
            }

        }
    }
}

