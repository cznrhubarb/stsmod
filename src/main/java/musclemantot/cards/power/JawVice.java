package musclemantot.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.powers.JawVicePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class JawVice extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            JawVice.class.getSimpleName(),
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public JawVice() {
        super(cardInfo);

        setInnate(false, true);
        setMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // TODO: Maybe stack amount should be 0?
        addToBot(new ApplyPowerAction(p, p, new JawVicePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new JawVice();
    }
}
