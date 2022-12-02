package musclemantot.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.powers.GuzzleGutPower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class GuzzleGut extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            GuzzleGut.class.getSimpleName(),
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public GuzzleGut() {
        super(cardInfo);

        setCostUpgrade(1);
        setMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GuzzleGutPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuzzleGut();
    }
}
