package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import musclemantot.util.BingeUtil;

import static musclemantot.MuscleManTotMod.makeID;

public class EyeOfTheTotgerPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("EyeOfTheTotger");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public EyeOfTheTotgerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean _isPlayer) {
        this.flash();
        this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BingeUtil.getPlayerBinge() * this.amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new EyeOfTheTotgerPower(owner, amount);
    }
}
