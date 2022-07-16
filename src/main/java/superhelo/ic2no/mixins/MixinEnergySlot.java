package superhelo.ic2no.mixins;

import java.util.Collections;
import java.util.List;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import requious.data.AssemblyProcessor;
import requious.data.component.ComponentEnergy;
import requious.gui.slot.BaseSlot;
import requious.gui.slot.EnergySlot;

@Mixin(value = EnergySlot.class, remap = false)
public abstract class MixinEnergySlot extends BaseSlot<ComponentEnergy.Slot> {

    public MixinEnergySlot(AssemblyProcessor assembly, ComponentEnergy.Slot binding, int xPosition, int yPosition) {
        super(assembly, binding, xPosition, yPosition);
    }

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"), cancellable = true)
    private void injectGetTooltip(CallbackInfoReturnable<List<String>> cir) {
        String unit = binding.getUnit();
        int amount = binding.getEUConversion().getUnit(binding.getAmount());
        int capacity = binding.getEUConversion().getUnit(binding.getCapacity());
        if (unit.equals("eu")) {
            cir.setReturnValue(Collections.singletonList(I18n.format("requious.unit.eu", amount, capacity)));
        }
    }
}
