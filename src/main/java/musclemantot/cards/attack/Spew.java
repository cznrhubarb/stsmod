package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Spew extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Spew.class.getSimpleName(),
            2,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 5;

    public Spew() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < BingeUtil.getPlayerBinge(true); i++) {
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.POISON));
        }
        addToBot(new RemoveSpecificPowerAction(p, p, BingePower.POWER_ID));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Spew();
    }
}
