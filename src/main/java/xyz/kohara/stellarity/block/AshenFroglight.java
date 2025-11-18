package xyz.kohara.stellarity.block;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class AshenFroglight extends RotatedPillarBlock {
    public AshenFroglight() {
        super(blockProperties());
    }

    public AshenFroglight(Properties properties) {
        super(properties);
    }

    public static Properties blockProperties() {
        return Properties.of().mapColor(MapColor.SAND).strength(0.3F).lightLevel((state) -> 15).sound(SoundType.FROGLIGHT);
    }
}
