package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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

public class Sunbathe extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Sunbathe.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public static final int BLOCK = 6;
    public static final int UPG_BLOCK = 3;

    public Sunbathe() {
        super(cardInfo);

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower bingePower = p.getPower(BingePower.POWER_ID);
        int bingeAmount = 0;
        if (bingePower != null) {
            bingeAmount = bingePower.amount;
        }

        for (int i = 0; i < bingeAmount; i++) {
            addToBot(new GainBlockAction(p, p, block));
        }

        addToBot(new RemoveSpecificPowerAction(p, p, bingePower));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sunbathe();
    }
}
