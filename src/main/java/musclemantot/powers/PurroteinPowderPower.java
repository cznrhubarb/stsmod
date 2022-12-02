package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static musclemantot.MuscleManTotMod.makeID;

public class PurroteinPowderPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("PurroteinPowder");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PurroteinPowderPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PurroteinPowderPower(owner, amount);
    }
}
