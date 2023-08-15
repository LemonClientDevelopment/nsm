package me.chirin.ysmdumper.mixins;

import com.elfmcys.yesstevemodel.oo00oo00OO0oo0OoOOo0000O;
import com.elfmcys.yesstevemodel.ooooOOOOo0o0oOoO00O000Oo;
import me.chirin.ysmdumper.YsmDumper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ooooOOOOo0o0oOoO00O000Oo.class)
public class PlayerModelsScreenMixin extends Screen {
    protected PlayerModelsScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo info) {
        addDrawableChild(
                new ButtonWidget.Builder(Text.literal("Reload"), button -> {
                    YsmDumper.doDecrypt = true;
                    oo00oo00OO0oo0OoOOo0000O.OoOOOoO0ooo0oO000O0o0O();
                    YsmDumper.mc.send(() -> {
                        oo00oo00OO0oo0OoOOo0000O.OooOo0oOoOO0o00OO000oO00();
                        YsmDumper.doDecrypt = false;
                    });
                })
                        .position(width / 2 - 40, 3)
                        .size(80, 20)
                        .build()
        );
    }
}
