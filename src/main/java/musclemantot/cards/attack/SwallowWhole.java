package musclemantot.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import musclemantot.actions.SwallowWholeAction;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.powers.JawVicePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class SwallowWhole extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            SwallowWhole.class.getSimpleName(),
            1,
            CardType.ATTACK,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.COMMON,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public SwallowWhole() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SwallowWholeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwallowWhole();
    }
}
