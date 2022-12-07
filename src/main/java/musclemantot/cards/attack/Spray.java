package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Spray extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Spray.class.getSimpleName(),
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 3;

    public Spray() {
        super(cardInfo);

        setDamage(DAMAGE);
        setCostUpgrade(1);
        setMagic(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < m.powers.size(); i++) {
            addToBot(
                    new DamageAction(
                            m,
                            new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                            AbstractGameAction.AttackEffect.POISON)
            );
        }
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        if (mo != null) {
            setMagic(mo.powers.size());
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Spray();
    }
}
