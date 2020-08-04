/*****************************************************************************
 * File: MonsterStrike.java
 * Purpose: For creation scene transactions depending on weapon implementation
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 *****************************************************************************/

package main.actions.weapons.material;

import main.actions.ActionDescriber;

import main.actions.weapons.Weapon;
import main.actions.weapons.properties.CloseProperty;
import main.actions.weapons.properties.ColdProperty;

import main.actions.weapons.properties.MaterialProperty;
import main.objects.Position;
import main.objects.characters.AbstractCharacter;

import main.transactions.ChangeHPTransaction;
import main.transactions.InfoTransaction;
import main.transactions.Transaction;

import java.util.ArrayList;
import java.util.Map;

/**
 * Weapon implementation
 *
 * Name: MonsterStrike
 * Target: All characters excluding attacker
 * Damage : Depends on character
 * Properties: Material, Close, Cold
 */

public class MonsterStrike extends Weapon implements MaterialProperty, CloseProperty, ColdProperty {
    public ActionDescriber getDescriber() {
        return MaterialWeaponList.MONSTERSTRIKE;
    }

    @Override
    public ArrayList<Transaction> attack(Map<Position, AbstractCharacter> battlefield, AbstractCharacter attacker) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (Position position : battlefield.keySet()) {
            AbstractCharacter target = battlefield.get(position);

            if (target == attacker) {
                continue;
            }

            transactions.add(new ChangeHPTransaction(attacker, target, this.getClass(), -attacker.getPower()));
            String message = String.format("Monster \"%s\" is attacking \"%s\" on %d hp.",
                    attacker.getName(), target.getName(), attacker.getPower());
            transactions.add(new InfoTransaction(message));
        }

        return transactions;
    }
}