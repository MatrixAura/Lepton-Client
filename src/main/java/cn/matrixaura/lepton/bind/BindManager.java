package cn.matrixaura.lepton.bind;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.input.EventKeyInput;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.lwjgl.input.Keyboard.KEY_NONE;

public class BindManager {
    protected final MinecraftWrapper mc = MinecraftWrapper.get();

    private final Map<String, Bind> bindMap = new LinkedHashMap<>();

    @Listener
    public void onKeyInput(EventKeyInput event) {
        // if the key is not known, do not try to handle it
        if (event.getKeyCode() <= KEY_NONE || mc.getCurrentScreen() != null) return;

        for (Bind bind : bindMap.values()) {

            // if the key pressed equals the bind key and this bind is a keyboard bind, toggle the bind
            if (bind.getKey() == event.getKeyCode()) {
                bind.setState(!bind.isToggled());
            }
        }
    }

    public void addBind(Bind bind) {
        bindMap.put(bind.getName(), bind);
    }

    public Bind getBind(String tag) {
        return bindMap.getOrDefault(tag, null);
    }

    public Collection<Bind> getBindList() {
        return bindMap.values();
    }
}
