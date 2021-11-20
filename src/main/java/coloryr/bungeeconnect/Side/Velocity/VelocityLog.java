package coloryr.bungeeconnect.Side.Velocity;


import coloryr.bungeeconnect.BungeeConnectVelocity;
import coloryr.bungeeconnect.Side.ILog;

public class VelocityLog implements ILog {
    @Override
    public void info(String data) {
        BungeeConnectVelocity.plugin.logger.info(data);
    }

    @Override
    public void warning(String data) {
        BungeeConnectVelocity.plugin.logger.warn(data);
    }
}
