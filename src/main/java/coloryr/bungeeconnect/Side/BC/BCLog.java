package coloryr.bungeeconnect.Side.BC;

import coloryr.bungeeconnect.BungeeConnectBC;
import coloryr.bungeeconnect.Side.ILog;

public class BCLog implements ILog {
    @Override
    public void info(String data) {
        BungeeConnectBC.log.info(data);
    }

    @Override
    public void warning(String data) {
        BungeeConnectBC.log.warning(data);
    }
}
