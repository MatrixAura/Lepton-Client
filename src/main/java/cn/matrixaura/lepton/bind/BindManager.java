package cn.matrixaura.lepton.bind;

import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.listener.bus.Listener;
import cn.matrixaura.lepton.listener.events.input.EventKeyInput;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.input.Keyboard.KEY_NONE;

public class BindManager {
    protected final MinecraftWrapper mc = MinecraftWrapper.get();

    private final List<KeyBind> binds = new ArrayList<>();

    @Listener
    public void onKeyInput(EventKeyInput event) {
        // if the key is not known, do not try to handle it
        if (event.getKeyCode() == KEY_NONE || mc.getCurrentScreen() != null) return;
        System.out.println(event.getKeyCode());

        for (KeyBind bind : binds) {

            // if the key pressed equals the bind key and this bind is a keyboard bind, toggle the bind
            if (bind.getKeyCode() == event.getKeyCode()) {
                bind.invoke();
            }
        }
    }

    public void addBind(KeyBind bind) {
        binds.add(bind);
    }
}
