package cn.matrixaura.lepton.module.impl.other;

import cn.matrixaura.lepton.module.Category;
import cn.matrixaura.lepton.module.Module;
import cn.matrixaura.lepton.module.ModuleInfo;
import cn.matrixaura.lepton.util.inject.InjectUtils;

@ModuleInfo(name = "Self Destruction", category = Category.Other, description = "Lets your minecraft looks like legit client")
public class SelfDestruction extends Module {

    @Override
    public void onEnable() {
        InjectUtils.destroyClient();
        this.toggle();
    }
}
