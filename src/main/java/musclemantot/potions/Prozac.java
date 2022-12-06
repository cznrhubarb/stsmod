package musclemantot.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import musclemantot.powers.BingePower;

import static musclemantot.MuscleManTotMod.makeID;

public class Prozac extends CustomPotion {
    public static final String POTION_ID = makeID("Prozac");
    public static final PotionRarity POTION_RARITY = PotionRarity.UNCOMMON;
    public static final PotionSize POTION_SIZE = PotionSize.BOTTLE;
    public static final PotionColor COLOR = PotionColor.ELIXIR;
    public static final Color LIQUID_COLOR = Color.YELLOW;
    public static final Color HYBRID_COLOR = Color.GOLD;
    public static final Color SPOTS_COLOR = Color.YELLOW;
    private static final PotionStrings potionStrings;

    public Prozac() {
        // TODO: Custom images would be nice here :/
        super(potionStrings.NAME, POTION_ID, POTION_RARITY, POTION_SIZE, COLOR);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BarricadePower.NAME), BarricadePower.DESCRIPTIONS[0]));
        //this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), (String) GameDictionary.keywords.get(GameDictionary.STRENGTH.NAMES[0])));
    }

    public void use(AbstractCreature _target) {
        AbstractCreature target = AbstractDungeon.player;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new BarricadePower(target)));
        }
    }

    public int getPotency(int ascensionLevel) {
        return 0;
    }

    public AbstractPotion makeCopy() {
        return new Prozac();
    }

    static {
        potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    }
}
