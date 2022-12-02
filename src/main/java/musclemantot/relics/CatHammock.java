package musclemantot.relics;

import musclemantot.characters.MuscleManTot;

import static musclemantot.MuscleManTotMod.makeID;

public class CatHammock extends BaseRelic {
    private static final String NAME = CatHammock.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public CatHammock() {
        super(ID, NAME, MuscleManTot.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
