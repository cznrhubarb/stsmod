package musclemantot.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;

import static musclemantot.MuscleManTotMod.makeID;

public class BoxOfHalfEatenGourmetBagels extends BaseRelic {
    private static final String NAME = BoxOfHalfEatenGourmetBagels.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public BoxOfHalfEatenGourmetBagels() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    // TODO: How does this replace the other starter relic?

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(HalfEatenGourmetBagel.ID);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BingePower(AbstractDungeon.player, 1)));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    // TODO: Need a custom trigger for when purge happens here
}
