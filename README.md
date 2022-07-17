# Example

## confing

```json
{
    "placeType": "Up",
    "layerType": "Cutout",
    "hasGUI": true,
    "extraVariants": [],
    "resourceName": "oil_refineries",
    "model": "requious:assembly_block",
    "colors": [
      {
        "r": 255,
        "g": 255,
        "b": 255,
        "a": 255
      }
    ],
    "hardness": 5.0,
    "blastResistance": 5.0,
    "aabb": {
      "x1": 0.0,
      "y1": 0.0,
      "z1": 0.0,
      "x2": 1.0,
      "y2": 1.0,
      "z2": 1.0
    }
}
```

## ZenScript

```csharp
// Assembly
// assembly.setEUSlot(x as int, y as int, face as ComponentFace, capacity as int);

// AssemblyRecipe
// recipe.requireEU(group as string, energy as int, @Optional mark as string);
// recipe.requireEU(group as string, min as int, max as int, @Optional mark as string);

// RecipeContainer
// container.addEUOutput(grout as string, energy as int);
// container.addEUOutput(grout as string, energy as int, minInsert as int);

import mods.requious.Assembly;
import mods.requious.ComponentFace;
import mods.requious.AssemblyRecipe;
 
static oilRefineries as Assembly = <assembly:oil_refineries>;

addFluidSlot(4, 1, ComponentFace.back(), 5000, true, false, "input");
addFluidSlot(2, 3, ComponentFace.up(), 5000, true, false, "output1");
addFluidSlot(6, 3, ComponentFace.front(), 5000, true, false, "output2");

oilRefineries.setEUSlot(0, 4, ComponentFace.all(), 2500)
      .acceptFE(false)
      .setGroup("energy");
oilRefineries.setJEIEnergySlot(0, 4, "energyInput", "eu");

oilRefineries.setEUSlot(0, 3, ComponentFace.all(), 10000)
      .acceptFE(false)
      .setAccess(false, true)
      .setGroup("energyO");
oilRefineries.setJEIEnergySlot(0, 3, "energyOutput", "eu");

var recipe as AssemblyRecipe = AssemblyRecipe.create(function(container) {
    container.addFluidOutput("output1", <fluid:water> * 1000);
    container.addFluidOutput("output2", <fluid:lava> * 1000);
    container.addEUOutput("energyOutput", 1000);
}).requireEU("energyInput", 2500);

oilRefineries.addRecipe(recipe);
oilRefineries.addJEIRecipe(recipe);

function addFluidSlot(x as int, y as int, face as ComponentFace, capacity as int, allowInput as bool, allowOutput as bool, group as string) {
    oilRefineries.setFluidSlot(x, y, face, capacity)
        .setAccess(allowInput, allowOutput)
        .setGroup(group);
    oilRefineries.setJEIFluidSlot(x, y, group);
}
```
