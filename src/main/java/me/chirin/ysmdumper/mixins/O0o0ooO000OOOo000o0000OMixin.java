package me.chirin.ysmdumper.mixins;

import com.elfmcys.yesstevemodel.O0o0ooO000OOOo000o0000O;
import me.chirin.ysmdumper.ZipUtil;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.util.Map;

import static me.chirin.ysmdumper.YsmDumper.mc;

@Mixin(O0o0ooO000OOOo000o0000O.class)
public class O0o0ooO000OOOo000o0000OMixin {
    @Inject(method = "OooOo0oOoOO0o00OO000oO00(Ljava/io/File;)Ljava/util/Map;", at = @At("RETURN"), remap = false)
    private static void afterDecrypt(File file, CallbackInfoReturnable<Map<String, byte[]>> cir) {
        Map<String, byte[]> entries = cir.getReturnValue();
        if (!entries.isEmpty()) {
            String result = ZipUtil.generate(cir.getReturnValue());
            if (result != null) {
                mc.getToastManager().add(
                        new SystemToast(SystemToast.Type.TUTORIAL_HINT, Text.literal("Model Dumped!"), Text.literal("Saved as %s.".formatted(result)))
                );
            }
        } else {
            ZipUtil.LOG.warn("Empty entries. Skipping.");
        }
    }
}
