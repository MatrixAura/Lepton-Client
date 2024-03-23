package cn.matrixaura.lepton.uiengines.LAIT;

import cn.matrixaura.lepton.module.NonModuleBus;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class LAIT {

    public final Node program;
    private Runnable prevFunc;
    private Runnable postFunc;

    private LAIT(Node program) {
        if (!Objects.equals(program.type, "Program")) throw new RuntimeException("不是根节点");
        this.program = program;
    }

    public static LAIT parse(File source) {
        try {
            return new LAIT(LAITParser.parse(new JsonParser().parse(new FileReader(source)).getAsJsonObject()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static LAIT parse(String source) {
        return new LAIT(LAITParser.parse(new JsonParser().parse(source).getAsJsonObject()));
    }

    public LAIT register() {
        NonModuleBus.renderList.add(this);
        return this;
    }

    public void render() {
        prevFunc.run();
        program.runAll();
        postFunc.run();
    }

    public LAIT setPrevFunc(Runnable runnable) {
        prevFunc = runnable;
        return this;
    }

    public LAIT setPostFunc(Runnable runnable) {
        postFunc = runnable;
        return this;
    }

}
