package me.qscbm.JoinAndLeaveMessage;

import io.github.minecraftchampions.dodoopenjava.Bot;
import io.github.minecraftchampions.dodoopenjava.api.v2.ChannelMessageApi;
import io.github.minecraftchampions.dodoopenjava.event.EventHandler;
import io.github.minecraftchampions.dodoopenjava.event.Listener;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.member.MemberJoinEvent;
import io.github.minecraftchampions.dodoopenjava.event.events.v2.member.MemberLeaveEvent;

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
    public void onMemberJoinEvent(MemberJoinEvent event) { //成员加入事件
        bot.getApi().V2.getChannelMessageApi().sendTextMessage("要发送的频道ID", "<@!" + event.getDodoSourceId() + ">" + "欢迎您来到某某群"); //调用接口
    }

    @EventHandler
    public void onMemberLeaveEvent(MemberLeaveEvent event) { //成员退出事件
        bot.getApi().V2.getChannelMessageApi().sendTextMessage("要发送的频道ID", "有个人悄悄的退群了哦"); //调用接口
    }
}