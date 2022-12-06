package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.actions.SnatchAction;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Snatch extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Snatch.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int STRENGTH_GAIN = 3;

    public Snatch() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(STRENGTH_GAIN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
            new DamageAction(
                m,
                new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SMASH)
        );
        addToBot(new SnatchAction(magicNumber, m));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Snatch();
    }
}
