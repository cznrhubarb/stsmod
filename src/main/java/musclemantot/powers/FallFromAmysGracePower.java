package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static musclemantot.MuscleManTotMod.makeID;

public class FallFromAmysGracePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("FallFromAmysGrace");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public FallFromAmysGracePower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    // TODO: Instead of making this work in a correct way, I am simply
    //  hacking it into the five other things in this mod that applies poison.
    //  What does that mean? Well it means that if Tot picks up any Silent
    //  cards that deal Poison, they're not gonna get Strength modified :(

    @Override
    public AbstractPower makeCopy() {
        return new FallFromAmysGracePower(owner);
    }

    public static int getStrengthBonusIfApplicable(AbstractCreature p) {
        int strengthBonus = 0;
        if (p.hasPower(FallFromAmysGracePower.POWER_ID)) {
            AbstractPower strengthPower = p.getPower(StrengthPower.POWER_ID);
            if (strengthPower != null && strengthPower.amount > 0) {
                strengthBonus = strengthPower.amount;
            }
        }

        return strengthBonus;
    }
}
