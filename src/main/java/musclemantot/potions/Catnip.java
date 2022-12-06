package musclemantot.potions;

import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import musclemantot.powers.BingePower;

import static musclemantot.MuscleManTotMod.makeID;

public class Catnip extends CustomPotion {
    public static final String POTION_ID = makeID("Catnip");
    public static final PotionRarity POTION_RARITY = PotionRarity.COMMON;
    public static final PotionSize POTION_SIZE = PotionSize.BOTTLE;
    public static final PotionColor COLOR = PotionColor.GREEN;
    public static final Color LIQUID_COLOR = Color.FOREST;
    public static final Color HYBRID_COLOR = Color.DARK_GRAY;
    public static final Color SPOTS_COLOR = Color.CHARTREUSE;
    private static final PotionStrings potionStrings;
    private static final String BINGE_KEYWORD = "musclemantot:binge";

    public Catnip() {
        // TODO: Custom images would be nice here :/
        super(potionStrings.NAME, POTION_ID, POTION_RARITY, POTION_SIZE, COLOR);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordProper(BINGE_KEYWORD)), BaseMod.getKeywordDescription(BINGE_KEYWORD)));
    }

    public void use(AbstractCreature _target) {
        AbstractCreature target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new BingePower(target, this.potency), this.potency));
        }
    }

    public int getPotency(int ascensionLevel) {
        return 5;
    }

    public AbstractPotion makeCopy() {
        return new Catnip();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}
