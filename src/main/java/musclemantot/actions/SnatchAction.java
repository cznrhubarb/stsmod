package musclemantot.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SnatchAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final int strengthAmount;
    private final AbstractMonster targetMonster;

    public SnatchAction(int strengthAmount, AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.strengthAmount = strengthAmount;
        this.targetMonster = m;
    }

    public void update() {
        if (this.targetMonster != null && intentIsBuff(this.targetMonster.intent)) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.strengthAmount), this.strengthAmount));
        }

        this.isDone = true;
    }

    private boolean intentIsBuff(AbstractMonster.Intent intent) {
        return intent == AbstractMonster.Intent.BUFF ||
                intent == AbstractMonster.Intent.ATTACK_BUFF ||
                intent == AbstractMonster.Intent.DEFEND_BUFF;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
        TEXT = uiStrings.TEXT;
    }
}
