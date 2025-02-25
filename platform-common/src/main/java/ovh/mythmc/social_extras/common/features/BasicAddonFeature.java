package ovh.mythmc.social_extras.common.features;

import java.util.List;

import ovh.mythmc.gestalt.annotations.Feature;
import ovh.mythmc.gestalt.annotations.status.FeatureDisable;
import ovh.mythmc.gestalt.annotations.status.FeatureEnable;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social.api.text.parser.SocialContextualParser;
import ovh.mythmc.social_extras.common.text.placeholders.ChannelColorPlaceholder;
import ovh.mythmc.social_extras.common.text.placeholders.ChannelRadiusPlaceholder;

@Feature(group = "social-extras", identifier = "BASE")
public class BasicAddonFeature {

    private final List<SocialContextualParser> parsers = List.of(
        new ChannelColorPlaceholder(),
        new ChannelRadiusPlaceholder()
    );

    @FeatureEnable
    public void enable() {
        parsers.forEach(Social.get().getTextProcessor()::registerContextualParser);
    }

    @FeatureDisable
    public void disable() {
        parsers.forEach(Social.get().getTextProcessor()::unregisterContextualParser);
    }
    
}
