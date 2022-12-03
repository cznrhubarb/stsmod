package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static musclemantot.MuscleManTotMod.makeID;

public class FiveToeDiscountPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("FiveToeDiscount");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public FiveToeDiscountPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        StringBuilder energyString = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            energyString.append(" [E]");
        }

        this.description = DESCRIPTIONS[0] + energyString + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new FiveToeDiscountPower(owner, amount);
    }
}
