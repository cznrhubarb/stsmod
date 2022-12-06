package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import musclemantot.powers.BingePower;
import musclemantot.powers.JawVicePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class GnawingBite extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            GnawingBite.class.getSimpleName(),
            2,
            CardType.ATTACK,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 10;
    private static final int BINGE_GAIN = 1;
    private static final int UPG_BINGE_GAIN = 1;

    public GnawingBite() {
        super(cardInfo);

        tags.add(MuscleManTot.Enums.BITE);
        setDamage(DAMAGE);
        setMagic(BINGE_GAIN, UPG_BINGE_GAIN);

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

        addToBot(new ApplyPowerAction(p, p, new BingePower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GnawingBite();
    }
}
