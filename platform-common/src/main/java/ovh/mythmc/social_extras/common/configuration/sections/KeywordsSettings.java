package ovh.mythmc.social_extras.common.configuration.sections;

import de.exlll.configlib.Configuration;
import lombok.Getter;

@Configuration
@Getter
public class KeywordsSettings {

    private Keyword groupInvite = new Keyword(true, "<dark_gray>Click to join this group</dark_gray>");

    public record Keyword(boolean enabled, String hoverText) { }
    
}
