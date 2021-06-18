package GameRPG;
import GameRPG.Characters.NPC;
import GameRPG.Characters.PlayerCharacter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        PlayerCharacter player = new PlayerCharacter(30,
                15,
                "Hero",
                0,
                1,
                0,
                30,
                0);

        System.out.println("Player character: ☲           Enemy: ☒");
        System.out.println("Healing well: ▩         Empty space: ▢");
        System.out.println("Exit: ▣");

        System.out.println("Level " + player.getLevel()
                + " | Exp: " + player.getExperience() + "/" + (player.getLevel() * 5 + 10)
                + " | Life points: " + player.getHp() + "/" + player.getMaxHp()
                + " | Attack power: " + player.getAttackPower()
                + " | Armour: " + player.getArmour());

        System.out.println("----------------------------------------");
        System.out.println();

        while (true) {
            char[][] createdMap = initiateWorldMap();
            char[][] finishedDungeon = mapGeneration(createdMap);
            while (arrayContains(finishedDungeon)) {
                System.out.println();
                System.out.println("Move: 1.Up    2.Left    3.Right    4.Down");
                int direction = reader.nextInt();
                reader.nextLine();
                try {
                    int playerLocationAfterMoving = playerMove(finishedDungeon, player, direction);
                    if (playerLocationAfterMoving == 1) {
                        int randomEnemy = (int) (Math.random() * 3 - 1 + 1);
                        if (randomEnemy == 0) {
                            NPC rat = spawnRat();
                            battlePhase(player, rat);
                            displayBattleResults(player, rat);
                        } else if (randomEnemy == 1) {
                            NPC thug = spawnThug();
                            battlePhase(player, thug);
                            displayBattleResults(player, thug);
                        } else {
                            NPC assassin = spawnAssassin();
                            battlePhase(player, assassin);
                            displayBattleResults(player, assassin);
                        }
                    } else if (playerLocationAfterMoving == 2) {
                        healingWell(player);
                    } else if (playerLocationAfterMoving == 3) {

                    }

                    for (int i = 0; i < finishedDungeon.length; i++) {
                        for (int j = 0; j < finishedDungeon[i].length; j++) {
                            System.out.print(finishedDungeon[i][j] + " ");
                        }
                        System.out.println();
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("You hit your head on a wall.");
                }
            }
            System.out.println("You find the stairs and descend.");
            System.out.println("The steps seem endless, but you eventually reach the lower level.");
            System.out.println();
        }
    }


    public static void battlePhase(PlayerCharacter player, NPC enemy) {
        Scanner reader = new Scanner(System.in);
        while (player.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("1. Aggressive stance(+8 att/-4 res)  -  " +
                    "2. Defensive stance(-10 att/+3 res).  -  " +
                    "3.Balanced stance(-2 att).");
            int playerChoice = reader.nextInt();
            if (player.getHp() > 0) {
                int playerAttack = (int) (Math.random() * (player.getAttackPower() + 1) - 1 + 1);
                if (playerChoice == 3) {
                    if (playerAttack - 2 >= 0) {
                        enemy.setHp(enemy.getHp() - (playerAttack - 2));
                        System.out.println("You deal " + (playerAttack - 2) + " damage.");
                    } else {
                        System.out.println("You deal 0 damage.");
                    }

                } else if (playerChoice == 1) {
                    System.out.println("You deal " + (playerAttack + 8) + " damage.");
                    enemy.setHp(enemy.getHp() - (playerAttack + 8));
                } else if (playerChoice == 2) {
                    if (playerAttack - 10 >= 0) {
                        enemy.setHp(enemy.getHp() - (playerAttack - 10));
                        System.out.println("You deal " + (playerAttack - 10) + " damage.");
                    } else {
                        System.out.println("You deal 0 damage.");
                    }

                }
                System.out.println("Enemy life points: " + enemy.getHp());
                System.out.println("----------------------------------------");
            }
            if (enemy.getHp() > 0) {
                int enemyAttack = (int) (Math.random() * (enemy.getAttackPower() + 1) - 1 + 1);
                if (playerChoice == 3) {
                    player.setHp(player.getHp() - (enemyAttack - player.getArmour()));
                    if (enemyAttack >= player.getArmour()) {
                        System.out.println("The enemy attacks you and deals " + enemyAttack + " damage. " +
                                "Your armour blocks " + player.getArmour() + " damage.");
                    } else {
                        System.out.println("The enemy attacks you and deals " + enemyAttack + " damage. " +
                                "Your armour blocks all of it.");
                    }
                } else if (playerChoice == 1) {
                    player.setHp(player.getHp() - (enemyAttack + 4 - player.getArmour()));
                    if (enemyAttack+4 >= player.getArmour()) {
                        System.out.println("The enemy attacks you and deals " + (enemyAttack + 4) + " damage. " +
                                "Your armour blocks " + player.getArmour() + " of it.");
                    } else {
                        System.out.println("The enemy attacks you and deals " + (enemyAttack + 4) + " damage. " +
                                "Your armour blocks all of it.");
                    }
                } else if (playerChoice == 2) {
                    if (enemyAttack - (player.getArmour() + 2) > 0) {
                        player.setHp(player.getHp() - (enemyAttack - (player.getArmour() + 3)));
                    }
                    if (enemyAttack - 3 >= player.getArmour()) {
                        System.out.println("The enemy attacks you and deals " + enemyAttack + " damage. " +
                                "Your armour blocks " + player.getArmour() + " of it.");
                    } else {
                        System.out.println("The enemy attacks you and deals " + enemyAttack + " damage. " +
                                "Your armour blocks all of it.");
                    }
                }
                System.out.println("Your life points: " + player.getHp());
                System.out.println("----------------------------------------");
            }
        }
        if (enemy.getHp() <= 0) {
            System.out.println("You defeated the " + enemy.getName() + ".");
            switch (enemy.getName()) {
                case "rat" -> {
                    player.setExperience(player.getExperience() + 4);
                    System.out.println("You received 4 experience points.");
                }
                case "Thug" -> {
                    player.setExperience(player.getExperience() + 6);
                    System.out.println("You received 6 experience points.");
                }
                case "Assassin" -> {
                    player.setExperience(player.getExperience() + 8);
                    System.out.println("You received 8 experience points.");
                }
            }
            System.out.println();
            System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        }
    }

    public static void displayBattleResults(PlayerCharacter player, NPC enemy) {
        if (player.getHp() > 0) {
            Scanner reader = new Scanner(System.in);
            System.out.println("After the battle you assess your situation - Life points: " + player.getHp());
            System.out.println("You now have " + player.getExperience() + " experience points.");
            while (player.getExperience() >= (player.getLevel() * 5 + 10)) {
                player.setSkillPoints(player.getSkillPoints() + 1);
                player.setLevel(player.getLevel() + 1);
                System.out.println("You've leveled up. You are now level " + player.getLevel() + ". Your stats have increased by 1 and you received 1 skill point..");
                player.setExperience(player.getExperience() - ((player.getLevel() - 1) * 5 + 10));
                player.setMaxHp(player.getMaxHp() + 1);
                player.setHp(player.getHp() + 1);
                if (player.getHp() > player.getMaxHp()) {
                    player.setHp(player.getMaxHp());
                }
                player.setAttackPower(player.getAttackPower() + 1);
            }
            System.out.println("What should your next move be?");
            System.out.println("     1. Rest and heal.");
            System.out.println("     2. Sharpen your blade.");
            if (player.getSkillPoints() > 0) {
                System.out.println("     3. Spend skill points");
            }
            int afterBattleChoice = reader.nextInt();
            reader.nextLine();
            int randomHeal = (int) (Math.random() * 6 - 1 + 1);
            if (afterBattleChoice == 1) {
                int healAmount = 3 + randomHeal;
                System.out.println("You decide to rest and heal " + healAmount + " health.");
                player.setHp(player.getHp() + healAmount);
                if (player.getHp() > player.getMaxHp()) {
                    player.setHp(player.getMaxHp());
                }
            } else if (afterBattleChoice == 2) {
                player.setAttackPower(player.getAttackPower() + 2);
                System.out.println("You decide to sharpen your blade and gain 2 attack power. " +
                        "You now have " + player.getAttackPower() + " attack power.");
            } else if (afterBattleChoice == 3) {
                for (int i = player.getSkillPoints(); i > 0; i--) {
                    System.out.println("Choose what stat to improve with your skill point.");
                    System.out.println("1. +4 att.");
                    System.out.println("2. +1 armour.");
                    System.out.println("3. +6 max hp.");
                    int statChoice = reader.nextInt();
                    if (statChoice == 1) {
                        player.setAttackPower(player.getAttackPower() + 4);
                    } else if (statChoice == 2) {
                        player.setArmour(player.getArmour() + 1);
                    } else if (statChoice == 3) {
                        player.setMaxHp(player.getMaxHp() + 6);
                    }
                }
                player.setSkillPoints(0);
            }
            System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
            System.out.println();
        }
        if (player.getHp() <= 0) {
            System.out.println("You were defeated by: " + enemy.getName() + ".");
        }
    }

    public static NPC spawnRat() {
        NPC rat = new NPC(15, 4, "rat", 0, 1, 0, 15, 0, 0);
        System.out.println("You ran into a rat!");
        System.out.println("Enemy life points: " + rat.getHp() + " | Attack power: " + rat.getAttackPower());
        System.out.println("----------------------------------------");
        return rat;
    }

    public static NPC spawnThug() {
        NPC thug = new NPC(25, 6, "Thug", 0, 3, 1, 25, 0, 1);
        System.out.println("You ran into a lowly thug!");
        System.out.println("Enemy life points: " + thug.getHp() + " | Attack power: " + thug.getAttackPower());
        System.out.println("----------------------------------------");
        return thug;
    }

    public static NPC spawnAssassin() {
        NPC assassin = new NPC(30, 8, "Assassin", 0, 5, 2, 30, 0, 2);
        System.out.println("You ran into a trained assassin!");
        System.out.println("Enemy life points: " + assassin.getHp() + " | Attack power: " + assassin.getAttackPower());
        System.out.println("----------------------------------------");
        return assassin;
    }

    public static char[][] initiateWorldMap () {
        int max = 6;
        int min = 3;
        int range = max - min + 1;
        int x = (int) (Math.random() * range) + min;
        int y = (int) (Math.random() * range) + min;
        return new char[x][y];
    }

    public static char[][] mapGeneration (char[][] worldMap) {
        int exitX = 0;
        int exitY = (int) (Math.random() * ((worldMap[exitX].length - 1) + 1));
        int playerX = worldMap.length - 1;
        int playerY = (int) (Math.random() * ((worldMap[playerX].length - 1) + 1));
        for (int i = 0; i< worldMap.length; i++) {
            for (int j = 0; j < worldMap[i].length; j++) {
                worldMap[i][j] = '▢';
                worldMap[exitX][exitY] = '▣';
                worldMap[playerX][playerY] = '☲';
            }
        }

        for (int i = 0; i< worldMap.length; i++) {
            for (int j = 0; j < worldMap[i].length; j++) {
                int enemyX = (int) (Math.random() * ((worldMap.length - 1) + 1));
                int enemyY = (int) (Math.random() * ((worldMap[i].length - 1) + 1));
                if (worldMap[enemyX][enemyY] == '▢') {
                    worldMap[enemyX][enemyY] = '☒';
                }
                int healX = (int) (Math.random() * ((worldMap.length - 1) + 1));
                int healY = (int) (Math.random() * ((worldMap[i].length - 1) + 1));
                if (worldMap[healX][healY] == '▢') {
                    worldMap[healX][healY] = '▩';
                }
                System.out.print(worldMap[i][j] + " ");
            }
            System.out.println();
        }
        return worldMap;
    }

    public static boolean arrayContains (char[][] worldMap) {
        for (int i = 0; i < worldMap.length; i++) {
          for (int j = 0; j < worldMap[i].length; j++) {
                if (worldMap[i][j] == '▣') return true;
        }
    }
    return false;
}

    public static int playerMove (char[][] worldMap, PlayerCharacter player, int direction) {
            for (int i = 0; i < worldMap.length; i++) {
                for (int j = 0; j < worldMap[i].length; j++) {
                    if (worldMap[i][j] == '☲') {
                        if (direction == 1) {
                            if (worldMap[i - 1][j] == '☒') {
                                worldMap[i-1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 1;
                            } else if (worldMap[i - 1][j] == '▩') {
                                worldMap[i-1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 2;
                            } else if (worldMap[i - 1][j] == '▢') {
                                worldMap[i-1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 3;
                            } else if (worldMap[i - 1][j] == '▣') {
                                worldMap[i-1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 4;
                            }
                        } else if (direction == 2) {
                            if (worldMap[i][j - 1] == '☒') {
                                worldMap[i][j - 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 1;
                            } else if (worldMap[i][j - 1] == '▩') {
                                worldMap[i][j - 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 2;
                            } else if (worldMap[i][j - 1] == '▢') {
                                worldMap[i][j - 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 3;
                            } else if (worldMap[i][j - 1] == '▣') {
                                worldMap[i][j - 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 4;
                            }
                        } else if (direction == 3) {
                            if (worldMap[i][j + 1] == '☒') {
                                worldMap[i][j + 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 1;
                            } else if (worldMap[i][j + 1] == '▩') {
                                worldMap[i][j + 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 2;
                            } else if (worldMap[i][j + 1] == '▢') {
                                worldMap[i][j + 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 3;
                            } else if (worldMap[i][j + 1] == '▣') {
                                worldMap[i][j + 1] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 4;
                            }
                        } else if (direction == 4) {
                            if (worldMap[i + 1][j] == '☒') {
                                worldMap[i+1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 1;
                            } else if (worldMap[i + 1][j] == '▩') {
                                worldMap[i+1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 2;
                            } else if (worldMap[i + 1][j] == '▢') {
                                worldMap[i+1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 3;
                            } else if (worldMap[i + 1][j] == '▣') {
                                worldMap[i+1][j] = worldMap[i][j];
                                worldMap[i][j] = '▢';
                                return 4;
                            }
                        }
                    }
                }
            }
        return 0;
    }

    public static void healingWell (PlayerCharacter player) {
        Scanner reader = new Scanner(System.in);
        System.out.println("You reach a serene grove that houses a calming spring of fresh water.");
        System.out.println("Do you wish to drink?");
        System.out.println("1. Yes     2.No");
        int choice = reader.nextInt();
        reader.nextLine();
        if (choice == 1) {
            player.setHp(player.getHp() + 6);
            if (player.getHp() > player.getMaxHp()) {
                player.setHp(player.getMaxHp());
            }
            System.out.println("You take a sip of the water and feel refreshed. Your hp is now: " + player.getHp());
        } else if (choice == 2) {
            System.out.println("You decide to not drink and leave the area.");
        }
    }

//    public static void checkMovementDirection (char[][] worldMap, int i, int j) {
//                if (worldMap[i-1][j] != '▢' && worldMap[i-1][j] != '☒' && worldMap[i-1][j] != '▩' && worldMap[i-1][j] != '▣') { //if up is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 2.Left    3.Right    4.Down");
//                } else if(worldMap[i][j-1] != '▢' && worldMap[i][j-1] != '☒' && worldMap[i][j-1] != '▩' && worldMap[i][j-1] != '▣') { //if left is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 1.Up    3.Right    4.Down");
//                } else if (worldMap[i+1][j] != '▢' && worldMap[i+1][j] != '☒' && worldMap[i+1][j] != '▩' && worldMap[i+1][j] != '▣') { //if down is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 1.Up    2.Left    3.Right");
//                } else if (worldMap[i][j+1] != '▢' && worldMap[i][j+1] != '☒' && worldMap[i][j+1] != '▩' && worldMap[i][j+1] != '▣') { //if right is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 1.Up    2.Left    4.Down");
//                } else if (worldMap[i-1][j] != '▢' && worldMap[i-1][j] != '☒' && worldMap[i-1][j] != '▩' && worldMap[i-1][j] != '▣' &&
//                        worldMap[i][j-1] != '▢' && worldMap[i][j-1] != '☒' && worldMap[i][j-1] != '▩' && worldMap[i][j-1] != '▣') { //if up and left is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 3. Right    4.Down");
//                } else if (worldMap[i-1][j] != '▢' && worldMap[i-1][j] != '☒' && worldMap[i-1][j] != '▩' && worldMap[i-1][j] != '▣' &&
//                        worldMap[i][j+1] != '▢' && worldMap[i][j+1] != '☒' && worldMap[i][j+1] != '▩' && worldMap[i][j+1] != '▣') { //if up and right is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 2.Left    4.Down");
//                } else if (worldMap[i+1][j] != '▢' && worldMap[i+1][j] != '☒' && worldMap[i+1][j] != '▩' && worldMap[i+1][j] != '▣' &&
//                        worldMap[i][j-1] != '▢' && worldMap[i][j-1] != '☒' && worldMap[i][j-1] != '▩' && worldMap[i][j-1] != '▣') { //if down and left is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 1.Up    3. Right");
//                } else if (worldMap[i+1][j] != '▢' && worldMap[i+1][j] != '☒' && worldMap[i+1][j] != '▩' && worldMap[i+1][j] != '▣' &&
//                        worldMap[i][j+1] != '▢' && worldMap[i][j+1] != '☒' && worldMap[i][j+1] != '▩' && worldMap[i][j+1] != '▣') { //if down and right is out of bounds
//                    System.out.println();
//                    System.out.println("Move: 1.Up    2.Left");
//                }
//            }
}
