package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static musclemantot.MuscleManTotMod.makeID;

public class LoafFormPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("LoafForm");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LoafFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new BingePower(this.owner, this.amount), this.amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoafFormPower(owner, amount);
    }
}
