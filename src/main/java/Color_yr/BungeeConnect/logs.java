package Color_yr.BungeeConnect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class logs {
    public static File file;

    public void log_write(String text) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            Date date = new Date();
            String year = String.format("%tF", date);
            String time = String.format("%tT", date);
            String write = "[" + year + "]" + "[" + time + "]" + text;
            PrintWriter pw = new PrintWriter(fw);
            pw.println(write);
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
        } catch (FileNotFoundException e) {
            BungeeConnect.log.warning("§d[BungeeConnect]§c日志文件写入失败" + e);
        } catch (IOException e) {
            BungeeConnect.log.warning("§d[BungeeConnect]§c日志文件写入失败" + e);
        }
    }
}
