package ovh.mythmc.social_extras.common.listeners;

import ovh.mythmc.gestalt.Gestalt;
import ovh.mythmc.gestalt.annotations.FeatureListener;
import ovh.mythmc.gestalt.features.FeatureEvent;
import ovh.mythmc.social_extras.common.boot.SocialExtrasBootstrap;

public class SocialListener {

    @FeatureListener(group = "social", identifier = "ADDON", events = { FeatureEvent.ENABLE })
    public void enable() {
        // Reload settings before enabling features
        SocialExtrasBootstrap.get().reload();

        Gestalt.get().enableAllFeatures("social-extras");
    }

    @FeatureListener(group = "social", identifier = "ADDON", events = { FeatureEvent.DISABLE })
    public void disable() {
        Gestalt.get().disableAllFeatures("social-extras");
    }
    
}
