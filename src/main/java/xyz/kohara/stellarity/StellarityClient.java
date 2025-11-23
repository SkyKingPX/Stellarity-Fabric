package xyz.kohara.stellarity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class StellarityClient implements ClientModInitializer {


  @Override
  public void onInitializeClient() {

    Stellarity.LOGGER.info("Stellarity Client Initializing");

    StellarityModels.init();
    StellarityCreativeModeTabs.init();
  }
}
