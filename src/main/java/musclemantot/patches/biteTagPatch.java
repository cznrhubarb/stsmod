package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import musclemantot.characters.MuscleManTot;

@SpirePatch(clz= Bite.class, method= SpirePatch.CONSTRUCTOR)
public class biteTagPatch {
    public static void Postfix(Bite __instance) {
        __instance.tags.add(MuscleManTot.Enums.BITE);
    }
}


