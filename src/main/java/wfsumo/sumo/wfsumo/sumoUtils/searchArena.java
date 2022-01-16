package wfsumo.sumo.wfsumo.sumoUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import wfsumo.sumo.wfsumo.Sumo;

import java.util.Iterator;

public class searchArena {

    public static void search() {
        Iterator var1 = Sumo.arenaStatus.keySet().iterator();

        while(var1.hasNext()) {
            String key = (String)var1.next();
            String value = (String)Sumo.arenaStatus.get(key);
            if (value.equals("free")) {
                startGame.freeArena = key;
                return;
            }
        }

        startGame.freeArena = "nonefree";
    }

    public static boolean invClear(Player player) {
        ItemStack[] var4;
        int var3 = (var4 = player.getInventory().getContents()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ItemStack it = var4[var2];
            if (it != null) {
                return false;
            }
        }

        return true;
    }
}
