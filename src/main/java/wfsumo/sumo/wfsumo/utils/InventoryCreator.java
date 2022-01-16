package wfsumo.sumo.wfsumo.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryCreator {

    public static Inventory createInventory(String title, int slots) {
        Inventory inv = Bukkit.getServer().createInventory((InventoryHolder)null, slots * 9, CC.translate(title));
        return inv;
    }
}
