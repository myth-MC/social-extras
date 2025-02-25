package ovh.mythmc.social_extras.common.features;

import ovh.mythmc.gestalt.annotations.Feature;
import ovh.mythmc.gestalt.annotations.conditions.FeatureConditionBoolean;
import ovh.mythmc.gestalt.annotations.status.FeatureDisable;
import ovh.mythmc.gestalt.annotations.status.FeatureEnable;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;
import ovh.mythmc.social_extras.common.listeners.ProximityListener;
import ovh.mythmc.social_extras.common.text.placeholders.ChannelRadiusPlaceholder;

@Feature(group = "social-extras", identifier = "PROXIMITY")
public class ProximityFeature {

    private final ProximityListener listener = new ProximityListener();
    private final ChannelRadiusPlaceholder channelRadiusPlaceholder = new ChannelRadiusPlaceholder();

    @FeatureConditionBoolean
    public boolean canBeEnabled() {
        return SocialExtrasSettings.get().getProximity().isEnabled();
    }

    @FeatureEnable
    public void enable() {
        listener.registerCallbackHandlers();

        // Parsers
        Social.get().getTextProcessor().registerContextualParser(channelRadiusPlaceholder);
    }

    @FeatureDisable
    public void disable() {
        listener.unregisterCallbackHandlers();

        // Parsers
        Social.get().getTextProcessor().unregisterContextualParser(channelRadiusPlaceholder);
    }
    
}
