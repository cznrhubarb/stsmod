package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import musclemantot.MuscleManTotMod;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.CardInfo;

import java.util.Iterator;

import static musclemantot.MuscleManTotMod.makeID;

public class CrunchAndMunch extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            CrunchAndMunch.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public CrunchAndMunch() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            if (m != null) {
                this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
            }

            addToBot(
                    new DamageAction(
                            m,
                            new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                            AbstractGameAction.AttackEffect.NONE)
            );
        }

        addToBot(new ApplyPowerAction(p, p, new BingePower(p, magicNumber)));
    }

    private void updateMagicByBite() {
        int biteCount = 0;
        for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisCombat)
        {
            if (card.hasTag(MuscleManTot.Enums.BITE)) {
                biteCount++;
            }
        }

        setMagic(biteCount);
    }

    public void applyPowers() {
        super.applyPowers();
        updateMagicByBite();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        updateMagicByBite();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new CrunchAndMunch();
    }
}
