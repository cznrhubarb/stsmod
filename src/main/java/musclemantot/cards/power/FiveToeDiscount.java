package musclemantot.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.powers.FiveToeDiscountPower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class FiveToeDiscount extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            FiveToeDiscount.class.getSimpleName(),
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public FiveToeDiscount() {
        super(cardInfo);

        setEthereal(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FiveToeDiscountPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FiveToeDiscount();
    }
}
