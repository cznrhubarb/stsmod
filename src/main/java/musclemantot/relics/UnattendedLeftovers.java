package musclemantot.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.BingePurgeInterface;
import musclemantot.characters.MuscleManTot;

import static musclemantot.MuscleManTotMod.makeID;

public class UnattendedLeftovers extends BaseRelic implements BingePurgeInterface {
    private static final String NAME = UnattendedLeftovers.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public UnattendedLeftovers() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onBinge(int amount) { }

    @Override
    public void onPurge(int amount) {
        addToBot(new DrawCardAction(1));
    }
}
