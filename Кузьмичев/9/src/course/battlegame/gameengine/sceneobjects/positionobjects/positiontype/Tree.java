package course.battlegame.gameengine.sceneobjects.positionobjects.positiontype;

import course.battlegame.gameengine.transactions.ActionTransaction;

import java.util.ArrayList;

public class Tree extends PositionType {
    @Override
    public ArrayList<ActionTransaction> getEffectedTransactions(ArrayList<ActionTransaction> transactions) {
        return transactions;
    }
}
