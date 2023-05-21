package wtf.agent.inject.asm.wrapper.impl.world;

import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class BlockPosWrapper extends Wrapper {
    private static Class<?> blockPosClass;
    private static final Map<Integer, Object> blockPosCache = new HashMap<>();

    private Object blockPosObj;
    private int x, y, z;

    public BlockPosWrapper(Object blockPosObj) {
        super("net/minecraft/util/BlockPos");
        this.blockPosObj = blockPosObj;

        //FD: df/a net/minecraft/util/Vec3i/field_177962_a x
        //FD: df/c net/minecraft/util/Vec3i/field_177960_b y
        //FD: df/d net/minecraft/util/Vec3i/field_177961_c z

        try {
            Field field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_177962_a"));
            field.setAccessible(true);

            x = (Integer) field.get(blockPosObj);

            field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_177960_b"));
            field.setAccessible(true);

            y = (Integer) field.get(blockPosObj);

            field = getClazz().getDeclaredField(Mappings.seargeToNotchField("field_177961_c"));
            field.setAccessible(true);

            z = (Integer) field.get(blockPosObj);
        } catch (Exception ignored) {

        }
    }

    public BlockPosWrapper(int x, int y, int z) {
        super("net/minecraft/util/BlockPos");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPosWrapper(double x, double y, double z) {
        super("net/minecraft/util/BlockPos");
        this.x = (int) x;
        this.y = (int) y;
        this.z = (int) z;
    }

    public Object add(int x, int y, int z) {
        return create(this.x + x, this.y + y, this.z + z);
    }

    public Object get() {
        return create(x, y, z);
    }

    public static Object create(int x, int y, int z) {
        int hash = hash(x, y, z);
        if (!blockPosCache.containsKey(hash)) {
            try {
                Object blockPosObj = getBlockPosClass().getConstructors()[4].newInstance(x, y, z);
                blockPosCache.put(hash, blockPosObj);
            } catch (Exception ignored) {

            }
        }

        return blockPosCache.get(hash);
    }

    public static Object create(double x, double  y, double z) {
        int hash = hash((int) x, (int) y, (int) z);
        if (!blockPosCache.containsKey(hash)) {
            try {
                Object blockPosObj = getBlockPosClass().getConstructors()[4].newInstance((int) x, (int) y, (int) z);
                blockPosCache.put(hash, blockPosObj);
            } catch (Exception ignored) {

            }
        }

        return blockPosCache.get(hash);
    }

    public static Class<?> getBlockPosClass() {
        if (blockPosClass == null) {
            try {
                blockPosClass = Class.forName(Mappings.getUnobfClass("net/minecraft/util/BlockPos"));
            } catch (Exception ignored) {
            }
        }

        return blockPosClass;
    }

    public static int hash(int x, int y, int z) {
        return (y + z * 31) * 31 + x;
    }
}
