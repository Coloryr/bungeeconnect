package Color_yr.BungeeConnect;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class command extends Command {
    public command() {
        super("bc");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("bc.admin")) {
            BungeeConnect.reloadConfig();
            sender.sendMessage(new TextComponent("§6[BungeeConnect]已重读配置文件"));
            return;
        } else if (args[0].equalsIgnoreCase("bind")) {
            if (args.length > 1) {
                Map<String, ServerInfo> Server = ProxyServer.getInstance().getServers();
                Collection<ServerInfo> values = Server.values();
                Iterator<ServerInfo> iterator = values.iterator();
                while (iterator.hasNext()) {
                    ServerInfo server = iterator.next();
                    String server_name = server.getName();
                    if (server_name.equalsIgnoreCase(args[1])) {
                        Event.bind.put(sender.getName(), server_name);
                        sender.sendMessage(new TextComponent("§6[BungeeConnect]你已绑定服务器" + server_name + "在下次进服时直接进入该服。"));
                        return;
                    }
                }
                sender.sendMessage(new TextComponent("§6[BungeeConnect]未找到服务器" + args[1]));
                return;
            } else {
                sender.sendMessage(new TextComponent("§6[BungeeConnect]请输入你要绑定的服务器"));
            }
        } else if (args[0].equalsIgnoreCase("unbind")) {
            if (Event.bind.containsKey(sender.getName()) == false) {
                sender.sendMessage(new TextComponent("§6[BungeeConnect]你没有绑定服务器"));
            } else {
                Event.bind.remove(sender.getName());
                sender.sendMessage(new TextComponent("§6[BungeeConnect]已取消绑定"));
            }
        }
        return;
    }
}