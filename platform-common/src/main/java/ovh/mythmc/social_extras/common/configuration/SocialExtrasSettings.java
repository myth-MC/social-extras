package ovh.mythmc.social_extras.common.configuration;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;
import ovh.mythmc.social_extras.common.configuration.sections.ForwardSettings;
import ovh.mythmc.social_extras.common.configuration.sections.KeywordsSettings;
import ovh.mythmc.social_extras.common.configuration.sections.ProximitySettings;
import ovh.mythmc.social_extras.common.configuration.sections.ShortcutsSettings;

@Configuration
@Getter
public class SocialExtrasSettings {

    public static SocialExtrasSettings get() { return SocialExtrasConfigProvider.get(); }

    @Comment("Forward messages by using (fwd:#id)")
    private ForwardSettings forward = new ForwardSettings();

    @Comment({"", "Keywords module"})
    private KeywordsSettings keywords = new KeywordsSettings();

    @Comment({"", "Proximity-based channels"})
    private ProximitySettings proximity = new ProximitySettings();

    @Comment({"", "Channel shortcuts"})
    private ShortcutsSettings shortcuts = new ShortcutsSettings();
    
}
