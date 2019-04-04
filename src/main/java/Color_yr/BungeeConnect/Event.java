package Color_yr.BungeeConnect;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static Color_yr.BungeeConnect.BungeeConnect.Servers;

public class Event implements Listener {
    static Map<String, ServerInfo> players = new HashMap<String, ServerInfo>();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        int Vision = event.getPlayer().getPendingConnection().getVersion();
        if (Vision == 340) {
            ServerInfo Toserver;
            if (players.size() == 0) {
                Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
            } else {
                String Formserver = players.get(event.getPlayer().getDisplayName()).getName();
                if (BungeeConnect.Server1122A == Formserver) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122B);
                } else if (BungeeConnect.Server1122B == Formserver) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122C);
                } else if (BungeeConnect.Server1122C == Formserver) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                } else {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                }
                BungeeConnect.log.info("[BungeeConnect]玩家进入下一个1.12.2服务器");
            }
            BungeeConnect.log.info("[BungeeConnect]将玩家送至1.12.2服务器");
            event.getPlayer().connect(Toserver);
            return;
        } else if (Vision == 5) {
            ServerInfo Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1710);
            BungeeConnect.log.info("[BungeeConnect]将玩家送至1.7.10服务器");
            event.getPlayer().connect(Toserver);
            return;
        } else if (Vision == 404) {
            ServerInfo Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1132);
            BungeeConnect.log.info("[BungeeConnect]将玩家送至1.13.2服务器");
            event.getPlayer().connect(Toserver);
            return;
        }
        logs logs = new logs();
        logs.log_write("" + Vision);
    }

    @EventHandler
    public void onServerKickEvent(ServerKickEvent event) {
        if (event.getKickedFrom().toString() != BungeeConnect.Server1122A)
            return;
        else if (event.getKickedFrom().toString() != BungeeConnect.Server1122B)
            return;
        else if (event.getKickedFrom().toString() != BungeeConnect.Server1122C)
            return;
        else {
            players.put(event.getPlayer().toString(), event.getKickedFrom());
            BungeeConnect.log.info("[BungeeConnect]从" + event.getKickedFrom().getName() + "踢出");
        }

    }
}
