package course.battlegame.gameengine.objects.positionobjects.effects;

import course.battlegame.gameengine.transactions.ActionTransaction;

public class Night implements Effect {
    @Override
    public ActionTransaction getEffectedTransactions(ActionTransaction transaction) {
        return transaction;
    }
}