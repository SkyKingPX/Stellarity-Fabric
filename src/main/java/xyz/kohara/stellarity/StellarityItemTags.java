package xyz.kohara.stellarity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class StellarityItemTags {
  public static final TagKey<Item> FISHES = bind("fishes");


  private static TagKey<Item> bind(String id) {
    return TagKey.create(Registries.ITEM, Stellarity.of(id));
  }

}
