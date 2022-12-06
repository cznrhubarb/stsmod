package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.beyond.Donu;

import java.util.Locale;
import java.util.Objects;

import static musclemantot.MuscleManTotMod.resourcePath;

public class baguPatches {
    @SpirePatch(clz = Donu.class, method = SpirePatch.CONSTRUCTOR)
    public static class patchConstructor {
        public static void Postfix(Donu __instance) {
            // TODO: This should use a monsterString so it can be localized, but I don't think Miles speaks any other
            //  languages, so it's probably OK.
            //monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(makeID("Bagu"));
            __instance.name = "Bagu";
        }
    }

    @SpirePatch(clz = AbstractCreature.class, method = "loadAnimation")
    public static class patchAnimation {
        public static void Prefix(AbstractCreature __instance, @ByRef String atlasUrl[], @ByRef String skeletonUrl[], float scale) {
            if (__instance != null && Objects.equals(__instance.id, "Donu")) {
                atlasUrl[0] = resourcePath("monsters/bagu/skeleton.atlas");
                skeletonUrl[0] = resourcePath("monsters/bagu/skeleton.json");
            }
        }
    }
}


