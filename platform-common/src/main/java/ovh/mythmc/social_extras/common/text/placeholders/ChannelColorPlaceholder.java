package ovh.mythmc.social_extras.common.text.placeholders;

import net.kyori.adventure.text.Component;
import ovh.mythmc.social.api.context.SocialParserContext;
import ovh.mythmc.social.api.text.parser.SocialContextualPlaceholder;

public class ChannelColorPlaceholder extends SocialContextualPlaceholder {

    @Override
    public String identifier() {
        return "channel_color";
    }

    @Override
    public Component get(SocialParserContext context) {
        return Component.text(context.channel().getColor().asHexString());
    }
    
}
