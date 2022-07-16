package superhelo.ic2no.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import requious.recipe.AssemblyRecipe;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenExpansion("mods.requious.AssemblyRecipe")
public class ExpandAssemblyRecipe {

    @ZenMethod
    public static AssemblyRecipe requireEU(AssemblyRecipe recipe, String group, int energy, @Optional String mark) {
        return recipe.requireEnergy(group, energy * 4, mark);
    }

    @ZenMethod
    public static AssemblyRecipe requireEU(AssemblyRecipe recipe, String group, int min, int max, @Optional String mark) {
        return recipe.requireEnergy(group, min * 4, max * 4, mark);
    }

}
