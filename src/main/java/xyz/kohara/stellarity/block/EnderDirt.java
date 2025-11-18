package xyz.kohara.stellarity.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class EnderDirt extends Block {

    public static Properties blockProperties() {
        return Properties.of()
                .mapColor(MapColor.DIRT)
                .strength(0.5F)
                .sound(SoundType.ROOTED_DIRT);
    }

    public EnderDirt() {
        super(blockProperties());
    }

    public EnderDirt(Properties properties) {
        super(properties);
    }






}