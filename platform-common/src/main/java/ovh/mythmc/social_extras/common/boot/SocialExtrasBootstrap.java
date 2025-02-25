package ovh.mythmc.social_extras.common.boot;

import org.bukkit.plugin.java.JavaPlugin;

import ovh.mythmc.gestalt.Gestalt;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasConfigProvider;
import ovh.mythmc.social_extras.common.features.BasicAddonFeature;
import ovh.mythmc.social_extras.common.features.ForwardFeature;
import ovh.mythmc.social_extras.common.features.GroupInviteFeature;
import ovh.mythmc.social_extras.common.features.ProximityFeature;
import ovh.mythmc.social_extras.common.features.ShortcutsFeature;
import ovh.mythmc.social_extras.common.listeners.SocialListener;

public class SocialExtrasBootstrap {

    private SocialExtrasConfigProvider config;

    public SocialExtrasBootstrap(final JavaPlugin plugin) {
        this.config = new SocialExtrasConfigProvider(plugin.getDataFolder());
    }

    public void initialize() {
        // Register features (Gestalt)
        Gestalt.get().register(
            BasicAddonFeature.class,
            ForwardFeature.class,
            GroupInviteFeature.class,
            ProximityFeature.class,
            ShortcutsFeature.class
        );

        // Register feature listener (makes /social reload effective here too)
        Gestalt.get().getListenerRegistry().register(new SocialListener(), true);
    }

    public void shutdown() {
        Gestalt.get().disableAllFeatures("social-extras");
    }

    public void reload() {
        config.load();
    }

    private static SocialExtrasBootstrap bootstrap;

    public static void set(SocialExtrasBootstrap b) {
        if (bootstrap == null)
            bootstrap = b;
    }

    public static SocialExtrasBootstrap get() {
        return bootstrap;
    }
    
}
