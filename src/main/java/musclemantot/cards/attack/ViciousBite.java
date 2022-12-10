package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.JawVicePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class
ViciousBite extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            ViciousBite.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;

    private static final int HEALTH = 2;

    public ViciousBite() {
        super(cardInfo);

        tags.add(MuscleManTot.Enums.BITE);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(HEALTH);

        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && AbstractDungeon.player.hasPower(JawVicePower.POWER_ID)) {
            this.updateCost(-1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
            } else {
                this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
            }
        }

        addToBot(
            new DamageAction(
                m,
                new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.NONE)
        );
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ViciousBite();
    }
}
