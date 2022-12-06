package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class Prowl extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            Prowl.class.getSimpleName(),
            1,
            CardType.SKILL,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int VULNERABLE = 3;
    private static final int UPG_VULNERABLE = 2;

    public Prowl() {
        super(cardInfo);

        setMagic(VULNERABLE, UPG_VULNERABLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster _m) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDead && !m.isDying) {
                this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Prowl();
    }
}
