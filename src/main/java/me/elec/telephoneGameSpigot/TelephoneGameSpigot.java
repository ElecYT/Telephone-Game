package me.elec.telephoneGameSpigot;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import me.elec.telephoneGameSpigot.commands.CallCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;


public final class TelephoneGameSpigot extends JavaPlugin{
    private static TelephoneGameSpigot plugin;

    @Nullable
    private VoiceChatManager voiceChatManager;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);

        if (service != null) {
            // Create one instance and register it
            VoiceChatManager voiceChatManager = new VoiceChatManager(this);
            service.registerPlugin(voiceChatManager);
        } else {
            getLogger().warning("Simple Voice Chat API not found. Voice features will be disabled.");
        }



        getCommand("call").setExecutor(new CallCommand(this, new VoiceChatManager(this)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
