package ovh.mythmc.social_extras.common.listeners;

import ovh.mythmc.callbacks.key.IdentifierKey;
import ovh.mythmc.social.api.callback.message.SocialMessageReceiveCallback;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;

public class ProximityListener {

    public void registerCallbackHandlers() {
        SocialMessageReceiveCallback.INSTANCE.registerHandler("social-extras:proximity-handler", (ctx) -> {
            if (ctx.sender() == null || ctx.recipient() == null)
                return;

            SocialExtrasSettings.get().getProximity().getChannels().forEach(proximityChannel -> {
                if (proximityChannel.name().equalsIgnoreCase(ctx.channel().getName())) {
                    double distance = ctx.sender().player().get().getLocation().distance(ctx.recipient().player().get().getLocation());

                    if (distance > proximityChannel.radiusInBlocks())
                        ctx.cancelled(true);
                }
            });
        });
    }

    public void unregisterCallbackHandlers() {
        SocialMessageReceiveCallback.INSTANCE.unregisterHandlers(IdentifierKey.of("social-extras:proximity-listener"));
    }
    
}
