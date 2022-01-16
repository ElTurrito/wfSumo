package wfsumo.sumo.wfsumo.sumoUtils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import wfsumo.sumo.wfsumo.Sumo;
import wfsumo.sumo.wfsumo.utils.CC;

public class startGame {

    //
    //
    // SUMO PLUGIN BY ELTURRITO PLS IF YOU USE THIS SRC OR .JAR GIVE ME CREDITS
    // THANKS!.
    //

    private static Plugin plugin = Sumo.getInstance();
    public static String freeArena;

    public static void startSumoGame(Player sender, Player reciever) {
        searchArena.search();
        if (freeArena.equals("nonefree")) {
            sender.sendMessage(CC.translate(Sumo.prefix +" &cNo arenas available in this moment!."));
            reciever.sendMessage(CC.translate(Sumo.prefix +" &cNo arenas available in this moment!."));
        } else {
            sender.sendMessage(CC.translate(Sumo.prefix +" &aStarted SUMO match against &6&l" + reciever.getDisplayName() + " &7in arena &e&l" + freeArena));
            reciever.sendMessage(CC.translate(Sumo.prefix +" &aStarted SUMO match against &6&l" + sender.getDisplayName() + " &7in arena &e&l" + freeArena));
            Sumo.arenaStatus.replace(freeArena, "ingame");
            int x = plugin.getConfig().getInt(freeArena + ".spawn1.x");
            int y = plugin.getConfig().getInt(freeArena + ".spawn1.y");
            int z = plugin.getConfig().getInt(freeArena + ".spawn1.z");
            Block b = Bukkit.getServer().getWorld("world").getBlockAt(x, y, z);
            Location spawn1 = b.getLocation();
            sender.teleport(spawn1.add(0.5D, 0.5D, 0.5D));
            x = plugin.getConfig().getInt(freeArena + ".spawn2.x");
            y = plugin.getConfig().getInt(freeArena + ".spawn2.y");
            z = plugin.getConfig().getInt(freeArena + ".spawn2.z");
            b = Bukkit.getServer().getWorld("world").getBlockAt(x, y, z);
            Location spawn2 = b.getLocation();
            reciever.teleport(spawn2.add(0.5D, 0.5D, 0.5D));
            System.out.println(sender.getName());
            Sumo.challengers.add(sender.getName());
            Sumo.recievers.add(reciever.getName());
            Sumo.playerInArena.put(sender, freeArena);
            Sumo.playerInArena.put(reciever, freeArena);
            sender.setGameMode(GameMode.ADVENTURE);
            reciever.setGameMode(GameMode.ADVENTURE);
        }
    }
}
