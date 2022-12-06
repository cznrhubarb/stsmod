package musclemantot.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import musclemantot.BingePurgeInterface;
import musclemantot.cards.BaseCard;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.BingePower;
import musclemantot.util.CardInfo;

import static musclemantot.MuscleManTotMod.makeID;

public class DeepSlumber extends BaseCard implements BingePurgeInterface {
    private final static CardInfo cardInfo = new CardInfo(
            DeepSlumber.class.getSimpleName(),
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            MuscleManTot.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;

    public DeepSlumber() {
        super(cardInfo);

        setBlock(BLOCK, UPG_BLOCK);

        boolean inCombat = false;
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null) {
            for (AbstractCreature m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDeadOrEscaped()) {
                    inCombat = true;
                    break;
                }
            }
        }

        if (inCombat) {
            setMagic(BingePower.getBingesThisCombat());
        } else {
            setMagic(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new GainBlockAction(p, p, block));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onBinge(int amount) {
        setMagic(BingePower.getBingesThisCombat());
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onPurge(int amount) { }

    @Override
    public AbstractCard makeCopy() {
        return new DeepSlumber();
    }
}
