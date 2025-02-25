package ovh.mythmc.social_extras.common.features;

import ovh.mythmc.gestalt.annotations.Feature;
import ovh.mythmc.gestalt.annotations.conditions.FeatureConditionBoolean;
import ovh.mythmc.gestalt.annotations.status.FeatureDisable;
import ovh.mythmc.gestalt.annotations.status.FeatureEnable;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;
import ovh.mythmc.social_extras.common.listeners.ShortcutsListener;

@Feature(group = "social-extras", identifier = "SHORTCUTS")
public class ShortcutsFeature {

    private final ShortcutsListener listener = new ShortcutsListener();
    
    @FeatureConditionBoolean
    public boolean canBeEnabled() {
        return Social.get().getConfig().getChat().isEnabled() && 
            SocialExtrasSettings.get().getShortcuts().isEnabled();
    }

    @FeatureEnable
    public void enable() {
        listener.registerCallbackHandlers();
    }

    @FeatureDisable
    public void disable() {
        listener.unregisterCallbackHandlers();
    }

}
