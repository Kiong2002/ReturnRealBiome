package com.kiong.returnrealbiome;

import com.kiong.returnrealbiome.Commands.BiomeCommand;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import org.bukkit.plugin.java.JavaPlugin;
// imports
import net.minecraft.core.IRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
// imports end

public final class ReturnRealBiome extends JavaPlugin {

    public BiomeBase getBiomeBase(String namespace, String id) {
        // aR = BiomeBase registry, aV() = registryAccess()
        DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
        IRegistry<BiomeBase> var = null;
        IRegistry<BiomeBase> registry = dedicatedServer.aV().d(var.c());
        ResourceKey<BiomeBase> key = ResourceKey.a(var.c(), new MinecraftKey(namespace, id));

        return registry.a(key);
    }
    public static BiomeBase getBiomeBase(Location location) {
        World bukkitWorld = location.getWorld();
        if(bukkitWorld == null)
            return null;

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        net.minecraft.world.level.World nmsWorld = ((CraftWorld) bukkitWorld).getHandle();
        return nmsWorld.getNoiseBiome(x >> 2, y >> 2, z >> 2).a();
    }
    public static MinecraftKey getBiomeKey(Location location) {

        DedicatedServer dedicatedServer = ((CraftServer) Bukkit.getServer()).getServer();
        IRegistry<BiomeBase> var = null;
        IRegistry<BiomeBase> registry = dedicatedServer.aV().d(var.c());

        return registry.b(getBiomeBase(location));
    }
    // get bukkit world
    public World getWorld(String namespace, String id) {
        DedicatedServer dedicatedServer = ((CraftServer)Bukkit.getServer()).getServer();

        ResourceKey<net.minecraft.world.level.World> key =
                ResourceKey.a(ResourceKey.a(new MinecraftKey("minecraft", "dimension")), new MinecraftKey(namespace, id));

        // MinecraftServer.getLevel(key).getWorld()
        return dedicatedServer.a(key).getWorld();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("biome").setExecutor(new BiomeCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
