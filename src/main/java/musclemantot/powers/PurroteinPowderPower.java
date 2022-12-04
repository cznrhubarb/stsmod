package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import musclemantot.BingePurgeInterface;

import static musclemantot.MuscleManTotMod.makeID;

public class PurroteinPowderPower extends BasePower implements CloneablePowerInterface, BingePurgeInterface {
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
    public void onBinge(int _amount) {
        this.flash();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount)));
    }

    @Override
    public void onPurge() { }

    @Override
    public AbstractPower makeCopy() {
        return new PurroteinPowderPower(owner, amount);
    }
}
