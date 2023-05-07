package me.qscbm.roleGrant;

import io.github.minecraftchampions.dodoopenjava.api.v2.RoleApi;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.MessageReactionEvent;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;

import java.io.IOException;
import java.util.Objects;

public class Main implements Listener {//实现Listener不能少
    public static void main(String[] args) {
        EventManage.registerEvents(new Main(), BaseUtil.Authorization("12345678", "AbC0Def1GHI.JKLmn234.5o-6pq7r8sTUvwxyZ9ABCDEfGH0ijKLmM-opq1R2stA"));
        //注册事件监听器
    }

    @EventHandler //注解不能少
    public void onMessageEvent(MessageReactionEvent e) {//MessageReactionEvent就是消息表情反应事件
        String Emoji = e.getReactionEmojiId();//获取Emoji的ID
        String islandId = e.getIslandSourceId();//获取群号
        String id = e.getDodoSourceId();//获取Dodo号
        // 判断触发事件是否发生在指定messageId上（表情反应的那条消息的ID）
        if (Objects.equals(e.getMessageId(), "消息ID")) {
            // 判断是否为指定的表情列表
            switch (Emoji) {
                //表情ID是否为“48”（就是那个0，方框中里面有个0的那个）
                case "48" -> {
                    try {
                        RoleApi.addRoleMember("按照文档填写", islandId, id, "身份组1的ID");
                    } catch (IOException en) {
                        throw new RuntimeException(en);
                    }
                }
                //表情ID是否为“49”（就是那个1，方框中里面有个1的那个）
                case "49" -> {
                    try {
                        RoleApi.addRoleMember("按照文档填写", islandId, id, "身份组2的ID");
                    } catch (IOException en) {
                        throw new RuntimeException(en);
                    }
                }
                //如果都不是，则不做处理
                default -> {
                }
            }
        }
    }
}