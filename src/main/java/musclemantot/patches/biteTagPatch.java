package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.JawVicePower;

@SpirePatch(clz= Bite.class, method= SpirePatch.CONSTRUCTOR)
public class biteTagPatch {
    public static void Postfix(Bite __instance) {
        __instance.tags.add(MuscleManTot.Enums.BITE);

        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && AbstractDungeon.player.hasPower(JawVicePower.POWER_ID)) {
            __instance.updateCost(-1);
        }
    }
}


