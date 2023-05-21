package wtf.agent.inject.asm.wrapper.impl.render;

import wtf.agent.inject.asm.api.ReflectionUtils;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

public class RenderManagerWrapper extends Wrapper {
    private final Object renderManagerObj;

    public RenderManagerWrapper(Object renderManagerObj) {
        super("net/minecraft/client/renderer/entity/RenderManager");
        this.renderManagerObj = renderManagerObj;
    }

    public double getRenderPosX() {
        Object value = ReflectionUtils.getFieldValue(
                getClazz(), renderManagerObj, Mappings.seargeToNotchField("field_78725_b"));
        return value == null ? 0.0 : (Double) value;
    }

    public double getRenderPosY() {
        Object value = ReflectionUtils.getFieldValue(
                getClazz(), renderManagerObj, Mappings.seargeToNotchField("field_78726_c"));
        return value == null ? 0.0 : (Double) value;
    }

    public double getRenderPosZ() {
        Object value = ReflectionUtils.getFieldValue(
                getClazz(), renderManagerObj, Mappings.seargeToNotchField("field_78728_n"));
        return value == null ? 0.0 : (Double) value;
    }
}
