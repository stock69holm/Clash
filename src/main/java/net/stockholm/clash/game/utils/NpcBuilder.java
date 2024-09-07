package net.stockholm.clash.game.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.stockholm.clash.Main;
import net.stockholm.clash.game.npc.Npc;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

public class NpcBuilder {
    public static void create(Player player, String name, double x, double y, double z, String skinName) {

        String[] skin;

        try {
            skin = getSkinData(skinName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ServerPlayer ePlayer = ((CraftPlayer) player).getHandle();

        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel level = ((CraftWorld) Objects.requireNonNull(Bukkit.getWorld("world"))).getHandle();

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        gameProfile.getProperties().put("textures", new Property("textures", Objects.requireNonNull(skin)[0], skin[1]));
        ServerPlayer npc = new ServerPlayer(server, level, gameProfile);

        Connection connection = new Connection(PacketFlow.CLIENTBOUND);
        npc.connection = new ServerGamePacketListenerImpl(server, connection, npc);

        npc.setPos(x,y,z);
        level.addNewPlayer(npc);

        ePlayer.connection.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
        ePlayer.connection.send(new ClientboundAddEntityPacket(npc));
        new BukkitRunnable() {
            @Override
            public void run() {
                ePlayer.connection.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, npc));
            }
        }.runTaskLater(Main.getInstance(), 5L);

    }

    public static String[] getSkinData(String playerName) throws Exception {
        String uuid = getUUID(playerName);
        if (uuid == null) {
            return null;
        }
        URL url = new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", uuid));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
        JsonElement properties = response.getAsJsonArray("properties").get(0);
        JsonObject property = properties.getAsJsonObject();
        String encoded = property.get("value").getAsString();
        String signature = property.get("signature").getAsString();
        return new String[]{encoded, signature};
    }

    private static String getUUID(String playerName) throws Exception {
        URL url = new URL(String.format("https://api.mojang.com/users/profiles/minecraft/%s", playerName));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
        return response.has("id") ? response.get("id").getAsString() : null;
    }

}
