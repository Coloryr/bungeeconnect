package coloryr.bungeeconnect;

import java.util.HashMap;
import java.util.Map;

public class ConfigObj {
    public String version;
    public Map<String, String> bind;
    public String lobby;

    public boolean check() {
        boolean res = false;
        if (bind == null) {
            bind = new HashMap<>();
            res = true;
        }
        return res;
    }
}
