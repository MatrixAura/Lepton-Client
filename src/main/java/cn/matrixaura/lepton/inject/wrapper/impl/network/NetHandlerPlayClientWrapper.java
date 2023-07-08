package cn.matrixaura.lepton.inject.wrapper.impl.network;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

import java.lang.reflect.Method;

public class NetHandlerPlayClientWrapper extends Wrapper {

    private final Object netHandlerPlayClientObj;

    public NetHandlerPlayClientWrapper(Object obj) {
        super("net/minecraft/client/network/NetHandlerPlayClient");
        netHandlerPlayClientObj = obj;
    }

    public void addToSendQueue(Object packet) {
        try {
            String notch = Mappings.seargeToNotchMethod("func_147297_a"); // addToSendQueue
            Class<?> packetClass = Class.forName(Mappings.getObfClass("net/minecraft/network/Packet"));
            Method method = getClass().getMethod(notch, packetClass);
            method.invoke(netHandlerPlayClientObj, packet);
        } catch (Exception ignored) {
        }
    }

    public NetworkManagerWrapper getNetworkManager() {
        return new NetworkManagerWrapper(ReflectionUtils.invokeMethod(getClazz(), netHandlerPlayClientObj, Mappings.seargeToNotchMethod("func_147298_b")));
    }
}
