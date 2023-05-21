package wtf.agent.inject.asm.wrapper.impl.world;

import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Method;

public class WorldClientWrapper extends Wrapper {
    private Object worldObj;

    public WorldClientWrapper() {
        super("net/minecraft/client/multiplayer/WorldClient");
    }

    public void setWorldObj(Object worldObj) {
        this.worldObj = worldObj;
    }

    public IBlockStateWrapper getBlockState(BlockPosWrapper wrapper) {
        return getBlockState(wrapper.get());
    }

    public IBlockStateWrapper getBlockState(Object blockPosObj) {
        try {
            Method method = getClazz().getMethod(Mappings.seargeToNotchMethod("func_180495_p"), BlockPosWrapper.getBlockPosClass());
            Object object = method.invoke(worldObj, blockPosObj);
            return new IBlockStateWrapper(object);
        } catch (Exception ignored) {

        }

        return null;
    }

    public boolean isAir(Object blockPosObj) {
        // MD: adq/d (Lcj;)Z net/minecraft/world/IBlockAccess/func_175623_d (Lnet/minecraft/util/BlockPos;)Z

        try {
            Method method = getClazz().getMethod(Mappings.seargeToNotchMethod("func_175623_d"), BlockPosWrapper.getBlockPosClass());
            Object value = method.invoke(worldObj, blockPosObj);
            return value != null && (Boolean) value;
        } catch (Exception ignored) {

        }

        return true;
    }
}
