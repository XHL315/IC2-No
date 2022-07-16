package superhelo.ic2no.mixins;

import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import requious.compat.jei.ingredient.Energy;
import requious.compat.jei.ingredient.EnergyRenderer;

@Mixin(value = EnergyRenderer.class, remap = false)
public abstract class MixinEnergyRender {

    @Inject(method = "getTooltip(Lnet/minecraft/client/Minecraft;Lrequious/compat/jei/ingredient/Energy;Lnet/minecraft/client/util/ITooltipFlag;)Ljava/util/List;",
        at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"), cancellable = true)
    private void injectGetTooltip(Minecraft minecraft, Energy ingredient, ITooltipFlag tooltipFlag, CallbackInfoReturnable<List<String>> cir) {
        if (ingredient.unit.equals("eu")) {
            cir.setReturnValue(Collections.singletonList(I18n.format("requious.unit.eu.cost",ingredient.energy / 4)));
        }
    }

}
