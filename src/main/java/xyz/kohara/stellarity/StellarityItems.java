package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.item.*;
//? >= 1.21.9 {
/*import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
*///?}


import java.util.function.Function;

public class StellarityItems {

    public static final Item CALL_OF_THE_VOID = register("call_of_the_void", CallOfTheVoid::new, CallOfTheVoid.properties());
    public static final Item SUSHI = register("sushi", Item::new, new Item.Properties().food(basicFood(4, 2.4f)));
    public static final Item GOLDEN_CHORUS_FRUIT = register("golden_chorus_fruit", GoldenChorusFruit::new, GoldenChorusFruit.properties());
    public static final Item FRIED_CHORUS_FRUIT = register("fried_chorus_fruit", FriedChorusFruit::new, FriedChorusFruit.properties());
    public static final Item FROZEN_CARPACCIO = register("frozen_carpaccio", Item::new, new Item.Properties().food(basicFood(7, 8.4f)));
    public static final Item ENDERMAN_FLESH = register("enderman_flesh", EndermanFlesh::new, EndermanFlesh.properties());
    public static final Item CRYSTAL_HEARTFISH = register("crystal_heartfish", CrystalHeartfish::new, CrystalHeartfish.properties());
    public static final Item GRILLED_ENDERMAN_FLESH = register("grilled_enderman_flesh", Item::new, new Item.Properties().food(basicFood(6, 9.6f)));
    public static final Item FLAREFIN_KOI = register("flarefin_koi", Item::new,
            //? >= 1.21.9 {
            /*new Item.Properties().food(basicFood(4, 0.8f), Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16*20), 1.0f)).build()));
            *///?} else {
            new Item.Properties().food(partialFood(4, 0.8f).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16*20), 1.0f).build()));
            //?}


    public static final Item ENDER_DIRT = registerBlock("ender_dirt", StellarityBlocks.ENDER_DIRT);
    public static final Item ENDER_GRASS_BLOCK = registerBlock("ender_grass_block", StellarityBlocks.ENDER_GRASS_BLOCK);
    public static final Item ASHEN_FROGLIGHT = registerBlock("ashen_froglight", StellarityBlocks.ASHEN_FROGLIGHT);

    public static Item registerBlock(String name, Block block) {
        return registerBlock(name, block, new Item.Properties());
    }

    public static Item registerBlock(String name, Block block, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));
        //? if >= 1.21.9 {
        /*settings = settings.useBlockDescriptionPrefix().setId(itemKey);
        *///?}
        Item item = new BlockItem(block, settings);

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));
        //? >= 1.21.10 {
        /*settings.setId(itemKey);
        *///?}

        Item item = itemFactory.apply(settings);
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static FoodProperties.Builder partialFood(int nutrition, float saturation) {
        return partialFood(nutrition, saturation, false);
    }

    public static FoodProperties.Builder partialFood(int nutrition, float saturation, boolean alwaysEat) {
        var builder = new FoodProperties.Builder()
                .nutrition(nutrition)
                //? = 1.20.1
                .saturationMod(saturation);
                //? >= 1.21.1
                /*.saturationModifier(saturation);*/

        if (alwaysEat) {
            builder =
            //? = 1.20.1
                    builder.alwaysEat();
            //? >= 1.21.1
                    /*builder.alwaysEdible();*/
        }

        return builder;
    }

    public static FoodProperties basicFood(int nutrition, float saturation) {
        return basicFood(nutrition, saturation, false);
    }

    public static FoodProperties basicFood(int nutrition, float saturation, boolean alwaysEat) {
        return partialFood(nutrition, saturation, alwaysEat).build();
    }



    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Items");
    }
}