package me.qscbm.DodoChat;

import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public final class DodoChat extends Plugin {
    public static Boolean EnableDodoMessage,EnableServerMessage,EnableJoinMessage,EnableLeaveMessage,EnableChanceMessage;
    public static String Authorization;
    private static DodoChat instance;

    public static String parsePlaceholders(String initialString, ProxiedPlayer player, String param,String sender) {
        String tempString = initialString;
        if (param != null) {
            tempString = tempString.replaceAll("%message%", param);
            tempString = tempString.replaceAll("%server%", param);
        }
        if (player != null) {
            tempString = tempString.replaceAll("%player%", player.getDisplayName());
        }
        if (sender != null) {
            tempString = tempString.replaceAll("%sender%", sender);
        }

        return tempString;
    }

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerListener(this, new event()); //注册mc事件监听器
        getLogger().info("DodoChat已加载");
        try {
            config.loadConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Authorization = BaseUtil.Authorization(config.getConfig().getString("settings.botClientId"), config.getConfig().getString("settings.botToken"));//拼接
        EnableDodoMessage = config.getConfig().getBoolean("settings.SendDodoMessage.Enable");//获取配置项
        EnableServerMessage = config.getConfig().getBoolean("settings.SendServerMessage.Enable");
        EnableChanceMessage = config.getConfig().getBoolean("settings.ChanceServer.Enable");
        EnableJoinMessage = config.getConfig().getBoolean("settings.JoinMessage.Enable");
        EnableLeaveMessage = config.getConfig().getBoolean("settings.LeaveMessage.Enable");
        if (EnableDodoMessage) {
            EventManage.registerEvents(new sendMessage(),Authorization); //注册DodoOpenJava事件
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("DodoChat已卸载");
    }



    public static DodoChat getInstance() {
        return instance;
    }
}
