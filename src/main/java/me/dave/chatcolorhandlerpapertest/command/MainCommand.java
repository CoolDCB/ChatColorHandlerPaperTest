package me.dave.chatcolorhandlerpapertest.command;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.chatcolorhandler.ModernChatColorHandler;
import me.dave.chatcolorhandlerpapertest.ChatColorHandlerPaperTest;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCommand extends Command {

    public MainCommand() {
        super("chatcolorhandlerpapertest");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (args.length == 1 && args[0].equals("version")) {
            ChatColorHandler.sendMessage(sender, "&#a8e1ffYou are currently running ChatColorHandlerPaperTest version &#58b1e0" + ChatColorHandlerPaperTest.getInstance().getDescription().getVersion());
            return true;
        } else if (args.length >= 1 && args[0].equals("parse")) {
            Audience audience = Audience.audience(sender);

            Component message;
            if (sender instanceof Player player) {
                message = ModernChatColorHandler.translateAlternateColorCodes(String.join(" ", Arrays.copyOfRange(args, 1, args.length)), player);
            } else {
                message = ModernChatColorHandler.translateAlternateColorCodes(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            }

            audience.sendMessage(message);
            return true;
        }

        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        List<String> tabComplete = new ArrayList<>();
        List<String> wordCompletion = new ArrayList<>();
        boolean wordCompletionSuccess = false;

        if (args.length == 1) {
            tabComplete.add("version");
            if (sender.hasPermission("chatcolorhandlertest.parse")) {
                tabComplete.add("parse");
            }
        }

        for (String currTab : tabComplete) {
            int currArg = args.length - 1;
            if (currTab.startsWith(args[currArg])) {
                wordCompletion.add(currTab);
                wordCompletionSuccess = true;
            }
        }

        return wordCompletionSuccess ? wordCompletion : tabComplete;
    }
}
