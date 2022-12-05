package musclemantot.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import musclemantot.characters.MuscleManTot;
import musclemantot.powers.JawVicePower;

@SpirePatch(clz= ShowCardAndAddToHandEffect.class, method= SpirePatch.CONSTRUCTOR,
        paramtypez={
                AbstractCard.class, float.class, float.class
        })
public class jawViceAddToHandOffsetConPatch {
    public static void Postfix(ShowCardAndAddToHandEffect __instance, AbstractCard card, float offX, float offY) {
        if (AbstractDungeon.player.hasPower(JawVicePower.POWER_ID) && card.hasTag(MuscleManTot.Enums.BITE)) {
            card.setCostForTurn(card.cost - 1);
        }
    }
}
