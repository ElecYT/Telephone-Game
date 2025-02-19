package me.elec.telephoneGame;

import me.elec.telephoneGame.commands.CallCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class TelephoneGame extends JavaPlugin {
    private static TelephoneGame plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        BukkitVoicechatService voicechatService = getServer().getServicesManager().load(BukkitVoicechatService.class);

        if (voicechatService != null) {
            VoicechatServerApi api = voicechatService.getApi();
            voiceChatManager = new VoiceChatManager(api);
        } else {
            getLogger().warning("Simple Voice Chat API not found. Voice features will be disabled.");
        }

        getCommand("call").setExecutor(new CallCommand(this, voiceChatManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
