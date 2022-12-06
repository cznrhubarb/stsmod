package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.defect.AllCostToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.actions.AllTagToHandAction;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class SneakABite extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            SneakABite.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public SneakABite() {
        super(cardInfo);

        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AllTagToHandAction(MuscleManTot.Enums.BITE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SneakABite();
    }
}
