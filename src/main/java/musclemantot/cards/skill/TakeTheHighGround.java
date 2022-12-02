package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import musclemantot.cards.BaseCard;
import musclemantot.cards.attack.Bully;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class TakeTheHighGround extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            TakeTheHighGround.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int VULNERABLE = 2;
    private static final int UPG_VULNERABLE = 1;

    public TakeTheHighGround() {
        super(cardInfo);

        setMagic(VULNERABLE, UPG_VULNERABLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new TakeTheHighGround();
    }
}


