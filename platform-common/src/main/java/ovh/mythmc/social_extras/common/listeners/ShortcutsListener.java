package ovh.mythmc.social_extras.common.listeners;

import ovh.mythmc.callbacks.key.IdentifierKey;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social.api.callback.message.SocialMessagePrepareCallback;
import ovh.mythmc.social.api.chat.ChatChannel;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;
import ovh.mythmc.social_extras.common.configuration.sections.ShortcutsSettings.Shortcut;

public final class ShortcutsListener {

    public void registerCallbackHandlers() {
        SocialMessagePrepareCallback.INSTANCE.registerHandler("social-extras:shortcuts-handler", (ctx) -> {
            for (Shortcut shortcut : SocialExtrasSettings.get().getShortcuts().getShortcuts()) {
                if (!ctx.plainMessage().startsWith(shortcut.prefix()))
                    continue;
    
                ChatChannel channel = Social.get().getChatManager().getChannel(shortcut.channelName());
                if (channel == null)
                    continue;
    
                if (!Social.get().getChatManager().hasPermission(ctx.sender(), channel))
                    continue;
    
                ctx.plainMessage(ctx.plainMessage().replaceFirst(shortcut.prefix(), "").trim());
                ctx.channel(channel);

                break;
            }
        });
    }

    public void unregisterCallbackHandlers() {
        SocialMessagePrepareCallback.INSTANCE.unregisterHandlers(IdentifierKey.of("social-extras:shortcuts-handler"));
    }
    
}
