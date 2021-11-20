package coloryr.bungeeconnect;

import coloryr.bungeeconnect.Side.Velocity.CommandVelocity;
import coloryr.bungeeconnect.Side.Velocity.EventVelocity;
import coloryr.bungeeconnect.Side.Velocity.SideVelocity;
import coloryr.bungeeconnect.Side.Velocity.VelocityLog;
import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(id = "bungeeconnect", name = "BungeeConnect", version = BungeeConnect.Version,
        url = "https://github.com/HeartAge/bungeeconnect", description = "绑定子服" +
        "", authors = {"Color_yr"})
public class BungeeConnectVelocity {
    public static BungeeConnectVelocity plugin;
    public final ProxyServer server;
    public final Path dataDirectory;
    public final Logger logger;

    @Inject
    public BungeeConnectVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        plugin = this;
        logger.info("§d[BungeeConnect]§e正在启动，感谢使用，本插件交流群：571239090");
        BungeeConnect.log = new VelocityLog();
        BungeeConnect.side = new SideVelocity();
        new BungeeConnect().init(dataDirectory.toFile());
        BungeeConnect.loadconfig();
        CommandMeta meta = server.getCommandManager().metaBuilder("bc")
                .build();

        server.getCommandManager().register(meta, new CommandVelocity());
        server.getEventManager().register(this, new EventVelocity());
        logger.info("§d[BungeeConnect]§e已启动-" + BungeeConnect.Version);
    }

    @Subscribe
    public void onStop(ProxyShutdownEvent event) {
        logger.info("§d[BungeeConnect]§e已停止，感谢使用");
    }
}
