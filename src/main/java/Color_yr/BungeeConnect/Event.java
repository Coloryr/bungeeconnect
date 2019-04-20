package Color_yr.BungeeConnect;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

public class Event implements Listener {
    static Map<String, String> players = new HashMap<String, String>();
    public static Map<String, String> bind = new HashMap<String,String>();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        int Vision = event.getPlayer().getPendingConnection().getVersion();
        ServerInfo Toserver = null;
        switch (Vision) {
            case 340:
                String Formserver = bind.get(event.getPlayer().getDisplayName());
                if (Formserver != null) {
                    Toserver = ProxyServer.getInstance().getServerInfo(Formserver);
                } else if (players.size() == 0) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                } else {
                    Formserver = players.get(event.getPlayer().getDisplayName());
                    if (BungeeConnect.Server1122A.equalsIgnoreCase(Formserver)) {
                        Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122B);
                    } else if (BungeeConnect.Server1122B.equalsIgnoreCase(Formserver)) {
                        Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122C);
                    } else if (BungeeConnect.Server1122C.equalsIgnoreCase(Formserver)) {
                        Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                    } else {
                        Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                    }
                }
                break;
            case 5:
                //Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1710);
                break;
            case 404:
                Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1132);
                break;
            case 47:
                //Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server189);
                break;
            default:
                logs logs = new logs();
                logs.log_write(event.toString() + "版本：" + Vision);
                break;
        }
        if (Toserver != null) {
            event.getPlayer().sendMessage(new TextComponent("[BungeeConnect]已将你传送至" + Toserver.getName()));
            event.getPlayer().connect(Toserver);
        }
    }

    @EventHandler
    public void onServerKickEvent(ServerKickEvent event) {
        if (!event.getKickedFrom().getName().equals(BungeeConnect.Servers)) {
            players.put(event.getPlayer().getDisplayName(), event.getKickedFrom().getName());
        }
        event.getPlayer().sendMessage(new TextComponent("[BungeeConnect]无法进入服务器" + event.getKickedFrom().getName()));
    }
}
