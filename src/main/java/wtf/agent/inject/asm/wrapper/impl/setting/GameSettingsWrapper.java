package wtf.agent.inject.asm.wrapper.impl.setting;

import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GameSettingsWrapper extends Wrapper {

    private final Object gameSettingsObj;

    private final Map<String, KeyBindingWrapper> bindings = new HashMap<>();

    public GameSettingsWrapper(Object gameSettingsObj) {
        super("net/minecraft/client/settings/GameSettings");
        this.gameSettingsObj = gameSettingsObj;

        try {

            // FD: avh/aw net/minecraft/client/settings/GameSettings/field_74324_K
            Field field = getClazz().getField(Mappings.seargeToNotchField("field_74324_K"));
            Object[] value = (Object[]) field.get(gameSettingsObj);
            for (Object v : value) {
                KeyBindingWrapper wrapper = new KeyBindingWrapper(v);
                bindings.put(wrapper.getKeyName(), wrapper);
            }

        } catch (Exception ignored) {

        }
    }

    public KeyBindingWrapper getKey(String name) {
        return bindings.getOrDefault(name, null);
    }
}
