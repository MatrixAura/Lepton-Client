package cn.matrixaura.lepton.module.impl.other.anticrash.checks;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.module.impl.other.anticrash.CrashCheck;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ResourcePackCheck extends CrashCheck {

    public ResourcePackCheck() {
        super("Resource Pack Check", "Blocked an invalid resource-pack packet");
    }

    @Override
    public boolean handle(Object packet) {
        if (Mappings.getUnobfClass(packet.getClass().getName().replace(".", "/")).equals("net/minecraft/network/play/server/S48PacketResourcePackSend")) {

            final String url = (String) ReflectionUtils.invokeMethod(packet.getClass(), packet, Mappings.seargeToNotchMethod("func_179783_a"));
            final String hash = (String) ReflectionUtils.invokeMethod(packet.getClass(), packet, Mappings.seargeToNotchMethod("func_179784_b"));

            if (url.toLowerCase().startsWith("level://")) {
                return check(url, hash);
            }
        }

        return false;
    }

    private boolean check(String url, final String hash) {
        try {
            final URI uri = new URI(url);

            final String scheme = uri.getScheme();
            final boolean isLevelProtocol = "level".equals(scheme);

            if (!("http".equals(scheme) || "https".equals(scheme) || isLevelProtocol)) {
                throw new URISyntaxException(url, "Wrong protocol");
            }

            url = URLDecoder.decode(url.substring("level://".length()), StandardCharsets.UTF_8.toString());

            if (isLevelProtocol && (url.contains("..") || !url.endsWith("/resources.zip"))) {
                System.out.println("Server tried to access the path: " + url);

                throw new URISyntaxException(url, "Invalid levelstorage resource pack path");
            }

            return false;
        } catch (final Exception e) {
            try {
                Class<?> clazz = Class.forName(Mappings.getObfClass("net/minecraft/network/play/client/C19PacketResourcePackStatus"));
                Class<?> actionClass = Class.forName(Mappings.getObfClass("net/minecraft/network/play/client/C19PacketResourcePackStatus$Action"));
                Object failedDownload = ReflectionUtils.getFieldValue(actionClass, null, Mappings.seargeToNotchField("FAILED_DOWNLOAD"));
                MinecraftWrapper.get().getNetHandler().addToSendQueue(ReflectionUtils.newInstance(clazz, new Class[]{String.class, actionClass}, hash, failedDownload));
            } catch (ClassNotFoundException ignored) {
            }
            return true;
        }
    }

}
