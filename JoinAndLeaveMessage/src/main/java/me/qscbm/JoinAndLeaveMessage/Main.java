package me.qscbm.JoinAndLeaveMessage;

import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.EventManage;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.MemberJoinEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.MemberLeaveEvent;

import java.io.IOException;


public class Main implements Listener {
    public static void main(String[] args) {
        EventManage.registerEvents(new Main(),"按照文档填");
    }

    @EventHandler
    public void onMemberJoinEvent(MemberJoinEvent event) { //成员加入事件
        try {
            ChannelMessageApi.sendTextMessage("按照文档填", "要发送的频道ID","<@!" + event.getDodoSourceId() + ">" + "欢迎您来到某某群"); //调用接口
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onMemberLeaveEvent(MemberLeaveEvent event) { //成员退出事件
        try {
            ChannelMessageApi.sendTextMessage("按照文档填","要发送的频道ID", "有个人悄悄的退群了哦"); //调用接口
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}