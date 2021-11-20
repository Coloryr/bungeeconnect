package coloryr.bungeeconnect.Side.Velocity;

import coloryr.bungeeconnect.BungeeConnectVelocity;
import coloryr.bungeeconnect.Side.ISide;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SideVelocity implements ISide {
    @Override
    public void sendMessage(Object sender, String data) {
        CommandSource sender1 = (CommandSource) sender;
        sender1.sendMessage(Component.text(data));
    }

    @Override
    public boolean hasPermission(Object sender, String data) {
        CommandSource sender1 = (CommandSource) sender;
        return sender1.hasPermission(data);
    }

    @Override
    public List<String> getServers() {
        List<String> list = new ArrayList<>();
        for (RegisteredServer item : BungeeConnectVelocity.plugin.server.getAllServers()) {
            list.add(item.getServerInfo().getName().toLowerCase(Locale.ROOT));
        }
        return list;
    }
}
