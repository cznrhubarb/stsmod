package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.powers.BingePower;

@SpirePatch(clz= AbstractDungeon.class, method= "reset")
public class resetBingePurgePatch {
    public static void Postfix() {
        BingePower.resetBingePurgeCount();
    }
}
