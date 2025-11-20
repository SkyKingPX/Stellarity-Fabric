package xyz.kohara.stellarity.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancements.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;


import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.network.chat.Component;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityItems;

import java.util.function.Consumer;
//? >= 1.21.1 {
/*import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
*///?} else {

import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.CriterionTriggerInstance;
 //?}

public class AdvancementsProvider extends FabricAdvancementProvider {


    //? >= 1.21.1 {
    /*public AdvancementType TASK = AdvancementType.TASK;
    public AdvancementType GOAL = AdvancementType.GOAL;
    public AdvancementType CHALLENGE = AdvancementType.CHALLENGE;

    public AdvancementsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        generateAdvancement(consumer);
    }
    *///?} else {

    public FrameType TASK = FrameType.TASK;
    public FrameType GOAL = FrameType.GOAL;
    public FrameType CHALLENGE = FrameType.CHALLENGE;
    public AdvancementsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
            //?}
    public void generateAdvancement(Consumer<
            //? >= 1.21.1 {
            /*AdvancementHolder
            *///?} else {
            Advancement
             //?}
            > consumer) {

        consumer.accept(Advancement.Builder.advancement()
                .display(
                        StellarityItems.CRYSTAL_HEARTFISH,
                        Component.translatable("advancements.stellarity.topped_off"),
                        Component.translatable("advancements.stellarity.topped_off.description"),
                        Stellarity.mcOf("textures/gui/advancements/backgrounds/adventure.png"),
                        TASK,
                        true,
                        true,
                        false
                )
                        .addCriterion("impossible", impossible())
                .build(Stellarity.of("void_fishing/topped_off")));
    }

    //? < 1.21.1 {
    private CriterionTriggerInstance impossible() {
        return new ImpossibleTrigger.TriggerInstance();
    }

    //?} else {
    /*private Criterion<ImpossibleTrigger.TriggerInstance> impossible() {
        return CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());
    }
    *///?}


}
