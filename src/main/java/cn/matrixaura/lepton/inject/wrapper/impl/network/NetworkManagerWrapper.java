package cn.matrixaura.lepton.inject.wrapper.impl.network;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

public class NetworkManagerWrapper extends Wrapper {

    private final Object networkManagerObj;

    public NetworkManagerWrapper(Object obj) {
        super("net/minecraft/network/NetworkManager");
        networkManagerObj = obj;
    }

    public void processPacket(Object packet) {
        try {
            ReflectionUtils.invokeMethod(
                    getClazz(),
                    networkManagerObj,
                    Mappings.seargeToNotchMethod("channelRead0"),
                    new Class[]{
                            Class.forName("io/netty/channel/ChannelHandlerContext"),
                            Class.forName(Mappings.getObfClass("net/minecraft/network/Packet"))
                    },
                    null,
                    packet
            );
        } catch (Exception ignored) {
        }
    }

}
