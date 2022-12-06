package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import java.util.Iterator;

import static musclemantot.MuscleManTotMod.makeID;

public class Blep extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Blep.class.getSimpleName(),
            0,
            CardType.SKILL,
            CardTarget.ALL,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int SELF_STRENGTH_LOSS = 3;
    private static final int ENEMY_STRENGTH_LOSS = 5;
    private static final int UPG_ENEMY_STRENGTH_LOSS = 3;

    public Blep() {
        super(cardInfo);

        setMagic(ENEMY_STRENGTH_LOSS, UPG_ENEMY_STRENGTH_LOSS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        } else {
            this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
        }

        // TODO: This uses Piercing Wail as a model, but it could maybe use Flex as a model to create synergy for the player with Shed.
        //  The downside is that the card text would have to change to match the Flex model as well, and that would be long af.

        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -SELF_STRENGTH_LOSS), -SELF_STRENGTH_LOSS, true, AbstractGameAction.AttackEffect.NONE));
        if (!p.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(p, p, new GainStrengthPower(p, SELF_STRENGTH_LOSS), SELF_STRENGTH_LOSS, true, AbstractGameAction.AttackEffect.NONE));
        }

        for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mon, p, new StrengthPower(mon, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            if (!mon.hasPower("Artifact")) {
                this.addToBot(new ApplyPowerAction(mon, p, new GainStrengthPower(mon, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (noAttacksPlayedYet()) {
                return true;
            } else {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean glow = noAttacksPlayedYet();

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    private boolean noAttacksPlayedYet() {
        for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (card.type == CardType.ATTACK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Blep();
    }
}
