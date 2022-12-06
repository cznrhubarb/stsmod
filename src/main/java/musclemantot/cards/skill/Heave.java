package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Heave extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Heave.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public Heave() {
        super(cardInfo);

        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower bingePower = p.getPower(BingePower.POWER_ID);
        int bingeAmount = 0;
        if (bingePower != null) {
            bingeAmount = bingePower.amount;
        }

        addToBot(new GainEnergyAction(bingeAmount));
        addToBot(new RemoveSpecificPowerAction(p, p, bingePower));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Heave();
    }
}
