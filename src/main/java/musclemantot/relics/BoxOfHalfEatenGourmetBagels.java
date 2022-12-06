package musclemantot.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.BingePurgeInterface;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;

import static musclemantot.MuscleManTotMod.makeID;

public class BoxOfHalfEatenGourmetBagels extends BaseRelic implements BingePurgeInterface {
    private static final String NAME = BoxOfHalfEatenGourmetBagels.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public BoxOfHalfEatenGourmetBagels() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void obtain() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player != null && player.hasRelic(HalfEatenGourmetBagel.ID)) {
            int index = player.relics.indexOf(player.getRelic(HalfEatenGourmetBagel.ID));
            instantObtain(player, index, true);
        } else {
            super.obtain();
        }
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(HalfEatenGourmetBagel.ID);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BingePower(AbstractDungeon.player, 1)));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void onBinge(int amount) { }

    @Override
    public void onPurge(int amount) {
        this.flash();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BingePower(AbstractDungeon.player, 1)));
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
