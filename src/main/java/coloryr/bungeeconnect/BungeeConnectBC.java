package coloryr.bungeeconnect;

import coloryr.bungeeconnect.Side.BC.BCLog;
import coloryr.bungeeconnect.Side.BC.CommandBC;
import coloryr.bungeeconnect.Side.BC.EventBC;
import coloryr.bungeeconnect.Side.BC.SideBC;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Logger;

public class BungeeConnectBC extends Plugin {

    public static Logger log = ProxyServer.getInstance().getLogger();

    @Override
    public void onEnable() {
        BungeeConnect.log = new BCLog();
        BungeeConnect.side = new SideBC();
        log.info("§d[BungeeConnect]§e正在启动，感谢使用，本插件交流群：571239090");
        new BungeeConnect().init(getDataFolder());
        BungeeConnect.loadconfig();
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandBC());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new EventBC());
        log.info("§d[BungeeConnect]§e已启动-" + BungeeConnect.Version);
    }

    @Override
    public void onDisable() {
        log.info("§d[BungeeConnect]§e已停止，感谢使用");
    }
}
