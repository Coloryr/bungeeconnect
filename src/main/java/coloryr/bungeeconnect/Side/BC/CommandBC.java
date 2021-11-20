package coloryr.bungeeconnect.Side.BC;

import coloryr.bungeeconnect.CommandEX;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CommandBC extends Command {

    public CommandBC() {
        super("bc");
    }

    public void execute(CommandSender sender, String[] args) {
        CommandEX.EX(sender, args, sender.getName());
    }
}
