package musclemantot.util;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import musclemantot.powers.BingePower;
import musclemantot.relics.Prozac;

public class BingeUtil {
    public static int getPlayerBinge() {
        AbstractPower bingePower = AbstractDungeon.player.getPower(BingePower.POWER_ID);
        int bingeAmount = 0;
        if (bingePower != null) {
            bingeAmount = bingePower.amount;
        }

        if (AbstractDungeon.player.hasRelic(Prozac.ID)) {
            bingeAmount += 1;
        }

        return bingeAmount;
    }
}
