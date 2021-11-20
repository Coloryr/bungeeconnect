package coloryr.bungeeconnect;

import java.util.List;
import java.util.Locale;

public class CommandEX {
    public static void EX(Object sender, String[] args, String name) {
        if (args.length == 0) {
            return;
        }
        if (args[0].equalsIgnoreCase("reload") && BungeeConnect.side.hasPermission(sender, "bc.admin")) {
            BungeeConnect.loadconfig();
            BungeeConnect.side.sendMessage(sender, "§d[BungeeConnect]§e已重读配置文件");
        } else if (args[0].equalsIgnoreCase("bind")) {
            if (args.length > 1) {

                List<String> list = BungeeConnect.side.getServers();
                String server = args[1].toLowerCase(Locale.ROOT);
                if (list.contains(server)) {
                    BungeeConnect.config.bind.put(name, server);
                    BungeeConnect.save();
                    BungeeConnect.side.sendMessage(sender, "§d[BungeeConnect]§e" + name
                            + "你已绑定服务器" + server + "在下次进服时直接进入该服。");
                    return;
                }
                BungeeConnect.side.sendMessage(sender, "§d[BungeeConnect]§e未找到服务器" + args[1]);
            } else {
                BungeeConnect.side.sendMessage(sender, "§d[BungeeConnect]§e请输入你要绑定的服务器");
            }
        } else if (args[0].equalsIgnoreCase("unbind")) {
            if (!BungeeConnect.config.bind.containsKey(name)) {
                BungeeConnect.side.sendMessage(sender, "§d[BungeeConnect]§e" + name + "你没有绑定服务器");
            } else {
                BungeeConnect.config.bind.remove(name);
                BungeeConnect.side.sendMessage(sender, "§d[BungeeConnect]§e" + name + "你已取消绑定");
            }
        }
    }
}
