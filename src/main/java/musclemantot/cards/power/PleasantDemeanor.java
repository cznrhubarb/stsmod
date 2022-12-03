package musclemantot.cards.power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.powers.PleasantDemeanorPower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class PleasantDemeanor extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            PleasantDemeanor.class.getSimpleName(),
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public PleasantDemeanor() {
        super(cardInfo);

        setInnate(false, true);
        setMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PleasantDemeanorPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PleasantDemeanor();
    }
}
