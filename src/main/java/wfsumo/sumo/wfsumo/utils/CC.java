package wfsumo.sumo.wfsumo.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wfsumo.sumo.wfsumo.Sumo;

public class CC {

    //
    //
    // SUMO PLUGIN BY ELTURRITO PLS IF YOU USE THIS SRC OR .JAR GIVE ME CREDITS
    // THANKS!.
    //

    public static String translate(String cc) {
       return ChatColor.translateAlternateColorCodes('&', cc);
    }

    public static void console(String cc) {
        Bukkit.getConsoleSender().sendMessage(translate(cc));
    }

    public static void sendTitleAll(String title, String subtitle){

        for(Player all : Bukkit.getOnlinePlayers()){
            all.sendTitle(title, subtitle);
            (new BukkitRunnable() {
                public void run() {
                    all.resetTitle();
                }
            }).runTaskLater(Sumo.getInstance(), 5 * 20);
        }
    }

    public static void sendTitle(Player j,String title, String subtitle){
        j.sendTitle(title,subtitle);
        (new BukkitRunnable() {
            public void run() {
                j.resetTitle();
            }
        }).runTaskLater(Sumo.getInstance(), 5 * 20);
    }
}
