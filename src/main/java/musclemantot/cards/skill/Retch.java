package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Retch extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Retch.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int POISON = 3;

    public Retch() {
        super(cardInfo);

        setCostUpgrade(0);
        setMagic(POISON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower bingePower = p.getPower(BingePower.POWER_ID);
        int bingeAmount = 0;
        if (bingePower != null) {
            bingeAmount = bingePower.amount;
        }

        for (int i = 0; i < bingeAmount; i++) {
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber)));
        }

        addToBot(new RemoveSpecificPowerAction(p, p, bingePower));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Retch();
    }
}
