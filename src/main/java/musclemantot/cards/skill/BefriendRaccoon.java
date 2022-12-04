package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class BefriendRaccoon extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            BefriendRaccoon.class.getSimpleName(),
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int CARD_COUNT = 3;

    public BefriendRaccoon() {
        super(cardInfo);

        setCostUpgrade(1);
        setMagic(CARD_COUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardPileToHandAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BefriendRaccoon();
    }
}
