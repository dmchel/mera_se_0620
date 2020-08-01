package course.battlegame.gameengine.sceneobjects.positionobjects.positiontype;

import course.battlegame.gameengine.transactions.ActionTransaction;

import java.util.ArrayList;

public abstract class PositionType {
    public abstract ArrayList<ActionTransaction> getEffectedTransactions(ArrayList<ActionTransaction> transactions);
}
