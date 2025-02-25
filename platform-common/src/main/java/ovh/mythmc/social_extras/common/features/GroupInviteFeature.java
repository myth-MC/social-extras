package ovh.mythmc.social_extras.common.features;

import ovh.mythmc.gestalt.annotations.Feature;
import ovh.mythmc.gestalt.annotations.conditions.FeatureConditionBoolean;
import ovh.mythmc.gestalt.annotations.status.FeatureDisable;
import ovh.mythmc.gestalt.annotations.status.FeatureEnable;
import ovh.mythmc.social.api.Social;
import ovh.mythmc.social_extras.common.configuration.SocialExtrasSettings;
import ovh.mythmc.social_extras.common.text.keywords.GroupInviteKeyword;

@Feature(group = "social-extras", identifier = "GROUP_INVITE_KEYWORD")
public class GroupInviteFeature {
    
    private final GroupInviteKeyword keyword = new GroupInviteKeyword();

    @FeatureConditionBoolean
    public boolean canBeEnabled() {
        return SocialExtrasSettings.get().getKeywords().getGroupInvite().enabled();
    }

    @FeatureEnable
    public void enable() {
        Social.get().getTextProcessor().registerContextualParser(keyword);
    }

    @FeatureDisable
    public void disable() {
        Social.get().getTextProcessor().unregisterContextualParser(keyword);
    }

}
