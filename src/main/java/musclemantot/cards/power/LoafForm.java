package musclemantot.cards.power;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.FallFromAmysGracePower;
import musclemantot.powers.LoafFormPower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class LoafForm extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            LoafForm.class.getSimpleName(),
            3,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public LoafForm() {
        super(cardInfo);

        tags.add(BaseModCardTags.FORM);
        setEthereal(true, false);
        setMagic(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LoafFormPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LoafForm();
    }
}
