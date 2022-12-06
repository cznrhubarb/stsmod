package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import musclemantot.BingePurgeInterface;

import static musclemantot.MuscleManTotMod.makeID;

public class LiquidLaughPower extends BasePower implements CloneablePowerInterface, BingePurgeInterface {
    public static final String POWER_ID = makeID("LiquidLaugh");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LiquidLaughPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onBinge(int amount) { }

    @Override
    public void onPurge(int amount) {
        int strengthBonus = FallFromAmysGracePower.getStrengthBonusIfApplicable(this.owner);

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            if (strengthBonus > 0) {
                this.owner.getPower(FallFromAmysGracePower.POWER_ID).flash();
            }

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying) {
                    this.addToBot(new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount + strengthBonus)));
                }
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new LiquidLaughPower(owner, amount);
    }
}
