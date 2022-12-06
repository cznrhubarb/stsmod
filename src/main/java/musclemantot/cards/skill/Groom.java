package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Groom extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Groom.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DRAW = 5;

    private static final int DISCARD = 5;

    public Groom() {
        super(cardInfo);

        setMagic(DRAW);
        setMagic(DISCARD);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new DiscardAction(p, p, this.magicNumber, false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Groom();
    }
}
