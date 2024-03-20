package cn.matrixaura.lepton.uiengines.LAIT;

import cn.matrixaura.lepton.module.NonModuleBus;
import com.google.gson.JsonParser;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LAIT {

    private final Node node;
    private Runnable prevFunc;
    private Runnable postFunc;

    private LAIT(Node node) {
        this.node = node;
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
        node.runAll();
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
