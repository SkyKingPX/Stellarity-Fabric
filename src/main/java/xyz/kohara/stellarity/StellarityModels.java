package xyz.kohara.stellarity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.Item;

//? <= 1.21.1 {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;

//?} else {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?}

@Environment(EnvType.CLIENT)
public class StellarityModels {
    private static void registerBowModel(Item bow) {
        //? <= 1.21.4 {

        ItemProperties.register(bow, Stellarity.mcOf("pull"), (itemStack, clientWorld, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            //? = 1.20.1
            return entity.getUseItem() != itemStack ? 0.0F : (itemStack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            //? = 1.21.1
            /*return entity.getUseItem() != itemStack ? 0.0F : (itemStack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;*/
        });

        ItemProperties.register(bow, Stellarity.mcOf("pulling"), (itemStack, clientWorld, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
        //?}

    }
    public static void initModelPredicates() {
        //? <= 1.21.4 {
        registerBowModel(StellarityItems.CALL_OF_THE_VOID);
        Stellarity.LOGGER.info("Stellarity Model Predicates Initialized!");
        //?}

    }

    public static void initBlockColors() {

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                return BiomeColors.getAverageGrassColor(world, pos);
            }
            // fallback tint
            return 0x91BD59;
        }, StellarityBlocks.ENDER_GRASS_BLOCK);

        //? <= 1.21.1 {
        BlockRenderLayerMap.INSTANCE.putBlock(StellarityBlocks.ENDER_GRASS_BLOCK, RenderType.cutout());
        //?} else {
        /*BlockRenderLayerMap.putBlock(StellarityBlocks.ENDER_GRASS_BLOCK, ChunkSectionLayer.CUTOUT);
         *///?}

        Stellarity.LOGGER.info("Initialized Block Model Colors");
    }

    public static void initItemColors() {
        //? <= 1.21.1 {
        ColorProviderRegistry.ITEM.register(((itemStack, i) -> {
            return 0x91BD59;
        }), StellarityItems.ENDER_GRASS_BLOCK);
        //?}

        Stellarity.LOGGER.info("Initialized Item Model Colors");

    }

    public static void init() {
        initModelPredicates();
        initBlockColors();
        initItemColors();
    }
}
