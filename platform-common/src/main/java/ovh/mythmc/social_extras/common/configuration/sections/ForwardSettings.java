package ovh.mythmc.social_extras.common.configuration.sections;

import de.exlll.configlib.Configuration;
import lombok.Getter;

@Configuration
@Getter
public class ForwardSettings {

    private boolean enabled = true;

    private String format = "<dark_gray>(<$(channel_color)>:raw_envelope:</$(channel_color)>)</dark_gray>";

}
