package xyz.kohara.stellarity.item;

import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.StellarityItems;

public class CrystalHeartfish extends Item {
    public CrystalHeartfish(Properties properties) {
        super(properties);
    }

    public static Properties properties() {
        return new Properties().food(StellarityItems.partialFood(0,0,true).build());
    }





}
