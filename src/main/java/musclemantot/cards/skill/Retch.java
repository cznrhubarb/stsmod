package musclemantot.cards.skill;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Retch extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Retch.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public Retch() {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Retch();
    }
}
