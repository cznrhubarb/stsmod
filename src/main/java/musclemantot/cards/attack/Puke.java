package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Puke extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Puke.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int POISON = 2;

    public Puke() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(POISON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower fallFromGracePower = p.getPower(FallFromAmysGracePower.POWER_ID);
        if (fallFromGracePower != null && this.magicNumber != this.baseMagicNumber) {
            fallFromGracePower.flash();
        }

        for (int i = 0; i < BingeUtil.getPlayerBinge(true); i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber)));
        }
        addToBot(new RemoveSpecificPowerAction(p, p, BingePower.POWER_ID));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.magicNumber = this.baseMagicNumber + FallFromAmysGracePower.getStrengthBonusIfApplicable(AbstractDungeon.player);
        this.isMagicNumberModified = this.magicNumber != this.baseMagicNumber;
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.magicNumber = this.baseMagicNumber;
        this.isMagicNumberModified = false;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.magicNumber = this.baseMagicNumber + FallFromAmysGracePower.getStrengthBonusIfApplicable(AbstractDungeon.player);
        this.isMagicNumberModified = this.magicNumber != this.baseMagicNumber;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Puke();
    }
}
