package superhelo.ic2no.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import requious.compat.crafttweaker.RecipeContainer;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenExpansion("mods.requious.RecipeContainer")
public class ExpandRecipeContainer {

    @ZenMethod
    public static void addEUOutput(RecipeContainer container, String group, int energy) {
        container.addEnergyOutput(group, energy * 4);
    }

    @ZenMethod
    public static void addEUOutput(RecipeContainer container, String group, int energy, int minInsert) {
        container.addEnergyOutput(group, energy * 4, minInsert * 4);
    }

}
