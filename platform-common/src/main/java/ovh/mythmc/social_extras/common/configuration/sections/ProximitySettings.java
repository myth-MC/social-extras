package ovh.mythmc.social_extras.common.configuration.sections;

import java.util.List;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;

@Configuration
@Getter
public class ProximitySettings {
    
    private boolean enabled = true;

    @Comment("You can use the channel's radius in channel descriptions with $(channel_radius)")
    public List<ProximityChannel> channels = List.of(
        new ProximityChannel("local", 32)
    );

    public record ProximityChannel(String name, int radiusInBlocks) { }
    
}
