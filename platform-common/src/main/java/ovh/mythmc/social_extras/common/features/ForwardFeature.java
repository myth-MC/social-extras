package ovh.mythmc.social_extras.common.features;

import ovh.mythmc.gestalt.annotations.Feature;
import ovh.mythmc.gestalt.annotations.conditions.FeatureConditionBoolean;
import ovh.mythmc.gestalt.annotations.status.FeatureDisable;
import ovh.mythmc.gestalt.annotations.status.FeatureEnable;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;
import ovh.mythmc.social_extras.common.text.parsers.ForwardParser;

@Feature(group = "social-extras", identifier = "FORWARD")
public class ForwardFeature {

    private final ForwardParser parser = new ForwardParser();

    @FeatureConditionBoolean
    public boolean canBeEnabled() {
        return SocialExtrasSettings.get().getForward().isEnabled();
    }

    @FeatureEnable
    public void enable() {
        Social.get().getTextProcessor().EARLY_PARSERS.add(parser);
    }

    @FeatureDisable
    public void disable() {
        Social.get().getTextProcessor().EARLY_PARSERS.remove(parser);
    }
    
}
