package musclemantot.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import musclemantot.BingePurgeInterface;

import java.util.HashMap;

import static musclemantot.MuscleManTotMod.makeID;

public class BingePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Binge");

    private static final HashMap<Integer, Integer> bingesPerFloor = new HashMap<>();
    private static final HashMap<Integer, Integer> purgesPerFloor = new HashMap<>();

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BingePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

        onBinge();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        onBinge();
    }

    private void onBinge() {
        bingesPerFloor.put(AbstractDungeon.floorNum, bingesPerFloor.getOrDefault(AbstractDungeon.floorNum, 0) + 1);

        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof BingePurgeInterface) {
                ((BingePurgeInterface) relic).onBinge(this.amount);
            }
        }

        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof BingePurgeInterface) {
                ((BingePurgeInterface) card).onBinge(this.amount);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card instanceof BingePurgeInterface) {
                ((BingePurgeInterface) card).onBinge(this.amount);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card instanceof BingePurgeInterface) {
                ((BingePurgeInterface) card).onBinge(this.amount);
            }
        }

        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof BingePurgeInterface) {
                ((BingePurgeInterface) power).onBinge(this.amount);
            }
        }
    }

    @Override
    public void onRemove() {
        purgesPerFloor.put(AbstractDungeon.floorNum, purgesPerFloor.getOrDefault(AbstractDungeon.floorNum, 0) + 1);

        for (AbstractRelic relic : AbstractDungeon.player.relics) {
            if (relic instanceof BingePurgeInterface) {
                ((BingePurgeInterface) relic).onPurge(this.amount);
            }
        }

        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof BingePurgeInterface) {
                ((BingePurgeInterface) card).onPurge(this.amount);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (card instanceof BingePurgeInterface) {
                ((BingePurgeInterface) card).onPurge(this.amount);
            }
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (card instanceof BingePurgeInterface) {
                ((BingePurgeInterface) card).onPurge(this.amount);
            }
        }

        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof BingePurgeInterface) {
                ((BingePurgeInterface) power).onPurge(this.amount);
            }
        }
    }

    public static void resetBingePurgeCount() {
        bingesPerFloor.clear();
        purgesPerFloor.clear();
    }

    public static int getBingesThisCombat() {
        return bingesPerFloor.getOrDefault(AbstractDungeon.floorNum, 0);
    }

    public static int getPurgesThisCombat() {
        return purgesPerFloor.getOrDefault(AbstractDungeon.floorNum, 0);
    }

    @Override
    public AbstractPower makeCopy() {
        return new BingePower(owner, amount);
    }
}
