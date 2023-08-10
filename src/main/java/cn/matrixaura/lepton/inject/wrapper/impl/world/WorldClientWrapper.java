package cn.matrixaura.lepton.inject.wrapper.impl.world;

import cn.matrixaura.lepton.inject.wrapper.Wrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.entity.EntityPlayerWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.entity.EntityWrapper;
import cn.matrixaura.lepton.util.inject.Mappings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WorldClientWrapper extends Wrapper {
    private Object worldObj;

    public WorldClientWrapper() {
        super("net/minecraft/client/multiplayer/WorldClient");
    }

    public void setWorldObj(Object worldObj) {
        this.worldObj = worldObj;
    }

    public List<EntityWrapper> getLoadedEntities() {
        // FD: adm/f net/minecraft/world/World/field_72996_f

        try {
            Field field = getClazz().getDeclaredField(Mappings.getObfField("field_72996_f"));
            Object value = field.get(worldObj);
            if (value instanceof List) {
                List<EntityWrapper> entities = new ArrayList<>();

                List<?> list = (List<?>) value;
                for (Object entityObj : list) {
                    entities.add(new EntityWrapper(entityObj));
                }
                return entities;
            }
        } catch (Exception ignored) {

        }

        return null;
    }

    public List<EntityPlayerWrapper> getLoadedPlayers() {
        // FD: adm/j net/minecraft/world/World/field_73010_i

        try {
            Field field = getClazz().getDeclaredField(Mappings.getObfField("field_73010_i"));
            Object value = field.get(worldObj);
            if (value instanceof List) {
                List<EntityPlayerWrapper> entities = new ArrayList<>();

                List list = (List) value;
                for (Object entityObj : list) {
                    entities.add(new EntityPlayerWrapper(entityObj));
                }

                return entities;
            }
        } catch (Exception ignored) {

        }

        return null;
    }

    public IBlockStateWrapper getBlockState(BlockPosWrapper wrapper) {
        return getBlockState(wrapper.get());
    }

    public IBlockStateWrapper getBlockState(Object blockPosObj) {
        try {
            Method method = getClazz().getMethod(Mappings.getObfMethod("func_180495_p"), BlockPosWrapper.getBlockPosClass());
            Object object = method.invoke(worldObj, blockPosObj);
            return new IBlockStateWrapper(object);
        } catch (Exception ignored) {

        }

        return null;
    }

    public boolean isAir(Object blockPosObj) {
        // MD: adq/d (Lcj;)Z net/minecraft/world/IBlockAccess/func_175623_d (Lnet/minecraft/util/BlockPos;)Z

        try {
            Method method = getClazz().getMethod(Mappings.getObfMethod("func_175623_d"), BlockPosWrapper.getBlockPosClass());
            Object value = method.invoke(worldObj, blockPosObj);
            return value != null && (Boolean) value;
        } catch (Exception ignored) {

        }

        return true;
    }
}
