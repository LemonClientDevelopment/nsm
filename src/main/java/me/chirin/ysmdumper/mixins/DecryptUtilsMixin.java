package me.chirin.ysmdumper.mixins;

import com.elfmcys.yesstevemodel.O0o0ooO000OOOo000o0000O;
import me.chirin.ysmdumper.YsmDumper;
import me.chirin.ysmdumper.ZipUtil;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.util.Map;

@Mixin(O0o0ooO000OOOo000o0000O.class)
public class DecryptUtilsMixin {
    @Inject(method = "OooOo0oOoOO0o00OO000oO00(Ljava/io/File;)Ljava/util/Map;", at = @At("RETURN"), remap = false)
    private static void afterDecrypt(File file, CallbackInfoReturnable<Map<String, byte[]>> cir) {
        if (!cir.getReturnValue().isEmpty()) {
            String result = ZipUtil.generate(cir.getReturnValue());
            if (YsmDumper.mc.getToastManager() == null || YsmDumper.mc.textRenderer == null) return;
            if (result != null) {
                YsmDumper.mc.getToastManager().add(
                        new SystemToast(SystemToast.Type.TUTORIAL_HINT, Text.literal("Model Dumped!"), Text.literal("Saved as %s.".formatted(result)))
                );
            } else {
                YsmDumper.mc.getToastManager().add(
                        new SystemToast(SystemToast.Type.PACK_LOAD_FAILURE, Text.literal("Model Dump Failed."), Text.literal("Check out the logs for more detailed information."))
                );
            }
        } else {
            ZipUtil.LOG.warn("Empty entries. Skipping.");
        }
    }
}
