package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static musclemantot.MuscleManTotMod.makeID;

public class Regurgitate extends BaseCard {
    private static final Logger logger = LogManager.getLogger(AbstractCard.class.getName());
    private final static CardInfo cardInfo = new CardInfo(
            Regurgitate.class.getSimpleName(),
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 3;

    public Regurgitate() {
        super(cardInfo);

        setDamage(DAMAGE);

        setExhaust(true, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < BingeUtil.getPlayerBinge(true); i++) {
            addToBot(
                    new DamageAction(
                            m,
                            new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                            AbstractGameAction.AttackEffect.POISON)
            );
        }
        addToBot(new RemoveSpecificPowerAction(p, p, BingePower.POWER_ID));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Regurgitate();
    }
}
