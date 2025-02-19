package me.elec.telephoneGame;

import de.maxhenkel.voicechat.api.*;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class VoiceChatManager implements VoicechatPlugin {


    public VoiceChatManager() {

    }

    public void createVoiceGroup(Player player1, Player player2) {
        if (voicechatApi == null) {
            player1.sendMessage("§cVoice chat plugin not found.");
            player2.sendMessage("§cVoice chat plugin not found.");
            return;
        }

        VoicechatConnection connection1 = voicechatApi.getConnectionOf(player1.getUniqueId()).orElse(null);
        VoicechatConnection connection2 = voicechatApi.getConnectionOf(player2.getUniqueId()).orElse(null);

        if (connection1 == null || connection2 == null) {
            player1.sendMessage("§cOne of you is not connected to voice chat.");
            player2.sendMessage("§cOne of you is not connected to voice chat.");
            return;
        }

        // Create a unique group ID
        String groupName = "Call-" + UUID.randomUUID().toString().substring(0, 6);
        Group group = voicechatApi.groupBuilder()
                .setName(groupName)
                .setPersistent(false) // Group is temporary
                .setPassword(null) // No password required
                .build();

        if (group == null) {
            player1.sendMessage("§cFailed to create a voice chat group.");
            player2.sendMessage("§cFailed to create a voice chat group.");
            return;
        }

        // Add both players to the group
        connection1.setGroup(group);
        connection2.setGroup(group);

        player1.sendMessage("§aYou have been added to a voice chat group with " + player2.getName() + "!");
        player2.sendMessage("§aYou have been added to a voice chat group with " + player1.getName() + "!");
    }

    @Override
    public String getPluginId() {
        return plugin.getPluginId();
    }

    @Override
    public void initialize(VoicechatApi api) {

    }

    @Override
    public void registerEvents(EventRegistration registration) {

    }
}

