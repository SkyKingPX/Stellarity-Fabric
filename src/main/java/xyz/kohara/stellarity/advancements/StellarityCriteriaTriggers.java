package xyz.kohara.stellarity.advancements;

import net.minecraft.advancements.CriteriaTriggers;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.advancements.critereron.VoidFishedTrigger;

public class StellarityCriteriaTriggers {
  public static VoidFishedTrigger VOID_FISHED = CriteriaTriggers.register(
    //? > 1.21
    //"stellarity:void_fished",
    new VoidFishedTrigger()
  );

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Criteria Triggers");
  }
}
