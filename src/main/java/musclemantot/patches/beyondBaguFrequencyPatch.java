package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import musclemantot.characters.MuscleManTot;

@SpirePatch(clz= TheBeyond.class, method= "initializeBoss")
public class beyondBaguFrequencyPatch {
    public static void Postfix(TheBeyond __instance) {
        if (TheBeyond.player instanceof MuscleManTot) {
            TheBeyond.bossList.clear();
            TheBeyond.bossList.add("Donu and Deca");
            TheBeyond.bossList.add("Donu and Deca");
            TheBeyond.bossList.add("Donu and Deca");
        }
    }
}


