package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BackAttackPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import java.util.Iterator;

import static musclemantot.MuscleManTotMod.makeID;

public class SaunterAway extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            SaunterAway.class.getSimpleName(),
            3,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BINGE_THRESHOLD = 4;
    private static final int UPG_BINGE_THRESHOLD = -1;

    public SaunterAway() {
        super(cardInfo);

        setMagic(BINGE_THRESHOLD, UPG_BINGE_THRESHOLD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.getCurrRoom().smoked = true;
            AbstractDungeon.player.hideHealthBar();
            AbstractDungeon.player.isEscaping = true;
            AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
            AbstractDungeon.overlayMenu.endTurnButton.disable();
            AbstractDungeon.player.escapeTimer = 2.5F;
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            if (anyMonsterIsBoss()) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[1];
                return false;
            } else if (anyMonsterHasBackAttack()) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[2];
                return false;
            } else if (BingeUtil.getPlayerBinge(false) < this.magicNumber) {
                this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean glow = !anyMonsterIsBoss() && !anyMonsterHasBackAttack() && BingeUtil.getPlayerBinge(false) >= this.magicNumber;

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    private boolean anyMonsterIsBoss() {
        for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mon.type == AbstractMonster.EnemyType.BOSS) {
                return true;
            }
        }

        return false;
    }

    private boolean anyMonsterHasBackAttack() {
        for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mon.hasPower(BackAttackPower.POWER_ID)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new SaunterAway();
    }
}
