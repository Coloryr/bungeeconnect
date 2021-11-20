package coloryr.bungeeconnect.Side;

import java.util.List;

public interface ISide {
    void sendMessage(Object sender, String data);

    boolean hasPermission(Object sender, String data);

    List<String> getServers();
}
