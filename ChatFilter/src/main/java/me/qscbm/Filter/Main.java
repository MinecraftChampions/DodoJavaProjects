package me.qscbm.Filter;

import io.github.minecraftchampions.dodoopenjava.Bot;
import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.WebSocketEventTrigger;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage.MessageEvent;
import io.github.minecraftchampions.dodoopenjava.utils.BaseUtil;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main implements Listener {
    public static Bot bot;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入clientId:");
        String token = "";
        String clientId = "";
        if (scanner.hasNext()) {
            clientId = scanner.next();
        }
        System.out.println("请输入token:");
        if (scanner.hasNext()) {
            token = scanner.next();
        }

        bot = new Bot(clientId, token);
        bot.registerListener(new Main());
    }

    @EventHandler
    public void event(MessageEvent event) throws IOException {
        // 这里没做频道号和群号的判断和消息类型，如果需要自行添加
        String messageId = event.getMessageId();
        String id = event.getDodoSourceId();
        String message = event.getMessageBody().getString("content");
        String channelId = event.getChannelId();
        String islandId = event.getIslandSourceId();
        if (BaseUtil.hasSensitiveWord(message)) {
            bot.getApi().V2.getChannelMessageApi().sendAtTextMessage(channelId, id, "你已触发违规词，处罚禁言10分钟，并撤回违规消息");
            bot.getApi().V2.getChannelMessageApi().withdrawChannelMessageWithReason(channelId, messageId);
            bot.getApi().V2.getMemberApi().addMemberReasonMute(islandId, id, 60 * 10, "违规");
        }
    }
}