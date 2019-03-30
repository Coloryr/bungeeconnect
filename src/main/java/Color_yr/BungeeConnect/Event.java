package Color_yr.BungeeConnect;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Random;

import static Color_yr.BungeeConnect.BungeeConnect.Servers;

public class Event implements Listener {
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        int Vision = event.getPlayer().getPendingConnection().getVersion();
        if (Vision == 340) {
            Random randomno = new Random();
            boolean value = randomno.nextBoolean();
            ServerInfo Toserver;
            if (value == true) {
                Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
            } else {
                Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122B);
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
        ServerInfo Toserver = ProxyServer.getInstance().getServerInfo(Servers);
        if (Toserver.equals(event.getKickedFrom())) {
            return;
        }
        if (BungeeConnect.HaveNull == true && event.getCancelServer() == BungeeConnect.Null)
            event.setCancelled(true);
        else
            BungeeConnect.log.info("[BungeeConnect]将玩家送至默认服务器");
        event.setCancelled(true);
        event.getPlayer().connect(Toserver);
    }
}
