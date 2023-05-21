package wtf.agent.inject.asm.wrapper.impl.world;

import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Field;

public class BlockWrapper extends Wrapper {

    private Object blockObj;

    private MaterialWrapper material;

    public BlockWrapper() {
        super("net/minecraft/block/Block");
    }

    public void setBlockObj(Object blockObj) {
        this.blockObj = blockObj;
    }

    public MaterialWrapper getMaterial() {

        try {
            // FD: afh/J net/minecraft/block/Block/field_149764_J
            Field field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_149764_J"));
            field.setAccessible(true);
            material = new MaterialWrapper(field.get(blockObj));
            field.setAccessible(false);
        } catch (Exception ignored) {

        }

        return material;
    }
}
