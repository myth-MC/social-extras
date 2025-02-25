package ovh.mythmc.social_extras.common.text.keywords;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social.api.chat.GroupChatChannel;
import ovh.mythmc.social.api.context.SocialParserContext;
import ovh.mythmc.social.api.text.parser.SocialContextualKeyword;
import ovh.mythmc.social.api.text.parser.SocialContextualParser;
import ovh.mythmc.social.common.text.parser.MiniMessageParser;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;;

public class GroupInviteKeyword extends SocialContextualKeyword {

    @Override
    public String keyword() {
        return "group_invite";
    }

    @Override
    public Component process(SocialParserContext context) {
        GroupChatChannel groupChatChannel = Social.get().getChatManager().getGroupChannelByUser(context.user());
        if (groupChatChannel == null)
            return Component.text("N/A");

        String hoverTextAsString = SocialExtrasSettings.get().getKeywords().getGroupInvite().hoverText();

        return Component.text(groupChatChannel.getCode())
            .hoverEvent(SocialContextualParser.request(context.withMessage(Component.text(hoverTextAsString)), MiniMessageParser.class).asHoverEvent())
            .clickEvent(ClickEvent.runCommand("/social:group join " + groupChatChannel.getCode()))
            .color(NamedTextColor.BLUE);
    }
    
}
