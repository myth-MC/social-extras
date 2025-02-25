package ovh.mythmc.social_extras.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

import ovh.mythmc.social_extras.common.boot.SocialExtrasBootstrap;

public class SocialExtrasPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        SocialExtrasBootstrap.set(new SocialExtrasBootstrap(this));
        SocialExtrasBootstrap.get().initialize();
    }

    @Override
    public void onDisable() {
        SocialExtrasBootstrap.get().shutdown();
    }
    
}
