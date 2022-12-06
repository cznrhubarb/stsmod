package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class CatNap extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            CatNap.class.getSimpleName(),
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int BINGE_THRESHOLD = 2;
    private static final int UPG_BINGE_THRESHOLD = 1;

    public CatNap() {
        super(cardInfo);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(BINGE_THRESHOLD, UPG_BINGE_THRESHOLD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (BingeUtil.getPlayerBinge(false) >= magicNumber) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean glow = BingeUtil.getPlayerBinge(false) < magicNumber;

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CatNap();
    }
}
