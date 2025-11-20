package xyz.kohara.stellarity.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import xyz.kohara.stellarity.StellarityItems;

//? >= 1.21.9 {
/*import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

*///?}

public class EndermanFlesh extends TeleportingFood {
    private static final int TELEPORT_DIAMETER = 16;

    public EndermanFlesh(Properties properties) {
        super(properties, TELEPORT_DIAMETER);
    }

    public static Properties properties() {
        var hunger = new MobEffectInstance(MobEffects.HUNGER, 1600, 0);
        //? >= 1.21.9 {
        /*return TeleportingFood.properties(StellarityItems.basicFood(4, 0.8f), TELEPORT_DIAMETER, Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(hunger, 0.8f))
        );
        *///?} else {
        return TeleportingFood.properties(StellarityItems.partialFood(4, 0.8f, false).effect(hunger, 0.8f).build(), TELEPORT_DIAMETER);
        //?}

    }
}