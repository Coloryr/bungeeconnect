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

public class Event implements Listener {
    static Map<String, String> players = new HashMap<String, String>();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        int Vision = event.getPlayer().getPendingConnection().getVersion();
        ServerInfo Toserver = null;
        if (Vision == 340) {
            BungeeConnect.log.info(players.toString());
            if(players.size() == 0)
            {
                Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
            }
            else {
                String Formserver = players.get(event.getPlayer().getDisplayName());
                if (BungeeConnect.Server1122A.equalsIgnoreCase(Formserver)) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122B);
                } else if (BungeeConnect.Server1122B.equalsIgnoreCase(Formserver)) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122C);
                } else if (BungeeConnect.Server1122C.equalsIgnoreCase(Formserver)) {
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                }else{
                    Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1122A);
                }
            }
        } else if (Vision == 5) {
            Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1710);
        } else if (Vision == 404) {
            Toserver = ProxyServer.getInstance().getServerInfo(BungeeConnect.Server1132);
        }

        if (Toserver != null) {
            BungeeConnect.log.info("[BungeeConnect]将玩家送至服务器" + Toserver.getName());
            event.getPlayer().connect(Toserver);
        }
    }

    @EventHandler
    public void onServerKickEvent(ServerKickEvent event) {
        BungeeConnect.log.info("[BungeeConnect]玩家" + event.getPlayer().getDisplayName() + "从" + event.getKickedFrom().getName() + "踢出");
        if (!event.getKickedFrom().getName().equals(BungeeConnect.Servers)) {
            players.put(event.getPlayer().getDisplayName(), event.getKickedFrom().getName());
            BungeeConnect.log.info("[BungeeConnect]设置玩家" + event.getPlayer().getDisplayName() + "被踢出服务器" + event.getKickedFrom().getName());
        }
    }
}
