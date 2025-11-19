package xyz.kohara.stellarity.item;


import xyz.kohara.stellarity.StellarityItems;

public class GoldenChorusFruit extends TeleportingFood {
    private static final int TELEPORT_DIAMETER = 300;

    public GoldenChorusFruit(Properties properties) {
        super(properties, TELEPORT_DIAMETER);
    }

    public static Properties properties() {
        return TeleportingFood.properties(StellarityItems.basicFood(6, 14.4f), TELEPORT_DIAMETER);
    }
}