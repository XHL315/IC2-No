package superhelo.ic2no.mixins;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import requious.block.BlockAssembly;
import requious.tile.TileEntityAssembly;

@Mixin(value = TileEntityAssembly.class)
public abstract class MixinTileEntityAssembly extends TileEntity {

    boolean addedToEnet = false;

    @Inject(method = "toLocalSide", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void injectToLocalSide(EnumFacing facing, EnumFacing side, CallbackInfoReturnable<EnumFacing> cir) {
        if (facing == null) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "toGlobalSide", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private static void injectToGlobalSide(EnumFacing facing, EnumFacing side, CallbackInfoReturnable<EnumFacing> cir) {
        if (facing == null) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "update", at = @At(value = "RETURN"))
    private void injectUpdate(CallbackInfo ci) {
        if (!world.isRemote && !addedToEnet) {
            addedToEnet = true;
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent((TileEntityAssembly) (Object) this));
        }
    }

    @Inject(method = "getFacing",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/block/state/IBlockState;getValue(Lnet/minecraft/block/properties/IProperty;)Ljava/lang/Comparable;", remap = true),
        cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectGetFacing(CallbackInfoReturnable<EnumFacing> cir, IBlockState state) {
        if (state.getPropertyKeys().contains(BlockAssembly.facing)) {
            cir.setReturnValue(state.getValue(BlockAssembly.facing));
            return;
        }
        cir.setReturnValue(null);
    }

    @Override
    public void invalidate() {
        if (!world.isRemote && addedToEnet) {
            addedToEnet = false;
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent((TileEntityAssembly) (Object) this));
        }
        super.invalidate();
    }

    @Override
    public void onChunkUnload() {
        if (!world.isRemote && addedToEnet) {
            addedToEnet = false;
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent((TileEntityAssembly) (Object) this));
        }
        super.onChunkUnload();
    }

}
