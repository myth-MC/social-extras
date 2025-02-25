package ovh.mythmc.social_extras.common.text.parsers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social.api.context.SocialMessageContext;
import ovh.mythmc.social.api.context.SocialParserContext;
import ovh.mythmc.social.api.text.parser.SocialContextualParser;
import ovh.mythmc.social.api.text.parser.SocialUserInputParser;
import ovh.mythmc.social.common.text.parser.EmojiParser;
import ovh.mythmc.social.common.text.parser.MiniMessageParser;
import ovh.mythmc.social.common.text.parser.RawEmojiParser;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasConfigProvider;
import ovh.mythmc.social_extras.common.text.placeholders.ChannelColorPlaceholder;

public class ForwardParser implements SocialUserInputParser {

    private Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public Component parse(SocialParserContext context) {
        Component message = context.message();

        if (!context.user().player().get().hasPermission("social.forward"))
            return message;
            
        TextComponent textComponent = (TextComponent) message;
        if (textComponent.content().contains("(fwd:#") && textComponent.content().contains(")")) {
            String messageIdString = textComponent.content().substring(textComponent.content().indexOf("(fwd:#") + 6, textComponent.content().indexOf(")"));
            int messageId = tryParse(messageIdString);
            if (messageId == 0) 
                return message;

            SocialMessageContext forwardedMessage = Social.get().getChatManager().getHistory().getById(messageId);
            if (forwardedMessage == null)
                return message;

            if (forwardedMessage.sender().player().isEmpty())
                return message;
            
            Component format = Component.text(SocialExtrasConfigProvider.get().getForward().getFormat());
            Component replacement = SocialContextualParser.request(context.withMessage(format), 
                EmojiParser.class,
                RawEmojiParser.class,
                ChannelColorPlaceholder.class
            );

            // Todo: change this
            replacement = SocialContextualParser.request(context.withMessage(replacement), MiniMessageParser.class);

            Component hoverText = Component.text(forwardedMessage.sender().getNickname() + ": ", NamedTextColor.GRAY)
                .append(Component.text(forwardedMessage.rawMessage(), NamedTextColor.WHITE));

            message = message.replaceText(TextReplacementConfig.builder()
                .matchLiteral("(fwd:#" + messageId + ")")
                .replacement(replacement.hoverEvent(HoverEvent.showText(hoverText)))
                .build()
            );
        }

        return message;
    }
    
}
