package coloryr.bungeeconnect.Side.BC;

import coloryr.bungeeconnect.BungeeConnect;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class EventBC implements Listener {
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        if(BungeeConnect.config.bind.containsKey(event.getPlayer().getName())) {
            String ServerName = BungeeConnect.config.bind.get(event.getPlayer().getName());
            if (ServerName != null) {
                ServerInfo toServer = ProxyServer.getInstance().getServerInfo(ServerName);
                if (toServer != null) {
                    event.getPlayer().sendMessage(new TextComponent("§6[BungeeConnect]已将你传送至" + toServer.getName()));
                    event.getPlayer().connect(toServer);
                    event.getPlayer().setReconnectServer(toServer);
                }
            }
        } else {
            event.getPlayer().sendMessage(new TextComponent("§6[BungeeConnect]你还没有绑定服务器，请输入/bc bind 服务器 来绑定服务器吧"));
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        if (!BungeeConnect.config.bind.containsKey(event.getPlayer().getName())) {
            event.getPlayer().connect(ProxyServer.getInstance().getServerInfo(BungeeConnect.config.lobby));
            event.getPlayer().setReconnectServer(ProxyServer.getInstance().getServerInfo(BungeeConnect.config.lobby));
        }
    }
}
