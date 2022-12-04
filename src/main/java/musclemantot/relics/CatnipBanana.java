package musclemantot.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import musclemantot.characters.MuscleManTot;

import static musclemantot.MuscleManTotMod.makeID;

public class CatnipBanana extends BaseRelic {
    private static final String NAME = CatnipBanana.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public CatnipBanana() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public float atDamageModify(float damage, AbstractCard c) {
        if (c.hasTag(MuscleManTot.Enums.BITE)) {
            return damage * 1.35F;
        } else {
            return damage;
        }
    }
}
