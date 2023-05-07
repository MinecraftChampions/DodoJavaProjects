package me.qscbm.Filter;

import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import io.github.minecraftchampions.dodoopenjava.api.v2.MemberApi;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.MessageEvent;
import io.github.minecraftchampions.dodoopenjava.utils.NetUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Main implements Listener {
    static String a;

    public static void main(String[] args) throws IOException {
        a = NetUtil.sendGetRequest("https://mcchampions.github.io/database.json");//获得违禁词列表，将database.json换成status.json可以获取Dodo返回码的含义
        byte[] b = a.getBytes();
        a = new String(b,"GBK");
        EventManage.registerEvents(new Main(), "Authorization,请按照文档填写");//注册事件
    }

    public static boolean Test(String param) {
        boolean e = false;
        JSONArray word = new JSONObject(a).getJSONArray("words");
        for (int c = 0; c < word.length(); c++) {
            if (param ==  word.get(c)) e = true;
        }
        return e;
    }

    @EventHandler
    public void event(MessageEvent event) {
        // 这里没做频道号和群号的判断和消息类型，如果需要自行添加
        String messageid = event.getMessageBody().getString("messageId");
        String id = event.getDodoSourceId();
        String message = event.getMessageBody().getString("content");
        String channelId = event.getChannelId();
        String islandId = event.getIslandSourceId();
        if(Test(message)) {
            try {
                ChannelMessageApi.referencedMessage("按照文档里的填",channelId, "你已触发违规词，处罚禁言10分钟，并撤回违规消息",messageid);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                ChannelMessageApi.withdrawChannelMessageWithReason("按照文档里的填",channelId,messageid);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                MemberApi.addMemberReasonrMute("按照文档里的填",islandId,id,60*10,"违规");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}