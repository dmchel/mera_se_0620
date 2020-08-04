package main.objects.characters;

import annotations.SaveIgnore;

import main.objects.Character;
import main.objects.characters.stuff.Stuff;

import main.transactions.ChangeHPTransaction;
import main.transactions.ReactionTransaction;
import main.transactions.InfoTransaction;
import main.transactions.WeaponTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Monster extends Character {
    @SaveIgnore
    protected static Integer MIN_POWER = 10;
    @SaveIgnore
    protected static Integer MAX_POWER = 20;

    protected Monster(String name, Integer maxHitPoint) {
        this(name, maxHitPoint, ThreadLocalRandom.current().nextInt(MIN_POWER, MAX_POWER), null);
    }

    protected Monster(String name, Integer maxHitPoint, Integer power) {
        this(name, maxHitPoint, power, null);
    }

    protected Monster(String name, Integer maxHitPoint, Integer power, Stuff stuff) {
        super(name, maxHitPoint, ThreadLocalRandom.current().nextInt(MIN_POWER, MAX_POWER), null, stuff);
    }

    @Override
    public ArrayList<Transaction> react(WeaponTransaction transaction) {
        ArrayList<Transaction> reaction = new ArrayList<>();

        if (transaction instanceof ChangeHPTransaction) {
            Character attacker = transaction.getActionCreator();
            Integer hitPoints = ((ChangeHPTransaction) transaction).getHitPoints();

            if (attacker == null) {
                reaction.add(new ReactionTransaction("Attacker Null Pointer Exception.", transaction, false));
                return reaction;
            }

            setHitPoints(getHitPoints() + hitPoints);
            reaction.add(new ReactionTransaction("SUCCESS", transaction, true));
            reaction.add(new InfoTransaction(String.format("Monster \"%s\" got damage on %d hp.", getName(), hitPoints)));
            return reaction;
        }

        reaction.add(new ReactionTransaction("Bad Transaction.", transaction, false));
        return reaction;
    }
}