package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Hork extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Hork.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 13;
    private static final int UPG_DAMAGE = 5;

    private static final int DEXTERITY = 1;

    public Hork() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DEXTERITY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
            new DamageAction(
                m,
                new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL)
        );
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -this.magicNumber), -this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Hork();
    }
}
