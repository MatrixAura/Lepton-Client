package wtf.agent.client.bind;

import wtf.agent.client.listener.bus.Listener;
import wtf.agent.client.listener.events.input.EventKeyInput;
import wtf.agent.client.listener.events.input.EventMouseInput;
import wtf.agent.inject.asm.wrapper.impl.MinecraftWrapper;

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
            if (bind.getKey() == event.getKeyCode() && bind.getDevice() == BindDevice.KEYBOARD) {
                bind.setState(!bind.isToggled());
            }
        }
    }

    @Listener
    public void onMouseInput(EventMouseInput event) {
        if (event.getButton() < KEY_NONE || mc.getCurrentScreen() != null) return;

        for (Bind bind : bindMap.values()) {

            // if the button pressed equals the bind button and this bind is a mouse bind, toggle the bind
            if (bind.getKey() == event.getButton() && bind.getDevice() == BindDevice.MOUSE) {
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
