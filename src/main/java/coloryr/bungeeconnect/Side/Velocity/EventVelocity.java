package coloryr.bungeeconnect.Side.Velocity;

import coloryr.bungeeconnect.BungeeConnect;
import coloryr.bungeeconnect.BungeeConnectVelocity;
import coloryr.bungeeconnect.Side.BC.EventBC;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EventVelocity {
    @Subscribe
    public void onPostLoginEvent(final PostLoginEvent event) {
        Player player = event.getPlayer();
        if (BungeeConnect.config.bind.containsKey(player.getUsername())) {
            String ServerName = BungeeConnect.config.bind.get(player.getUsername());
            if (ServerName != null) {
                Optional<RegisteredServer> toServer = BungeeConnectVelocity.plugin.server.getServer(ServerName);
                if (toServer.isPresent()) {
                    RegisteredServer server = toServer.get();
                    event.getPlayer().sendMessage(Component.text("§6[BungeeConnect]已将你传送至" + server.getServerInfo().getName()));
                    event.getPlayer().createConnectionRequest(server).connect();
                }
            }
        } else {
            event.getPlayer().sendMessage(Component.text("§6[BungeeConnect]你还没有绑定服务器，请输入/bc bind 服务器 来绑定服务器吧"));
        }
    }

    @Subscribe
    public void onPlayerDisconnect(DisconnectEvent event) {
        if (!BungeeConnect.config.bind.containsKey(event.getPlayer().getUsername())) {
            Optional<RegisteredServer> toServer = BungeeConnectVelocity.plugin.server.getServer(BungeeConnect.config.lobby);
            if (toServer.isPresent()) {
                RegisteredServer server = toServer.get();
                event.getPlayer().createConnectionRequest(server).connect();
            }
        }
    }
}
