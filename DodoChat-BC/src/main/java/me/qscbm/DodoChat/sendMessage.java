package me.qscbm.DodoChat;

import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.MessageEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;


class sendMessage implements Listener {
    @EventHandler
    public void onMessageEvent(MessageEvent event) {
        String Message = config.getConfig().getString("settings.SendDodoMessage.format");
        String message = event.getMessageBody().getString("content");
        String nick = event.getMemberNickName();
        String id = event.getChannelId();
        // 判断消息是否来自指定频道号码
        if(id.equalsIgnoreCase(config.getConfig().getString("settings.SendDodoMessage.channelId"))) {
            for (ProxiedPlayer proxy : ProxyServer.getInstance().getPlayers()) {
                proxy.sendMessage(new TextComponent(DodoChat.parsePlaceholders(Message, null, message, nick)));
            }
        }
    }

}