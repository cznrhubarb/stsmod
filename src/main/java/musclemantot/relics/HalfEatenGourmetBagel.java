package musclemantot.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;

import static musclemantot.MuscleManTotMod.makeID;

public class HalfEatenGourmetBagel extends BaseRelic {
    private static final String NAME = HalfEatenGourmetBagel.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public HalfEatenGourmetBagel() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BingePower(AbstractDungeon.player, 1)));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
