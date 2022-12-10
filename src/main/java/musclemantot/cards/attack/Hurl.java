package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Hurl extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Hurl.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;
    private static final int POISON = 2;

    public Hurl() {
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
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (target != null) {
                calculateCardDamage(target);
                this.addToBot(new DamageAction(target, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                this.addToBot(new ApplyPowerAction(target, p, new PoisonPower(target, p, magicNumber)));
            }
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
        return new Hurl();
    }
}
