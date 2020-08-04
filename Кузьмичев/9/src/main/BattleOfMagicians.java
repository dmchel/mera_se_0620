package main;

import main.objects.Scene;

import main.actions.weapons.Weapon;
import main.actions.weapons.material.BowShot;
import main.actions.weapons.material.KnightStrike;
import main.actions.weapons.spells.Healing;
import main.actions.weapons.spells.Chainlightning;
import main.actions.weapons.spells.ExileMonsters;
import main.actions.weapons.spells.Firetouch;
import main.actions.weapons.spells.Firewall;
import main.actions.weapons.spells.Lightning;
import main.actions.weapons.spells.Migraine;
import main.actions.weapons.spells.SpellsList;

import main.objects.characters.Archer;
import main.objects.characters.Knight;
import main.objects.characters.Monster;
import main.objects.characters.Magician;

import main.objects.players.computer.ArcherComputerPlayer;
import main.objects.players.computer.KnightComputerPlayer;
import main.objects.players.computer.MagicianComputerPlayer;
import main.objects.players.computer.MonsterComputerPlayer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BattleOfMagicians {
    private static Integer MIN_CHARACTERS = 5;
    private static Integer MAX_CHARACTERS = 10;

    private static Integer MAX_MAGICIAN_HEALTH = 50;
    private static Integer MAX_MONSTER_HEALTH = 80;
    private static Integer MAX_ARCHERS_HEALTH = 40;
    private static Integer MAX_KNIGHT_HEALTH = 60;

    private static Integer MIN_SPELLS = 1;
    private static Integer MAX_SPELLS = 2;

    private static Integer MAX_GAME_STEPS = 200;

    public static void main(String[] args) {
        System.out.println("Battle Of Magicians.");

        Scene scene1 = new Scene("Avengers");
        Scene scene2 = new Scene("Defenders");

        Integer numOfPositions = ThreadLocalRandom.current().nextInt(MIN_CHARACTERS, MAX_CHARACTERS);
        createScene(scene1, numOfPositions);

        numOfPositions = ThreadLocalRandom.current().nextInt(MIN_CHARACTERS, MAX_CHARACTERS);
        createScene(scene2, numOfPositions);

        System.out.println("Starting the game.");

        Integer currentStep = 1;
        while (currentStep <= MAX_GAME_STEPS && !(scene1.getEndGame() && scene2.getEndGame())) {
            System.out.printf("Move: %d.\n", currentStep);

            if (!scene1.getEndGame()) {
                scene1.playMove();
            }

            if (!scene2.getEndGame()) {
                scene2.playMove();
            }

            currentStep++;
        }
    }

    private static void createScene(Scene scene, Integer numOfPositions) {
        if (scene == null) {
            return;
        }

        System.out.printf("Creating the server \"%s\" ...\n", scene.getName());

        for (Integer i = 0; i < numOfPositions; i++) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                Monster monster = new MonsterComputerPlayer("monster" + i, MAX_MONSTER_HEALTH);
                scene.addCharacter(monster);
                continue;
            }

            if (ThreadLocalRandom.current().nextBoolean()) {
                ArrayList<Weapon> weapons = new ArrayList<>();
                weapons.add(new BowShot());
                Archer archer = new ArcherComputerPlayer("archer" + i, MAX_ARCHERS_HEALTH, weapons);
                scene.addCharacter(archer);
                continue;
            }

            if (ThreadLocalRandom.current().nextBoolean()) {
                ArrayList<Weapon> weapons = new ArrayList<>();
                weapons.add(new KnightStrike());
                Knight knight = new KnightComputerPlayer("knight" + i, MAX_KNIGHT_HEALTH, weapons);
                scene.addCharacter(knight);
                continue;
            }

            Integer numOfSpells = ThreadLocalRandom.current().nextInt(MIN_SPELLS, MAX_SPELLS);
            ArrayList<Weapon> spells = new ArrayList<>();

            for (Integer j = 0; j < numOfSpells; j++) {
                SpellsList randomSpell = SpellsList.values()[ThreadLocalRandom.current().nextInt(SpellsList.values().length)];

                if (randomSpell == SpellsList.CHAINLIGHTNING) {
                    spells.add(new Chainlightning());
                } else if (randomSpell == SpellsList.EXILEMONSTER) {
                    spells.add(new ExileMonsters());
                } else if (randomSpell == SpellsList.FIRETOUCH) {
                    spells.add(new Firetouch());
                } else if (randomSpell == SpellsList.FIREWALL) {
                    spells.add(new Firewall());
                } else if (randomSpell == SpellsList.HEALING) {
                    spells.add(new Healing());
                } else if (randomSpell == SpellsList.LIGHTNING) {
                    spells.add(new Lightning());
                } else if (randomSpell == SpellsList.MIGRAINE) {
                    spells.add(new Migraine());
                }
            }

            Magician magician = new MagicianComputerPlayer("magician" + i, MAX_MAGICIAN_HEALTH, spells);
            scene.addCharacter(magician);
        }

        System.out.println("Creating completed.");
    }
}