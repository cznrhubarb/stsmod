package musclemantot.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.EyeOfTheTotgerPower;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class FallFromAmysGrace extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            FallFromAmysGrace.class.getSimpleName(),
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public FallFromAmysGrace() {
        super(cardInfo);

        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FallFromAmysGracePower(p, 1), 0));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FallFromAmysGrace();
    }
}
