package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import musclemantot.BingePurgeInterface;

import static musclemantot.MuscleManTotMod.makeID;

public class FiveToeDiscountPower extends BasePower implements CloneablePowerInterface, BingePurgeInterface {
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
    public void onBinge(int _amount) {
        this.flash();
        addToBot(new GainEnergyAction(this.amount));
    }

    @Override
    public void onPurge(int amount) { }

    @Override
    public AbstractPower makeCopy() {
        return new FiveToeDiscountPower(owner, amount);
    }
}
