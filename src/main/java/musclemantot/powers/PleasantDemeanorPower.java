package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static musclemantot.MuscleManTotMod.makeID;

public class PleasantDemeanorPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("PleasantDemeanor");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PleasantDemeanorPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            int enemiesDefeated = 0;
            for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (mon.isDead) {
                    enemiesDefeated++;
                }
            }
            this.flash();
            p.increaseMaxHp(this.amount * enemiesDefeated, true);
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new PleasantDemeanorPower(owner, amount);
    }
}
