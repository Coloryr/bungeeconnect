package coloryr.bungeeconnect.Side.BC;

import coloryr.bungeeconnect.Side.ISide;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SideBC implements ISide {
    @Override
    public void sendMessage(Object sender, String data) {
        CommandSender sender1 = (CommandSender) sender;
        sender1.sendMessage(new TextComponent(data));
    }

    @Override
    public boolean hasPermission(Object sender, String data) {
        CommandSender sender1 = (CommandSender) sender;
        return sender1.hasPermission(data);
    }

    @Override
    public List<String> getServers() {
        List<String> list = new ArrayList<>();
        for (ServerInfo item : ProxyServer.getInstance().getServers().values()) {
            list.add(item.getName().toLowerCase(Locale.ROOT));
        }
        return list;
    }
}
