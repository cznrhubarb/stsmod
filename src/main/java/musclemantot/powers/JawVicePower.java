package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import musclemantot.characters.MuscleManTot;

import java.util.HashMap;

import static musclemantot.MuscleManTotMod.makeID;

public class JawVicePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("JawVice");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public JawVicePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

        // TODO: Not sure this works if the card gets added after :(
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                card.modifyCostForCombat(-1);
            }
        }
    }

    // NOTE: If this was to get removed, it doesn't undo the ability. But that doesn't happen in the game so whatever.

    @Override
    public AbstractPower makeCopy() {
        return new JawVicePower(owner, amount);
    }
}
