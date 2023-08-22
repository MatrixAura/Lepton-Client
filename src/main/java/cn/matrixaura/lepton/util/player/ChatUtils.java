package cn.matrixaura.lepton.util.player;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class ChatUtils {

    public static void addChatMessage(String message) {
        try {
            MinecraftWrapper.get().getPlayer().addChatMessage(ReflectionUtils.newInstance(
                    Class.forName(Mappings.getObfClass("net/minecraft/util/ChatComponentText")),
                    new Class[]{
                            String.class
                    },
                    message
            ));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendChatMessage(String message) {
        MinecraftWrapper.get().getPlayer().sendChatMessage(message);
    }

}
