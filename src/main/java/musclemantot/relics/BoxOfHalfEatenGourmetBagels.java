package musclemantot.relics;

import musclemantot.characters.MuscleManTot;

import static musclemantot.MuscleManTotMod.makeID;

public class BoxOfHalfEatenGourmetBagels extends BaseRelic {
    private static final String NAME = BoxOfHalfEatenGourmetBagels.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public BoxOfHalfEatenGourmetBagels() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    
}
