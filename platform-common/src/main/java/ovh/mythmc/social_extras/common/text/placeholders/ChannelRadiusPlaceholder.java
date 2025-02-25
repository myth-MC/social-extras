package ovh.mythmc.social_extras.common.text.placeholders;

import net.kyori.adventure.text.Component;
import ovh.mythmc.social.api.context.SocialParserContext;
import ovh.mythmc.social.api.text.parser.SocialContextualPlaceholder;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;
import ovh.mythmc.social_extras.common.configuration.sections.ProximitySettings.ProximityChannel;

public class ChannelRadiusPlaceholder extends SocialContextualPlaceholder {

    @Override
    public String identifier() {
        return "channel_radius";
    }

    @Override
    public Component get(SocialParserContext context) {
        Component radius = Component.text("∞");
        if (!SocialExtrasSettings.get().getProximity().isEnabled()) 
            return radius;

        for (ProximityChannel proximityChannel : SocialExtrasSettings.get().getProximity().getChannels()) {
            if (context.channel().getName().equalsIgnoreCase(proximityChannel.name())) {
                radius = Component.text(String.valueOf(proximityChannel.radiusInBlocks()));
                break;
            }
        }

        return radius;
    }
    
}
