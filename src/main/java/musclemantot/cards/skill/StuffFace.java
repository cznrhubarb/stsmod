package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class StuffFace extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            StuffFace.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BINGE_GAIN = 1;
    private static final int UPG_BINGE_GAIN = 1;

    public StuffFace() {
        super(cardInfo);

        setCostUpgrade(0);
        setMagic(BINGE_GAIN, UPG_BINGE_GAIN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BingePower(p, BINGE_GAIN)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StuffFace();
    }
}
