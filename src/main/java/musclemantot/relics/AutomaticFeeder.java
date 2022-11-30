package musclemantot.relics;

import musclemantot.characters.MuscleManTot;

import static musclemantot.MuscleManTotMod.makeID;

public class AutomaticFeeder extends BaseRelic {
    private static final String NAME = AutomaticFeeder.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public AutomaticFeeder() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    
}
