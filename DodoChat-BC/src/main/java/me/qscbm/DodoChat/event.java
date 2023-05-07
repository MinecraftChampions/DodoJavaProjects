package me.qscbm.DodoChat;

import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class event implements Listener {
    // 监听Mc服务器的玩家加入服务器事件
    @EventHandler
    public void PlayerJoin(PostLoginEvent event) throws IOException {
        if (!DodoChat.EnableJoinMessage) {
            return;
        }
        String message = config.getConfig().getString("settings.JoinMessage.format"); //获取配置文件的配置项
        String channelId = config.getConfig().getString("settings.JoinMessage.channelId");
        //调用接口
        ChannelMessageApi.sendTextMessage(DodoChat.Authorization, channelId, DodoChat.parsePlaceholders(message, event.getPlayer(), null, null));
    }

    // 监听Mc服务器的玩家离开服务器事件
    @EventHandler
    public void PlayerQuit(PlayerDisconnectEvent event) throws IOException {
        if (!DodoChat.EnableLeaveMessage) {
            return;
        }
        String message = config.getConfig().getString("settings.LeaveMessage.format");
        String channelId = config.getConfig().getString("settings.LeaveMessage.channelId");
        ChannelMessageApi.sendTextMessage(DodoChat.Authorization, channelId, DodoChat.parsePlaceholders(message, event.getPlayer(), null, null));
    }

    // 监听Mc服务器的玩家切换服务器事件
    @EventHandler
    public void PlayerChangeServer(ServerSwitchEvent event) throws IOException {
        if (!DodoChat.EnableChanceMessage) {
            return;
        }
        String server = event.getPlayer().getServer().getInfo().getName();
        String message = config.getConfig().getString("settings.ChanceServer.format");
        String channelId = config.getConfig().getString("settings.ChanceServer.channelId");
        if(config.getConfig().getStringList("settings.ChanceServer.servers").contains(server)) {
            ChannelMessageApi.sendTextMessage(DodoChat.Authorization, channelId, DodoChat.parsePlaceholders(message, event.getPlayer(), server, null));
        }
    }

    // 监听Mc服务器的玩家发送消息事件
    @EventHandler
    public void PlayerSendMessage(ChatEvent event) throws IOException {
        if (!DodoChat.EnableServerMessage) {
            return;
        }
        if (!event.isCommand()) {
            String message = config.getConfig().getString("settings.SendServerMessage.format");
            String channelId = config.getConfig().getString("settings.SendServerMessage.channelId");
            ChannelMessageApi.sendTextMessage(DodoChat.Authorization, channelId, DodoChat.parsePlaceholders(message, (ProxiedPlayer) event.getSender(), event.getMessage(), null));
        };
    }
}
