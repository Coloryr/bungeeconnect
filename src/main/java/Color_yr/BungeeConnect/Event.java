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
        ServerInfo Toserver = null;
        if (Vision == 340) {
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
            }
        } else if (Vision == 5) {
            Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1710);
        } else if (Vision == 404) {
            Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1132);
        }
        BungeeConnect.log.info("[BungeeConnect]将玩家送至1.12.2服务器");
        if (Toserver != null) {
            event.getPlayer().connect(Toserver);
        }
        logs logs = new logs();
        logs.log_write("" + Vision);
    }
    @EventHandler
    public void onServerKickEvent(ServerKickEvent event) {
        players.put(event.getPlayer().toString(), event.getKickedFrom());
        BungeeConnect.log.info("[BungeeConnect]从" + event.getKickedFrom().getName() + "踢出");
    }
}
