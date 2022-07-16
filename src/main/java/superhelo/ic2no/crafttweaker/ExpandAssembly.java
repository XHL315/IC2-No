package superhelo.ic2no.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import requious.compat.crafttweaker.ComponentFaceCT;
import requious.data.AssemblyData;
import requious.data.component.ComponentEnergy;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenExpansion("mods.requious.Assembly")
public class ExpandAssembly {

    @ZenMethod
    public static ComponentEnergy setEUSlot(AssemblyData assembly, int x, int y, ComponentFaceCT face, int capacity) {
        return assembly.setEnergySlot(x, y, face, capacity * 4).setUnit("eu").acceptEU(true);
    }

}
