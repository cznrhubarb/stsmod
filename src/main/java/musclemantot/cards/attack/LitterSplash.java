package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class LitterSplash extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            LitterSplash.class.getSimpleName(),
            2,
            CardType.ATTACK,
            CardTarget.ALL,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 10;
    private static final int SLIMED_CARDS = 2;

    public LitterSplash() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(SLIMED_CARDS);
        this.cardsToPreview = new Slimed();
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        addToBot(new MakeTempCardInDiscardAction(new Slimed(), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LitterSplash();
    }
}
