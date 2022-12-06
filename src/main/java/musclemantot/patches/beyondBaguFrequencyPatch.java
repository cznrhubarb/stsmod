package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import musclemantot.characters.MuscleManTot;

@SpirePatch(clz= TheBeyond.class, method= "initializeBoss")
public class beyondBaguFrequencyPatch {
    public static void Postfix(TheBeyond __instance) {
        if (TheBeyond.player instanceof MuscleManTot) {
            // Not sure why they put all three in and randomize instead of just giving the one they need,
            //  but I don't want to mess anything up if there is a reason. So we keep the whole list,
            //  put Bagu first, and don't randomize.
            TheBeyond.bossList.add("Donu and Deca");
            TheBeyond.bossList.add("Awakened One");
            TheBeyond.bossList.add("Time Eater");
        }
    }
}


