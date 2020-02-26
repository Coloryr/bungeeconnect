package Color_yr.BungeeConnect;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class command extends Command {
    public command() {
        super("bc");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("bc.admin")) {
            new BungeeConnect().reloadConfig();
            sender.sendMessage(new TextComponent("§6[BungeeConnect]已重读配置文件"));
        } else if (args[0].equalsIgnoreCase("bind")) {
            if (args.length > 1) {
                Iterator<ServerInfo> iterator = ProxyServer.getInstance().getServers().values().iterator();
                while (iterator.hasNext()) {
                    ServerInfo server = iterator.next();
                    String ServerName = server.getName();
                    if (ServerName.equalsIgnoreCase(args[1])) {
                        Event.bind.put(sender.getName(), ServerName);
                        BungeeConnect.config.set("bind", Event.bind);
                        sender.sendMessage(new TextComponent("§6[BungeeConnect]" + sender.getName()
                                + "你已绑定服务器" + ServerName + "在下次进服时直接进入该服。"));
                        try {
                            ConfigurationProvider.getProvider(YamlConfiguration.class).save(BungeeConnect.config, BungeeConnect.FileName);
                        } catch (Exception e) {
                            BungeeConnect.log.warning(e.getMessage());
                        }
                        return;
                    }
                }
                sender.sendMessage(new TextComponent("§6[BungeeConnect]未找到服务器" + args[1]));
            } else {
                sender.sendMessage(new TextComponent("§6[BungeeConnect]请输入你要绑定的服务器"));
            }
        } else if (args[0].equalsIgnoreCase("unbind")) {
            if (!Event.bind.containsKey(sender.getName())) {
                sender.sendMessage(new TextComponent("§6[BungeeConnect]" + sender.getName() + "你没有绑定服务器"));
            } else {
                Event.bind.remove(sender.getName());
                sender.sendMessage(new TextComponent("§6[BungeeConnect]" + sender.getName() + "你已取消绑定"));
            }
        }
    }
}