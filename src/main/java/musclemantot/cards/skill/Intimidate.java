package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.ThirdEyeEffect;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Intimidate extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Intimidate.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    // TODO: These could all be magic numbers, but I'm too lazy to bother figuring out how to set them up.
    //  The values don't change on upgrade anyway. Only risk is updating the values and not the strings...
    private static final int BINGE_THRESHOLD = 3;
    private static final int STRENGTH_LOSS = 2;
    private static final int VULNERABLE = 3;

    public Intimidate() {
        super(cardInfo);

        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster mon) {
        if (p != null) {
            this.addToBot(new VFXAction(new ThirdEyeEffect(p.hb.cX, p.hb.cY)));
        }

        this.addToBot(new ApplyPowerAction(mon, p, new StrengthPower(mon, -STRENGTH_LOSS), -STRENGTH_LOSS, true, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ApplyPowerAction(mon, p, new VulnerablePower(mon, VULNERABLE, false), VULNERABLE, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (BingeUtil.getPlayerBinge(false) < BINGE_THRESHOLD) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean glow = BingeUtil.getPlayerBinge(false) >= BINGE_THRESHOLD;

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Intimidate();
    }
}
