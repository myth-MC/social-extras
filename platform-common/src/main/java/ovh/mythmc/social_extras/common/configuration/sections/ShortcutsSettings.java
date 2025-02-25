package ovh.mythmc.social_extras.common.configuration.sections;

import java.util.List;

import de.exlll.configlib.Configuration;
import lombok.Getter;

@Configuration
@Getter
public class ShortcutsSettings {
    
    private boolean enabled = true;

    private List<Shortcut> shortcuts = List.of(
        new Shortcut("staff", "#"),
        new Shortcut("example", "!")
    );

    public record Shortcut(String channelName, String prefix) { }

}
