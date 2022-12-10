package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import musclemantot.BingePurgeInterface;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.powers.JawVicePower;
import musclemantot.util.BingeUtil;
import musclemantot.util.CardInfo;

import java.util.Iterator;

import static musclemantot.MuscleManTotMod.makeID;

public class BigBite extends BaseCard implements BingePurgeInterface {
    private final static CardInfo cardInfo = new CardInfo(
            BigBite.class.getSimpleName(),
            4,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;

    public BigBite() {
        super(cardInfo);

        tags.add(MuscleManTot.Enums.BITE);
        setDamage(DAMAGE, UPG_DAMAGE);

        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null) {
            this.configureCostsOnNewCard();
        }
    }

    public void configureCostsOnNewCard() {
        AbstractPower bingePower = AbstractDungeon.player.getPower(BingePower.POWER_ID);
        if (bingePower != null) {
            this.updateCost(-bingePower.amount);
        }

        if (AbstractDungeon.player.hasPower(JawVicePower.POWER_ID)) {
            this.updateCost(-1);
        }
    }

    @Override
    public void onBinge(int amount) {
        this.updateCost(amount);
    }

    @Override
    public void onPurge(int amount) {
        this.updateCost(amount);
        if (this.baseCost == this.cost) {
            this.isCostModified = false;
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
    }

    @Override
    public AbstractCard makeCopy() {
        return new BigBite();
    }
}
