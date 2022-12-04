package musclemantot.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.BingeUtil;

import static musclemantot.MuscleManTotMod.makeID;

public class CatHammock extends BaseRelic {
    private static final String NAME = CatHammock.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public CatHammock() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    public void onVictory() {
        int bingeAmount = Math.min(10, BingeUtil.getPlayerBinge(false));

        if (bingeAmount > 0) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractPlayer p = AbstractDungeon.player;
            if (p.currentHealth > 0) {
                p.heal(bingeAmount);
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
