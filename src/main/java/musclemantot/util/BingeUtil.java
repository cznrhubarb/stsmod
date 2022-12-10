package musclemantot.util;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import musclemantot.powers.BingePower;
import musclemantot.relics.UnattendedLeftovers;

public class BingeUtil {
    public static int getPlayerBinge(boolean isForPurge) {
        AbstractPower bingePower = AbstractDungeon.player.getPower(BingePower.POWER_ID);
        int bingeAmount = 0;
        if (bingePower != null) {
            bingeAmount = bingePower.amount;
        }

//        AbstractRelic leftoversRelic = AbstractDungeon.player.getRelic(UnattendedLeftovers.ID);
//        if (isForPurge && leftoversRelic != null) {
//            leftoversRelic.flash();
//            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, leftoversRelic));
//            bingeAmount += 1;
//        }

        return bingeAmount;
    }
}
