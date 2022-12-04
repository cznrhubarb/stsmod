package musclemantot.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AllTagToHandAction extends AbstractGameAction{
    private final AbstractPlayer p;
    private final AbstractCard.CardTags tagTarget;

    public AllTagToHandAction(AbstractCard.CardTags tagToTarget) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.tagTarget = tagToTarget;
    }

    public void update() {
        if (this.p.discardPile.size() > 0) {
            for (AbstractCard card : this.p.discardPile.group) {
                if (card.hasTag(this.tagTarget)) {
                    this.addToBot(new DiscardToHandAction(card));
                }
            }
        }

        this.tickDuration();
        this.isDone = true;
    }
}
