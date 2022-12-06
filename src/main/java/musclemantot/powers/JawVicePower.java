package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import musclemantot.BingePurgeInterface;
import musclemantot.characters.MuscleManTot;

import java.util.HashMap;

import static musclemantot.MuscleManTotMod.makeID;

public class JawVicePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("JawVice");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public JawVicePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onInitialApplication() {
        // This takes care of when JawVice is added, but for cards created after that,
        //  I just jammed the cost adjustment into the constructor. :/
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.flash();
                card.updateCost(-1);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.updateCost(-1);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.updateCost(-1);
            }
        }
    }

    @Override
    public void onRemove() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.flash();
                card.updateCost(1);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.updateCost(1);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.updateCost(1);
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new JawVicePower(owner);
    }
}
