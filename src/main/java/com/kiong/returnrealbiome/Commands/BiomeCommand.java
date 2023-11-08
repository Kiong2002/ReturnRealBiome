package com.kiong.returnrealbiome.Commands;

import com.kiong.returnrealbiome.ReturnRealBiome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BiomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Current Biome: "+ ReturnRealBiome.getBiomeKey(player.getLocation()));
        }
        return true;
    }
}
