package xyz.kohara.stellarity.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityItems;

//? >= 1.21.9 {
import net.minecraft.world.item.component.Consumables;
//?}

import java.util.UUID;

public class CrystalHeartfish extends Item {
    public CrystalHeartfish(Properties properties) {
        super(properties);
    }

    //? <= 1.21.1 {
    /*@Override
    public int getUseDuration(ItemStack itemStack
                              //? = 1.21.1
                              /^, LivingEntity livingEntity^/
    ) {
        return 100;
    }
    *///?}
    

    public static Properties properties() {
        return new Properties().food(
                StellarityItems.partialFood(0, 0, true).build()
                //? >= 1.21.9
                , Consumables.defaultFood().consumeSeconds(5f).build()
        );
    }

    @Override
    @NotNull
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            this.addHealth(livingEntity);
        }
        return super.finishUsingItem(itemStack, level, livingEntity);

    }

    public void addHealth(LivingEntity entity) {
        AttributeInstance maxHPAttribute = entity.getAttributes().getInstance(Attributes.MAX_HEALTH);
        if (maxHPAttribute == null) return;


        //? if < 1.21.1 {
        
        /*UUID uuid = UUID.fromString("019a9cd4-c40f-7032-a01f-273d3b1ed9b1");
        AttributeModifier oldModifier = maxHPAttribute.getModifier(uuid);

        double amount = oldModifier == null ? 0.0 : oldModifier.getAmount();
        if (amount >= 10) return;
        amount++;

        AttributeModifier newModifier = new AttributeModifier(uuid, "stellarity:crystal_heartfish_health_bonus",
                amount,
                AttributeModifier.Operation.ADDITION
        );

        

        *///?} else {
        AttributeModifier oldModifier = maxHPAttribute.getModifier(Stellarity.of("crystal_heartfish_health_bonus"));

        double amount = oldModifier == null ? 0.0 : oldModifier.amount();
        if (amount >= 10) return;
        amount++;

        AttributeModifier newModifier = new AttributeModifier(
                Stellarity.of("crystal_heartfish_health_bonus"),
                amount,
                AttributeModifier.Operation.ADD_VALUE
        );
        //?}


        if (oldModifier != null) {
            maxHPAttribute.removeModifier(oldModifier);
        }
        maxHPAttribute.addPermanentModifier(newModifier);
    }


}
