package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class LickWounds extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            LickWounds.class.getSimpleName(),
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BINGE_THRESHOLD = 3;
    private static final int HEAL = 7;
    private static final int UPG_HEAL = 3;

    public LickWounds() {
        super(cardInfo);

        setMagic(BINGE_THRESHOLD);
        setMagic(HEAL, UPG_HEAL);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.getCurrRoom().smoked = true;

        }

        this.addToBot(new HealAction(p, p, this.magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (BingeUtil.getPlayerBinge(false) < BINGE_THRESHOLD) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean glow = BingeUtil.getPlayerBinge(false) >= BINGE_THRESHOLD;

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LickWounds();
    }
}
