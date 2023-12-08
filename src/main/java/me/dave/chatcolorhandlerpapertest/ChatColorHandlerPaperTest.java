package me.dave.chatcolorhandlerpapertest;

import me.dave.chatcolorhandlerpapertest.command.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatColorHandlerPaperTest extends JavaPlugin {
    private static ChatColorHandlerPaperTest plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getCommandMap().register("chatcolorhandlerpapertest", new MainCommand());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static ChatColorHandlerPaperTest getInstance() {
        return plugin;
    }
}
