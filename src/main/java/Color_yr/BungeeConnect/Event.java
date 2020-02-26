package Color_yr.BungeeConnect;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class Event implements Listener {
    public static Map<String, String> bind = new HashMap<>();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        if(bind.containsKey(event.getPlayer().getName())) {
            String ServerName = bind.get(event.getPlayer().getName());
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
        if (!Event.bind.containsKey(event.getPlayer().getName())) {
            event.getPlayer().connect(ProxyServer.getInstance().getServerInfo(BungeeConnect.lobby));
            event.getPlayer().setReconnectServer(ProxyServer.getInstance().getServerInfo(BungeeConnect.lobby));
        }
    }
}
