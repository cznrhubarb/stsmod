package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class BulkUp extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            BulkUp.class.getSimpleName(),
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int STRENGTH = 1;

    public BulkUp() {
        super(cardInfo);

        setMagic(STRENGTH);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower bingePower = p.getPower(BingePower.POWER_ID);
        int bingeAmount = 0;
        if (bingePower != null) {
            bingeAmount = bingePower.amount;
        }

        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, bingeAmount * magicNumber)));
        addToBot(new RemoveSpecificPowerAction(p, p, bingePower));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BulkUp();
    }
}
