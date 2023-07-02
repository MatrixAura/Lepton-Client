package cn.matrixaura.lepton.inject.wrapper.impl.world;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.util.inject.Mappings;

import java.lang.reflect.Method;

public class IBlockStateWrapper extends Wrapper {
    private final Object iBlockStateObj;

    private final BlockWrapper block = new BlockWrapper();

    public IBlockStateWrapper(Object iBlockStateObj) {
        super("net/minecraft/block/state/IBlockState");
        this.iBlockStateObj = iBlockStateObj;
    }

    public BlockWrapper getBlock() {
        try {
            Method method = getClazz().getMethod(Mappings.seargeToNotchMethod("func_177230_c"));
            Object value = method.invoke(iBlockStateObj);
            block.setBlockObj(value);
        } catch (Exception ignored) {

        }

        return block;
    }
}
