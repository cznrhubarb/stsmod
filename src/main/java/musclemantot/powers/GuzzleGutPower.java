package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import musclemantot.BingePurgeInterface;

import java.util.Iterator;

import static musclemantot.MuscleManTotMod.makeID;

public class GuzzleGutPower extends BasePower implements CloneablePowerInterface, BingePurgeInterface {
    public static final String POWER_ID = makeID("GuzzleGut");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public GuzzleGutPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onBinge(int amount) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying) {
                    this.addToBot(new ApplyPowerAction(m, this.owner, new PoisonPower(m, this.owner, this.amount), this.amount));
                }
            }
        }
    }

    @Override
    public void onPurge() { }

    @Override
    public AbstractPower makeCopy() {
        return new GuzzleGutPower(owner, amount);
    }
}
