package Color_yr.BungeeConnect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.logging.Logger;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeConnect extends Plugin {
    public static String Version = "1.0.0";

    public static Configuration config;
    public static File FileName;

    public static String lobby;

    public static Logger log = ProxyServer.getInstance().getLogger();

    public void loadconfig() {
        log.info("[BungeeConnect]你的配置文件版本是：" + config.getString("Version"));
        lobby = config.getString("lobby", "heartage0");

        Configuration a = config.getSection("bind");
        Collection<String> b = a.getKeys();
        for (String c : b) {
            String d = a.getString(c);
            Event.bind.put(c, d);
            log.info("玩家" + c + "绑定服务器：" + d);
        }
    }

    public void reloadConfig() {
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(FileName);
            loadconfig();
        } catch (Exception arg0) {
            log.warning("§6[BungeeConnect]配置文件读取错误：" + arg0.getMessage());
        }
    }

    public void setConfig() {
        FileName = new File(getDataFolder(), "config.yml");
        logs.file = new File(getDataFolder(), "logs.log");
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        if (!FileName.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, FileName.toPath());
            } catch (IOException e) {
                log.warning("§6[BungeeConnect]配置文件创建错误：" + e.getMessage());
            }
        }
        try {
            if (!logs.file.exists()) {
                logs.file.createNewFile();
            }
        } catch (IOException e) {
            log.warning("§d[BungeeConnect]§c日志文件错误：" + e);
        }
        reloadConfig();
    }

    @Override
    public void onEnable() {
        log.info("§6[BungeeConnect]正在启动，插件交流群：571239090欢迎加入");
        setConfig();
        ProxyServer.getInstance().getPluginManager().registerListener(this, new Event());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new command());

        log.info("§6[BungeeConnect]已启动，你运行的版本是：" + Version);
    }

    @Override
    public void onDisable() {
        log.info("§6[BungeeConnect]已停止运行，欢迎再次使用");
    }
}
