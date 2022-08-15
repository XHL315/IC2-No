package superhelo.ic2no;

import static superhelo.ic2no.Ic2No.DEPENDENCIES;
import static superhelo.ic2no.Ic2No.MOD_ID;
import static superhelo.ic2no.Ic2No.MOD_NAME;
import static superhelo.ic2no.Ic2No.VERSION;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION, dependencies = DEPENDENCIES)
public class Ic2No {

    public static final String MOD_ID = "ic2-no";
    public static final String MOD_NAME = "IC2-No";
    public static final String VERSION = "1.1";
    public static final String DEPENDENCIES = "required-after:mixinbooter;required-after:requious";

}
