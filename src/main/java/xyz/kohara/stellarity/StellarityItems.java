package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.item.CallOfTheVoid;
import java.util.function.Function;

public class StellarityItems {

    public static final Item CALL_OF_THE_VOID = register("call_of_the_void", CallOfTheVoid::new, CallOfTheVoid.properties());
    public static final Item SUSHI = register("sushi", Item::new, new Item.Properties().food(basicFood(4, 2.4f)));

    public static final Item ENDER_DIRT = registerBlock("ender_dirt", StellarityBlocks.ENDER_DIRT);
    public static final Item ENDER_GRASS_BLOCK = registerBlock("ender_grass_block", StellarityBlocks.ENDER_GRASS_BLOCK);
    public static final Item ASHEN_FROGLIGHT = registerBlock("ashen_froglight", StellarityBlocks.ASHEN_FROGLIGHT);

    public static Item registerBlock(String name, Block block) {
        return registerBlock(name, block, new Item.Properties());
    }
    public static Item registerBlock(String name, Block block, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));
        //? if >= 1.21.9 {
        settings = settings.useBlockDescriptionPrefix().setId(itemKey);
        //?}
        Item item = new BlockItem(block, settings);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }
    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings)  {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));
        //? >= 1.21.10 {
        settings.setId(itemKey);
        //?}

        Item item = itemFactory.apply(settings);
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static FoodProperties basicFood(int nutrition, float saturation) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                //? = 1.20.1
                /*.saturationMod(saturation)*/
                //? >= 1.21.1
                .saturationModifier(saturation)
                .build();
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Items");
    }
}