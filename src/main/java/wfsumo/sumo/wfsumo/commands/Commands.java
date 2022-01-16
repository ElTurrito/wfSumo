package wfsumo.sumo.wfsumo.commands;

import net.minecraft.server.v1_8_R3.CommandExecute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import wfsumo.sumo.wfsumo.Sumo;
import wfsumo.sumo.wfsumo.menu.acceptMenu;
import wfsumo.sumo.wfsumo.sumoUtils.searchArena;
import wfsumo.sumo.wfsumo.utils.CC;

public class Commands extends CommandExecute implements Listener, CommandExecutor {

    //
    //
    // SUMO PLUGIN BY ELTURRITO PLS IF YOU USE THIS SRC OR .JAR GIVE ME CREDITS
    // THANKS!.
    //


    private Plugin plugin = Sumo.getInstance();
    public String cmd1 = "sumo";
    public String cmd2 = "sa";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (cmd.getName().equalsIgnoreCase(this.cmd1)) {
            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (searchArena.invClear(player) && searchArena.invClear(target)) {
                    player.sendMessage(CC.translate(Sumo.prefix + " &aYou sent a SUMO request to &e&l" + target.getDisplayName()));
                    acceptMenu.openAcceptMenu(target, player);
                } else {
                    player.sendMessage(CC.translate( Sumo.prefix + " &cPlease empty your inventory then retry."));
                    target.sendMessage(CC.translate( Sumo.prefix + " &cPlease empty your inventory then retry."));
                }
            } else {
                player.sendMessage("Use /sumo or /sa");
            }
        }

        if (cmd.getName().equalsIgnoreCase("sa")) {
            if (sender.hasPermission("sumo.admin")) {
                if (args.length == 0) {
                    sender.sendMessage(CC.translate( Sumo.prefix + " &eList of current arenas:"));
                    if (Sumo.arenas != null) {
                        for(int x = 0; x < Sumo.arenas.length; ++x) {
                            if (!Sumo.arenas[x].equals("null")) {
                                sender.sendMessage(ChatColor.GREEN + "- " + Sumo.arenas[x]);
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You have not configured any arenas");
                    }
                }

                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("create")) {
                        this.plugin.getConfig().createSection(args[1]);
                        String currentArenas = this.plugin.getConfig().getString("arenas");
                        if (currentArenas == "") {
                            this.plugin.getConfig().set("arenas", args[1]);
                        }

                        this.plugin.getConfig().set("arenas", currentArenas + "," + args[1]);
                        this.plugin.saveConfig();
                        currentArenas = "";
                        sender.sendMessage(CC.translate( Sumo.prefix + " &cPlease reload the server to finish creating the new arena"));
                    }

                    Location loc;
                    if (args[1].equalsIgnoreCase("spawn1") && this.plugin.getConfig().contains(args[0])) {
                        loc = player.getLocation();
                        this.plugin.getConfig().set(args[0] + ".world", player.getWorld().getName());
                        this.plugin.getConfig().set(args[0] + ".spawn1.x", loc.getBlockX());
                        this.plugin.getConfig().set(args[0] + ".spawn1.y", loc.getBlockY());
                        this.plugin.getConfig().set(args[0] + ".spawn1.z", loc.getBlockZ());
                        this.plugin.saveConfig();
                        sender.sendMessage(CC.translate( Sumo.prefix + " &aSuccessfully set spawnpoint 1"));
                    }

                    if (args[1].equalsIgnoreCase("spawn2") && this.plugin.getConfig().contains(args[0])) {
                        loc = player.getLocation();
                        this.plugin.getConfig().set(args[0] + ".world", player.getWorld().getName());
                        this.plugin.getConfig().set(args[0] + ".spawn2.x", loc.getBlockX());
                        this.plugin.getConfig().set(args[0] + ".spawn2.y", loc.getBlockY());
                        this.plugin.getConfig().set(args[0] + ".spawn2.z", loc.getBlockZ());
                        this.plugin.saveConfig();
                        sender.sendMessage(CC.translate( Sumo.prefix + " &aSuccessfully set spawnpoint 2"));
                    }
                }

                if (args.length == 3 && args[1].equalsIgnoreCase("deathlimit") && this.plugin.getConfig().contains(args[0])) {
                    this.plugin.getConfig().set(args[0] + ".deathlimit", Integer.parseInt(args[2]));
                    this.plugin.saveConfig();
                    sender.sendMessage(CC.translate( Sumo.prefix + " &aSuccessfully set deathlimit to " + args[2]));
                }
            } else {
                sender.sendMessage(CC.translate(Sumo.prefix +" &cYou do not have admin permissions."));
            }
        }

        return false;
    }
}


