package Color_yr.BungeeConnect;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class command extends Command {
    public command() {
        super("bc");
    }
    public void execute(CommandSender sender, String[] args) {
        if(args[0].equalsIgnoreCase("relaoad"))
        {
            if(sender.hasPermission("bc.admin"))
            {
                BungeeConnect.reloadConfig();
                sender.sendMessage(new TextComponent("&6[BungeeConnect]已重读配置文件"));
                sender.sendMessage(new TextComponent("&6[BungeeConnect]1.7.10传送至"+ BungeeConnect.Server1710));
                sender.sendMessage(new TextComponent("&6[BungeeConnect]1.12.2传送至"+ BungeeConnect.Server1122));
                sender.sendMessage(new TextComponent("&6[BungeeConnect]1.13.2传送至"+ BungeeConnect.Server1132));
                sender.sendMessage(new TextComponent("&6[BungeeConnect]默认传送至"+ BungeeConnect.Servers));
                return;
            }
        }
        return;
    }
}
