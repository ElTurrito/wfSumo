package wfsumo.sumo.wfsumo.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import wfsumo.sumo.wfsumo.Sumo;
import wfsumo.sumo.wfsumo.utils.CC;

public class sumoListener implements Listener {

    private Plugin plugin = Sumo.getInstance();
    private String arena;

    @EventHandler
    public void underDeathLimit(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        double y = player.getLocation().getY();
        if (Sumo.playerInArena.containsKey(player)) {
            int i;
            for(i = 0; i < Sumo.playerInArena.size(); ++i) {
                if (Sumo.playerInArena.keySet().toArray()[i].equals(player)) {
                    arena = Sumo.playerInArena.get(Sumo.playerInArena.keySet().toArray()[i]);
                }
            }

            i = this.plugin.getConfig().getInt(this.arena + ".deathlimit");
            if (y < (double)i) {
                Player winner;
                String spawnCommand;
                if (Sumo.challengers.contains(player.getName())) {
                    winner = Bukkit.getServer().getPlayer((String)Sumo.recievers.get(Sumo.challengers.indexOf(player.getName())));
                    player.sendMessage(CC.translate( Sumo.prefix + " &cYou &c&lLost &7the match against &6&l" + winner.getDisplayName()));
                    winner.sendMessage(CC.translate( Sumo.prefix + " &aYou &a&lWon &7the match against &6&l" + player.getDisplayName()));
                    Sumo.challengers.remove(player.getName());
                    Sumo.recievers.remove(winner.getName());
                    Sumo.arenaStatus.replace(this.arena, "free");
                    spawnCommand = this.plugin.getConfig().getString("spawn-cmd");
                    winner.chat(spawnCommand);
                    player.chat(spawnCommand);
                    player.setGameMode(GameMode.SURVIVAL);
                    winner.setGameMode(GameMode.SURVIVAL);
                }

                if (Sumo.recievers.contains(player.getName())) {
                    winner = Bukkit.getServer().getPlayer((String)Sumo.challengers.get(Sumo.recievers.indexOf(player.getName())));
                    player.sendMessage(CC.translate( Sumo.prefix + " &cYou &c&lLost &7the match against &6&l" + winner.getDisplayName()));
                    winner.sendMessage(CC.translate( Sumo.prefix + " &aYou &a&lWon &7the match against &6&l" + player.getDisplayName()));
                    Sumo.recievers.remove(Sumo.recievers.indexOf(player.getName()));
                    Sumo.challengers.remove(Sumo.challengers.indexOf(winner.getName()));
                    Sumo.arenaStatus.replace(this.arena, "free");
                    spawnCommand = this.plugin.getConfig().getString("spawn-cmd");
                    winner.chat(spawnCommand);
                    player.chat(spawnCommand);
                    player.setGameMode(GameMode.SURVIVAL);
                    winner.setGameMode(GameMode.SURVIVAL);
                }
            }
        }

    }

    @EventHandler
    public void damageProtect(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity attackee = e.getEntity();
        if (damager instanceof Player && (Sumo.challengers.contains(damager.getName()) || Sumo.recievers.contains(damager.getName())) && (Sumo.challengers.contains(attackee.getName()) || Sumo.recievers.contains(attackee.getName()))) {
            e.setDamage(0.0D);
        }

    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e) {
        Player j = e.getPlayer();
        j.hasPlayedBefore();
    }
}
