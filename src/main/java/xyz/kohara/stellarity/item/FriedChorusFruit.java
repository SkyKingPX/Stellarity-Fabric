package xyz.kohara.stellarity.item;

import xyz.kohara.stellarity.StellarityItems;

public class FriedChorusFruit extends TeleportingFood {
    public static final int TELEPORT_DIAMETER = 32;

    public FriedChorusFruit(Properties properties) {
        super(properties, TELEPORT_DIAMETER);
    }

    public static Properties properties() {
        return TeleportingFood.properties(StellarityItems.basicFood(7, 11.2f), TELEPORT_DIAMETER);
    }
}