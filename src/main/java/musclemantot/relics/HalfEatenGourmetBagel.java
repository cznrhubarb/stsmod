package musclemantot.relics;

import musclemantot.characters.MuscleManTot;

import static musclemantot.MuscleManTotMod.makeID;

public class HalfEatenGourmetBagel extends BaseRelic {
    private static final String NAME = HalfEatenGourmetBagel.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public HalfEatenGourmetBagel() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }


}
