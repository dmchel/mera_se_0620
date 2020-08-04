package main.actions.weapons;

import main.actions.Action;
import main.actions.ActionDescriber;

import main.objects.Position;
import main.objects.Character;

import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.Map;

public abstract class Weapon extends Action {
    private Boolean blocked = false;

    public Boolean getBlocked() {
        return blocked;
    }

    protected void setBlocked(Boolean state) {
        blocked = state;
    }

    public abstract ActionDescriber getDescriber();

    public abstract ArrayList<Transaction> attack(Map<Position, Character>  battlefield, Character attacker);
}