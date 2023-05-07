package me.qscbm.DodoChat;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class config {
    public static File configFile;

    static Configuration config;

    //加载配置文件
    //BC的配置文件对对象的操作和我们是一样的，但是初始化的其他的都不一样
    //几乎一样的是bukkit
    public static void loadConfig() throws IOException {
        if (!DodoChat.getInstance().getDataFolder().exists()) {
            DodoChat.getInstance().getDataFolder().mkdir();
        }

        configFile = new File(DodoChat.getInstance().getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            FileOutputStream outputStream = new FileOutputStream(configFile);
            InputStream in = DodoChat.getInstance().getResourceAsStream("config.yml");
            in.transferTo(outputStream);
        }
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DodoChat.getInstance().getDataFolder(), "config.yml"));
    }

    public static Configuration getConfig() {
        return config;
    }
}