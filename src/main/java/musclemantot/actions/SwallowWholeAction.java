package musclemantot.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import musclemantot.powers.BingePower;

public class SwallowWholeAction extends AbstractGameAction {
    private DamageInfo info;
    private int bingeAmount;

    public SwallowWholeAction(AbstractCreature target, DamageInfo info, int bingeAmount) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.bingeAmount = bingeAmount;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            this.tickDuration();
            if (this.isDone) {
                AbstractDungeon.effectList.add(new BiteEffect(this.target.hb.cX, this.target.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()));
                boolean hadBlock = this.target.currentBlock > 0;
                this.target.damage(this.info);
                if (this.target.currentBlock == 0 && hadBlock) {
                    this.addToTop(new ApplyPowerAction(this.source, this.source, new BingePower(this.source, bingeAmount)));
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    this.addToTop(new WaitAction(0.1F));
                }
            }
        }
    }
}
