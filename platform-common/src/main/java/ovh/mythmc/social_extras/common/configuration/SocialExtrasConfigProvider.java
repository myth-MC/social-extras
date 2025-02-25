package ovh.mythmc.social_extras.common.configuration;

import java.io.File;
import java.nio.file.Path;

import org.jetbrains.annotations.NotNull;

import de.exlll.configlib.YamlConfigurations;
import lombok.Getter;

@Getter
public final class SocialExtrasConfigProvider {

    private static SocialExtrasSettings settings;
    private final Path settingsFilePath;

    public SocialExtrasConfigProvider(final @NotNull File pluginFolder) {
        settings = new SocialExtrasSettings();
        this.settingsFilePath = new File(pluginFolder, "settings.yml").toPath();
    }

    public void load() {
        settings = YamlConfigurations.update(settingsFilePath, SocialExtrasSettings.class);
    }

    public static SocialExtrasSettings get() { return settings; }
    
}
