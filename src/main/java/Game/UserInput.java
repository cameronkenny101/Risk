package Game;

import javafx.application.Platform;

import java.io.IOException;
import java.util.*;


public class UserInput {

    Game game;
    Player player1, player2;
    int troops;
    int countryIndex;
    int adjacentIndex;
    int neutralTurnCountdown = Constants.NUM_PLAYERS;
    UserInputLogic userInputLogic = new UserInputLogic();
    Battle battle = new Battle();

    /**
     * Constructor for UserInput class
     *
     * @param game    controller for the game class
     * @param player1 instance of player1
     * @param player2 instance of player2
     */
    public UserInput(Game game, Player player1, Player player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
    }

    public UserInput(Game game) {
        this.game = game;
        game.receiveUserInput(this);
    }

    /**
     * This is used to align a question with a given answer
     *
     * @param question the question that has been displayed to the player on the terminal ie "How many troops do you want to place"
     * @param input    the input of the user ie in response to how many troops question "3" troops
     */
    public void receiveInput(String question, String input) {
        Player player = game.player1.isTurn() ? game.player1 : game.player2;
        Player nextPlayer = game.player1.isTurn() ? game.player2 : game.player1;
        game.uiController.clearQuestion();

        switch (question) {
            case "Press enter to choose your 9 cards from the deck":
            case "Press enter to let neutrals choose their cards":
                if (game.isOnline) {
                    player.onlineGameHandler.sendNextMove(player.getCsc());
                }
                game.start();
                break;
            case "Press enter to roll the dice":
                game.setFirstTurn();
                break;
            case "How many troops do you want to place":
                placeTroops(input, player);
                break;
            case "What country do you want to reinforce":
                askForCountry(input, player);
                break;
            case "Are you sure you want to reinforce this country? (yes/no)":
                reinforceCountry(input, player, nextPlayer);
                break;
            case "Do you want to fortify your territories":
                fortifyCountry(input, player, nextPlayer);
                break;
            case "What country do you want to fortify":
                askToFortify(input, player);
                break;
            case "What country will fortify your country":
                countryToGive(input, player);
                break;
            case "Would you like to invade a country? (yes/no)":
                askToBattle(input, player);
                break;
            case "What country do you wish to attack from?":
                battleFrom(input, player);
                break;
            case "What country do you wish to attack?":
                attackCountry(input, player);
                break;
            case "How many units do you wish to attack for you?":
                setAttackUnits(input, player, nextPlayer);
                break;
            case "How many units do you wish to defend for you?":
                setDefenceUnits(input, player, nextPlayer);
                break;
            case "Do you want to move any additional troops to your new territory?":
                askToMoveAdditionalTroops(input, player, nextPlayer);
                break;
            case "How many troops do you want to add? (There must still be 1 troop left in your original territory)":
                moveTroops(input, player, nextPlayer);
                break;
            case "Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.":
                attackerChoice(input, player, nextPlayer);
                break;
            case "How many troops do you want to move":
                troopsToFortify(input, player, nextPlayer);
                break;
            case "Do you want to exchange any of your cards? (yes/no)":
                askToPlayCards(player, nextPlayer, input);
                break;
            case "Enter the number for the set you want to exchange" :
                exchangeCards(player, nextPlayer, input);
            case "":
                break;
        }
    }

    private void askToPlayCards(Player player, Player nextPlayer, String answer) {
        if (answer.equals("yes")) {
            game.uiController.output.appendText(Sets.setsToPlay(player.getInsignias()));
            if(Sets.validSet == 1)
                exchangeCards(player, nextPlayer, "1");
            else
                game.uiController.askQuestion("Enter the number for the set you want to exchange");
        } else {
            switchTurn(player, nextPlayer);
        }
    }

    private void exchangeCards(Player player, Player nextPlayer, String input) {
        int num = catchIntException(input);
        if(num == -1) {
            game.uiController.askQuestion("Enter the number for the set you want to exchange");
            return;
        }

        if(num < 1 || num > Sets.validSet) {
            game.uiController.output.appendText("> Invalid set number. Try again\n");
            game.uiController.askQuestion("Enter the number for the set you want to exchange");
        } else {
            player.exchangeCards(num);
            player.updateTroops(Sets.getSetsValue());
            game.uiController.output.appendText("> You have exchanged your cards and got " + Sets.getSetsValue() + " troops \n");
            Sets.updateSetsValue();
            game.uiController.output.appendText(player.printCardHand());
            switchTurn(player, nextPlayer);
        }
    }

    private int catchIntException(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (Exception e) {
            game.uiController.output.appendText("> Invalid entry. Try again\n");
            return -1;
        }
        return num;
    }

    public void attackerChoice(String input, Player player, Player nextPlayer) {
        int num = catchIntException(input);
        if(num == -1) {
            game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
            return;
        }

        switch (num) {
            case 1:
                if(game.logic.getTroop_count()[battle.attackCountryId] == 1) {
                    game.uiController.output.appendText("> You only have 1 troop left. Try again\n");
                    game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
                } else
                    continueInvasion(player);
                break;
            case 2:
                if(checkInvasionIsAvailable(player))
                    askToBattle("yes", player);
                else {
                    game.uiController.output.appendText("> You have no territories ready to attack. Try again\n");
                    game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
                }
                break;
            default:
                if (game.isOnline)
                    player.getCsc().writeBoolean(false);
                game.uiController.askQuestion("Do you want to fortify your territories");
                break;
        }
    }

    private void continueInvasion(Player player) {
        if (battle.invasionVictory)
            game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
        else {
            game.uiController.askQuestion("How many units do you wish to attack for you?");
            if (game.isOnline)
                player.getCsc().writeBoolean(true);
        }
    }

    public void moveTroops(String troops, Player attacker, Player defender) {
        int num = catchIntException(troops);
        if(num == -1) {
            game.uiController.askQuestion("How many troops do you want to add? (There must still be 1 troop left in your original territory)");
            return;
        }

        if (game.logic.getTroop_count()[battle.attackCountryId] - num >= 1) {
            game.logic.getTroop_count()[battle.attackCountryId] -= num;
            game.logic.getTroop_count()[battle.defenceCountryId] += num;

            game.uiController.setRegion(battle.defenceCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.defenceCountryId]);
            game.uiController.setRegion(battle.attackCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.attackCountryId]);

            if (game.isOnline) {
                attacker.onlineGameHandler.sendInt(game.logic.getTroop_count()[battle.defenceCountryId], attacker.getCsc());
                attacker.onlineGameHandler.sendInt(game.logic.getTroop_count()[battle.attackCountryId], attacker.getCsc());
            }
            if(checkInvasionIsAvailable(attacker))
                game.uiController.askQuestion("Would you like to invade a country? (yes/no)");
            else
                battle("no", attacker, defender);
        } else {
            game.uiController.output.appendText("Invalid number of troops\n");
            game.uiController.askQuestion("Do you want to move any additional troops to your new territory?");
        }
    }

    /**
     * Asks to move additional troops after a victory
     *
     * @param input
     * @param attacker
     * @param defender
     */
    public void askToMoveAdditionalTroops(String input, Player attacker, Player defender) {
        if (input.equals("yes")) {
            game.uiController.askQuestion("How many troops do you want to add? (There must still be 1 troop left in your original territory)");
            if (game.isOnline)
                attacker.getCsc().writeBoolean(true);
        } else {
            if (game.isOnline)
                attacker.getCsc().writeBoolean(false);
            battle("no", attacker, defender);
        }
    }

    public void battle(String input, Player attacker, Player defender) {
        if (input.equals("yes") && !battle.invasionVictory && !battle.invasionLoss) {
            // Roll dice
            ArrayList<Integer> attackerDice = Dice.rollSetOfDice(battle.numAttackUnits);
            ArrayList<Integer> defenderDice = Dice.rollSetOfDice(battle.numDefenceUnits);
            game.uiController.output.appendText("Attacker has rolled the following dice: " + attackerDice + "\n");
            game.uiController.output.appendText("Defender has rolled the following dice: " + defenderDice + "\n");

            //Execute battle sequence
            battle.calculateBattleSequence(attackerDice, defenderDice);

            //set the regions in the UI
            if (game.isOnline) {
                Platform.runLater(() -> {
                    game.uiController.setRegion(battle.defenceCountryId, game.logic.country_owner[battle.defenceCountryId], game.logic.getTroop_count()[battle.defenceCountryId]);
                    game.uiController.setRegion(battle.attackCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.attackCountryId]);
                });
            } else {
                game.uiController.setRegion(battle.defenceCountryId, game.logic.country_owner[battle.defenceCountryId], game.logic.getTroop_count()[battle.defenceCountryId]);
                game.uiController.setRegion(battle.attackCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.attackCountryId]);
            }

            if (!battle.invasionVictory && game.isOnline) {
                attacker.onlineGameHandler.sendInt(game.logic.getTroop_count()[battle.defenceCountryId], attacker.getCsc());
                attacker.onlineGameHandler.sendInt(game.logic.getTroop_count()[battle.attackCountryId], attacker.getCsc());
                attacker.getCsc().writeBoolean(true);
                attacker.getCsc().writeBoolean(false);
            }

            if (battle.invasionLoss) {
                game.uiController.output.appendText("You have lost the battle.\n");
                if(checkInvasionIsAvailable(attacker))
                    game.uiController.askQuestion("Would you like to invade a country? (yes/no)");
                else
                    battle("no", attacker, defender);
            }
            //if we win the battle
            else if (battle.invasionVictory) {
                game.uiController.output.appendText("You have won the battle and claimed a new territory\n");
                attacker.setConquerTerritory(true);

                //Set Troop Counts:
                game.logic.getTroop_count()[battle.defenceCountryId] = battle.numAttackUnits;
                game.logic.getTroop_count()[battle.attackCountryId] -= battle.numAttackUnits;

                //Set Owner
                game.logic.getCountry_owner()[battle.defenceCountryId] = attacker.getColour();

                if (game.isOnline) {
                    System.out.println("def: " + game.logic.getTroop_count()[battle.defenceCountryId]);
                    System.out.println("att: " + game.logic.getTroop_count()[battle.attackCountryId]);
                    attacker.onlineGameHandler.sendInt(game.logic.getTroop_count()[battle.defenceCountryId], attacker.getCsc());
                    attacker.onlineGameHandler.sendInt(game.logic.getTroop_count()[battle.attackCountryId], attacker.getCsc());
                    attacker.getCsc().writeBoolean(false);
                    attacker.getCsc().writeBoolean(game.logic.getTroop_count()[battle.attackCountryId] > 1);
                    Platform.runLater(() -> {
                        // Potential bug
                        game.uiController.setRegion(battle.defenceCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.defenceCountryId]);
                        game.uiController.setRegion(battle.attackCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.attackCountryId]);
                    });
                } else {
                    //set the regions in the UI
                    game.uiController.setRegion(battle.defenceCountryId, attacker.getColour(), battle.numAttackUnits);
                    game.uiController.setRegion(battle.attackCountryId, attacker.getColour(), game.logic.getTroop_count()[battle.attackCountryId]);
                }

                if (game.isLoser(defender, game.logic.country_owner)) {
                    game.uiController.output.appendText("> " + defender.getName() + " has been annihilated\n");
                    game.uiController.output.appendText("> " + attacker.getName() + " has won!");
                    return;
                }

                if (game.logic.getTroop_count()[battle.attackCountryId] > 1) {
                    game.uiController.askQuestion("Do you want to move any additional troops to your new territory?");
                } else {
                    game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
                }
            } else {
                game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
            }
        } else {
            game.uiController.askQuestion("Would you like to:\n1, continue the invasion.\n2, invade a new territory.\n3, end combat.");
        }
    }

    public void setDefenceUnits(String troops, Player attacker, Player defender) {
        battle.numDefenceUnits = catchIntException(troops);
        if(battle.numDefenceUnits == -1) {
            game.uiController.askQuestion("How many units do you wish to defend for you?");
            return;
        }
        boolean valid = battle.assertValidDefenders();
        if (!valid) {
            game.uiController.output.appendText("You chose an invalid number of troops. You may only choose between 1 and 2. \n");
            game.uiController.askQuestion("How many units do you wish to defend for you?");
        } else if (game.isOnline) {
            defender.onlineGameHandler.sendInt(battle.numDefenceUnits, defender.getCsc());
            Thread t = new Thread(() -> {
                try {
                    updateAfterAttack(attacker, defender);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        } else {
            battle("yes", attacker, defender);
        }
    }

    public void updateAfterAttack(Player attacker, Player defender) throws IOException {
        int defenseTroops = defender.getCsc().receiveInt();
        int attackTroops = defender.getCsc().receiveInt();
        boolean sameColor = defender.getCsc().receiveBoolean();
        boolean canContinue = defender.getCsc().receiveBoolean();
        game.logic.getTroop_count()[battle.attackCountryId] = attackTroops;
        game.logic.getTroop_count()[battle.defenceCountryId] = defenseTroops;
        Platform.runLater(() -> game.uiController.setMap(game.logic.troop_count, game.logic.getCountry_owner()));
        if (!sameColor) {
            game.logic.getCountry_owner()[battle.defenceCountryId] = attacker.getColour();
            game.uiController.output.appendText("> " + Constants.COUNTRY_NAMES.get(battle.defenceCountryId) + " has been invaded by " + Constants.COUNTRY_NAMES.get(battle.attackCountryId) + "\n");
            if (canContinue) {
                Thread t = new Thread(() -> isFortifying(attacker, defender));
                t.start();
            } else {
                game.threadForQuestion(defender, attacker);
            }
        } else {
            game.uiController.output.appendText("> " + Constants.COUNTRY_NAMES.get(battle.attackCountryId) + " could not successfully invade " + Constants.COUNTRY_NAMES.get(battle.defenceCountryId) + "\n");
            game.threadForQuestion(defender, attacker);
        }
    }

    private void isFortifying(Player attacker, Player defender) {
        boolean fortify = defender.getCsc().receiveBoolean();
        if (fortify) {
            Thread t = new Thread(() -> {
                try {
                    threadForUpdating(attacker, defender);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        } else
            game.threadForQuestion(defender, attacker);
    }

    private void threadForUpdating(Player attacker, Player defender) throws IOException {
        int defenseTroops = defender.getCsc().receiveInt();
        int attackTroops = defender.getCsc().receiveInt();
        game.logic.getTroop_count()[battle.attackCountryId] = attackTroops;
        game.logic.getTroop_count()[battle.defenceCountryId] = defenseTroops;
        game.uiController.output.appendText("> " + attacker.getName() + " fortified " + Constants.COUNTRY_NAMES.get(battle.defenceCountryId));
        game.uiController.setMap(game.logic.troop_count, game.logic.getCountry_owner());
        game.threadForQuestion(defender, attacker);
    }

    public void setAttackUnits(String troops, Player attacker, Player defender) {
        battle.numAttackUnits = catchIntException(troops);
        if(battle.numAttackUnits == -1) {
            game.uiController.askQuestion("How many units do you wish to attack for you?");
            return;
        }

        boolean valid = battle.assertValidAttackers();
        if (!valid) {
            game.uiController.output.appendText("You chose an invalid number of troops. You have " + game.logic.getTroop_count()[battle.attackCountryId] + " troops available and you may only choose between 1 and 3. \n");
            game.uiController.askQuestion("How many units do you wish to attack for you?");
        }
        //Skips asking for defending player response if the troops available are 1 or if it's a neutral territory
        else if (game.logic.troop_count[battle.defenceCountryId] == 1 || game.logic.country_owner[battle.defenceCountryId] != defender.getColour()) {
            if (game.isOnline)
                sendAttackData(attacker, false);
            if (game.logic.troop_count[battle.defenceCountryId] == 1)
                battle.numDefenceUnits = 1;
            else
                battle.numDefenceUnits = Math.min(game.logic.troop_count[battle.defenceCountryId], 2);
            battle("yes", attacker, defender);
        } else {
            if (game.isOnline) {
                game.uiController.output.appendText("> Please wait for " + defender.getName() + " to defend there territory\n");
                sendAttackData(attacker, true);
                Thread t = new Thread(() -> {
                    try {
                        waitForDefender(attacker, defender);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                t.start();
            } else {
                game.uiController.output.appendText("The defending player, " + defender.getName() + ", must now choose how many units to defend with.\n");
                game.uiController.askQuestion("How many units do you wish to defend for you?");
            }
        }
    }

    private void sendAttackData(Player attacker, boolean getData) {
        attacker.getCsc().writeBoolean(getData);
        game.onlineGameHandler.sendInt(battle.numAttackUnits, attacker.getCsc());
        game.onlineGameHandler.sendInt(battle.attackCountryId, attacker.getCsc());
        game.onlineGameHandler.sendInt(battle.defenceCountryId, attacker.getCsc());
    }

    private void waitForDefender(Player attacker, Player defender) throws IOException {
        battle.numDefenceUnits = attacker.getCsc().receiveInt();
        game.uiController.output.appendText("> " + defender.getName() + " is defending with " + battle.numDefenceUnits + " troops\n");
        battle("yes", attacker, defender);
    }

    public void attackCountry(String country, Player player) {
        battle.defenceCountryId = userInputLogic.shortCountryName(country);
        boolean adj = battle.assertAdjacent();
        if (!adj) {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(battle.defenceCountryId) + " You choose a country that is not adjacent.\n");
            game.uiController.askQuestion("What country do you wish to attack?");
        } else if (game.logic.getCountry_owner()[battle.defenceCountryId] != player.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(battle.defenceCountryId) + " to attack.\n");
            game.uiController.askQuestion("How many units do you wish to attack for you?");
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(battle.defenceCountryId) + " You choose a country that you own. \n");
            game.uiController.askQuestion("What country do you wish to attack?");
        }
    }

    /**
     * Ask do you want battle a country?
     *
     * @param input yes or no string to start battle
     */
    private void askToBattle(String input, Player player) {
        if (input.equals("yes")) {
            resetBattle();
            game.uiController.askQuestion("What country do you wish to attack from?");
            if (game.isOnline)
                player.getCsc().writeBoolean(true);
        } else {
            //END OF BATTLE
            game.uiController.output.appendText("> You chose not to battle.\n");
            game.uiController.askQuestion("Do you want to fortify your territories");
            if (game.isOnline)
                player.getCsc().writeBoolean(false);

        }
    }

    /**
     * Resets all the variables in battle
     */
    protected void resetBattle() {
        battle.invasionVictory = false;
        battle.invasionLoss = false;
        battle.attackCountryId = -1;
        battle.defenceCountryId = -1;
        battle.numAttackUnits = -1;
        battle.numDefenceUnits = -1;
    }

    /**
     * Asks a user for a country to battle from
     *
     * @param country the input of the country
     * @param player  the current players instance
     */
    private void battleFrom(String country, Player player) {
        battle.attackCountryId = userInputLogic.shortCountryName(country);

        if (game.logic.getCountry_owner()[battle.attackCountryId] != player.getColour()) {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(battle.attackCountryId) + " You chose a country you do not own. \n");
            game.uiController.askQuestion("What country do you wish to attack from?");
        } else if (game.logic.getTroop_count()[battle.attackCountryId] <= 1) {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(battle.attackCountryId) + " You chose a country that has less than two troops. \n");
            game.uiController.askQuestion("What country do you wish to attack from?");
        } else {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(battle.attackCountryId) + " to attack from.\n");
            game.uiController.askQuestion("What country do you wish to attack?");
        }
    }

    /**
     * Used to place process input and call functions in Game to place troops
     *
     * @param input  the amount of troops that want to be placed
     * @param player the player placing them
     */
    private void placeTroops(String input, Player player) {
        troops = catchIntException(input);
        if(troops == -1) {
            game.uiController.askQuestion("How many troops do you want to place");
            return;
        }

        if (troops < 0 || troops > player.getTroops())
            incorrectNumber();
        else if (game.logic.getInitPhase() && troops > player.getInitTroops())
            incorrectNumber();
        else {
            player.setTroops(player.getTroops() - troops);
            if (game.logic.getInitPhase())
                player.setInitTroops(player.getInitTroops() - troops);
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * Used to call functions in Game to reinforce a held territory of a player
     *
     * @param input      user response to question asking them do they want to reinforce the selected country
     * @param player     the player instance
     * @param nextPlayer the next player instance
     */
    private void reinforceCountry(String input, Player player, Player nextPlayer) {
        if (input.equals("yes")) {
            game.setCountry(countryIndex, player.getColour(), troops);
            endPlacingTroops(player, nextPlayer);
        } else {
            game.uiController.output.appendText("> You chose not to reinforce this country \n");
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * This is used for the Neutral (non player) entities to be allocated countries
     *
     * @param nextPlayer this is used for after the neutrals get their countries, to progress the game and let one of the actual players make moves
     */
    private void chooseNeutralTerritory(Player nextPlayer) {
        game.uiController.output.appendText("> Neutral countries allocating troop\n");
        Random random = new Random();
        int countryId = random.nextInt(6);
        game.setCountry(game.logic.getOwnedGray().get(countryId), Constants.PLAYER_COLOUR.GREY, 1);
        game.setCountry(game.logic.getOwnedOrange().get(countryId), Constants.PLAYER_COLOUR.ORANGE, 1);
        game.setCountry(game.logic.getOwnedGreen().get(countryId), Constants.PLAYER_COLOUR.GREEN, 1);
        game.setCountry(game.logic.getOwnedPurple().get(countryId), Constants.PLAYER_COLOUR.PURPLE, 1);
        neutralTurnCountdown = Constants.NUM_PLAYERS;

        if (game.isOnline && nextPlayer.getTroops() > 0) {
        } else if (nextPlayer.getTroops() > 0)
            askForTroops(nextPlayer);
        else
            game.endInitPhase();
    }

    /**
     * Used for when all the initial troop numbers are allocated to countries
     *
     * @param player     the player doing the operations
     * @param nextPlayer the player waiting for their turn
     */
    private void endPlacingTroops(Player player, Player nextPlayer) {
        if (player.getInitTroops() > 0 && game.logic.getInitPhase() || player.getTroops() > 0) {
            game.uiController.output.appendText("> You have " + player.getTroops() + " troops left to move \n");
            game.uiController.askQuestion("How many troops do you want to place");
        } else {
            //Todo: check logic of next player
            if (game.logic.getInitPhase()) {
                userInputLogic.nextTurn(player, nextPlayer);
                neutralTurnCountdown--;
                if (neutralTurnCountdown == 0)
                    chooseNeutralTerritory(nextPlayer);
                else if (!game.isOnline)
                    askForTroops(nextPlayer);
                if (game.isOnline)
                    handleOnlineReinforcement(player, nextPlayer);
            } else {
                if (game.isOnline)
                    handleOnlineReinforcement(player, nextPlayer);
                if(checkInvasionIsAvailable(player))
                    game.uiController.askQuestion("Would you like to invade a country? (yes/no)");
                else
                    battle("no", player, nextPlayer);
            }
        }
    }

    private boolean checkInvasionIsAvailable(Player player) {
        for(int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            if(game.logic.getTroop_count()[i] > 1 && game.logic.getCountry_owner()[i] == player.getColour())
                return true;
        }

        return false;
    }

    private void handleOnlineReinforcement(Player player, Player nextPlayer) {
        if (game.isOnline && game.logic.getInitPhase()) {
            player.getCsc().writeBoolean(true);
            player.onlineGameHandler.sendInt(game.logic.getTroop_count()[countryIndex], player.getCsc());
            player.onlineGameHandler.sendInt(countryIndex, player.getCsc());
            player.onlineGameHandler.sendIntArray(game.logic.getTroop_count(), player.getCsc());
            game.uiController.output.appendText("> You must now wait for " + nextPlayer.getName() + " to reinforce there territories\n");
            Thread t = new Thread(() -> {
                try {
                    game.reinforcementTurn(nextPlayer, player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        } else {
            player.getCsc().writeBoolean(false);
            player.onlineGameHandler.sendInt(game.logic.getTroop_count()[countryIndex], player.getCsc());
            player.onlineGameHandler.sendInt(countryIndex, player.getCsc());
            player.onlineGameHandler.sendIntArray(game.logic.getTroop_count(), player.getCsc());
        }
    }

    /**
     * Used to start the troop laying process
     *
     * @param player the player who's turn it is
     */
    private void askForTroops(Player player) {
        game.uiController.output.appendText("> " + player.getName() + ", you will now reinforce your territories. You have " + player.getTroops() + " troops left. You can place 3 troops max at a time\n");
        game.uiController.askQuestion("How many troops do you want to place");
    }

    /**
     * for when an invalid number of troops is requested to be placed
     */
    private void incorrectNumber() {
        game.uiController.output.appendText("You placed an incorrect number of troops. Try again \n");
        game.uiController.askQuestion("How many troops do you want to place");
    }

    /**
     * Asks a user for a country to reinforce
     *
     * @param country the input of the country
     * @param player  the current players instance
     */
    private void askForCountry(String country, Player player) {
        setCountryIndex(userInputLogic.shortCountryName(country));
        if (game.logic.getCountry_owner()[countryIndex] == player.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(countryIndex) + "\n");
            game.uiController.askQuestion("Are you sure you want to reinforce this country? (yes/no)");
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(countryIndex) + " You chose a country you do not own. \n");
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * Asks a player what country do they want to fortify
     *
     * @param input      user input determining whether they want to enter the fortify stage
     * @param player     instance of current player
     * @param nextPlayer instance of next player
     */
    private void fortifyCountry(String input, Player player, Player nextPlayer) {
        if (input.equals("yes")) {
            game.uiController.askQuestion("What country do you want to fortify");
        } else {
            if (player.isConquerTerritory()) {
                // ASK TO DRAW CARD
                game.uiController.output.appendText("> You conquered a territory. You will now be given a territory card\n");
                Card card = game.logic.deck.removeCard();
                game.uiController.output.appendText("> You selected a " + card.toString() + ".\n");
                player.addCardToHand(card);
                game.uiController.output.appendText(player.printCardHand());
                player.setConquerTerritory(false); //resetting it to false
            }
            if(player.getCardsInHand() >= 5) {
                game.uiController.output.appendText("> You have 5 or more cards. You must exchange your cards\n");
                askToPlayCards(player, nextPlayer, "yes");
            } else if(Sets.isValidSet(player.getInsignias()))
                game.uiController.askQuestion("Do you want to exchange any of your cards? (yes/no)");
            else {
                switchTurn(player, nextPlayer);
            }
        }
    }

    private void switchTurn(Player player, Player nextPlayer) {
        userInputLogic.nextTurn(player, nextPlayer);
        game.uiController.output.appendText("> It is now " + nextPlayer.getName() + " turn\n");
        nextPlayer.updateTroops(game.logic.calculateReinforcements(nextPlayer));
        game.uiController.output.appendText("> You have a total of " + nextPlayer.getTroops() + " troops to place\n");
        game.uiController.askQuestion("How many troops do you want to place");
    }

    /**
     * Receives the country a user entered and asks the user to confirm that is the territory they selected
     *
     * @param country user input of a country
     * @param player  the current player instance
     */
    private void askToFortify(String country, Player player) {
        setCountryIndex(userInputLogic.shortCountryName(country));
        if (game.logic.getCountry_owner()[countryIndex] == player.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(countryIndex) + " to fortify.\n");
            countriesThatCanFortify(player);
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(countryIndex) + " You choose a country you do not own. \n");
            game.uiController.askQuestion("What country do you want to fortify");
        }
    }

    /**
     * Shows the countries that can fortify the selected territory
     *
     * @param player the current player instance
     */
    private void countriesThatCanFortify(Player player) {
        int count = 0;
        for (int i = 0; i < Constants.ADJACENT[countryIndex].length; i++) {
            Constants.PLAYER_COLOUR color = game.logic.getCountry_owner()[Constants.ADJACENT[countryIndex][i]];
            int troopCount = game.logic.getTroop_count()[Constants.ADJACENT[countryIndex][i]];
            if (color == player.getColour() && troopCount > 1) {
                String country = Constants.COUNTRY_NAMES.get(Constants.ADJACENT[countryIndex][i]);
                game.uiController.output.appendText("> You can fortify " + Constants.COUNTRY_NAMES.get(countryIndex) + " with " + country + " with at most "
                        + (troopCount - 1) + " troops \n");
                count++;
                setAdjacentIndex(i);
            }
        }
        if (count == 0) {
            game.uiController.output.appendText("> There are no adjacent countries you own \n");
            game.uiController.askQuestion("Do you want to fortify your territories");
        } else if (count == 1) {
            game.uiController.askQuestion("How many troops do you want to move");
        } else {
            game.uiController.askQuestion("What country will fortify your country");
        }
    }

    /**
     * Takes user input for amount of troops the player wants to fortify their territory with
     *
     * @param input  user input for number of troops to fortify
     * @param player player instance
     */
    private void troopsToFortify(String input, Player player, Player nextPlayer) {
        int troops = catchIntException(input);
        if(troops == -1) {
            game.uiController.askQuestion("How many troops do you want to move");
            return;
        }
        if (troops < 0 || troops >= game.logic.getTroop_count()[Constants.ADJACENT[countryIndex][adjacentIndex]])
            incorrectNumber();
        else {
            game.takeCountry(countryIndex, player.getColour(), troops);
            game.takeCountry(Constants.ADJACENT[countryIndex][adjacentIndex], player.getColour(), -troops);
        }
        fortifyCountry("no", player, nextPlayer);
    }



    /**
     * Takes the adjacent country and competes a fortify action
     *
     * @param country the adjacent country from user input
     * @param player  current player instance
     */
    private void countryToGive(String country, Player player) {
        setAdjacentIndex(userInputLogic.shortCountryName(country));
        boolean isThere = false;
        for (int i = 0; i < Constants.ADJACENT[countryIndex].length; i++) {
            if (Constants.ADJACENT[countryIndex][i] == adjacentIndex) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            game.uiController.askQuestion("How many troops do you want to move?");
        } else {
            game.uiController.output.appendText("> You do not own \n" + Constants.COUNTRY_NAMES.get(adjacentIndex));
            game.uiController.askQuestion("What country will fortify your country");
        }
    }

    public void setCountryIndex(int countryIndex) {
        this.countryIndex = countryIndex;
    }

    public void setAdjacentIndex(int adjacentIndex) {
        this.adjacentIndex = adjacentIndex;
    }

    /*Stores variables for attacking*/
    public class Battle {
        public int attackCountryId;
        public int defenceCountryId;
        public int numAttackUnits;
        public int numDefenceUnits;
        public boolean invasionVictory;
        public boolean invasionLoss;

        /**
         * Asserts that the two countries are adjacent
         *
         * @return true if the countries are adjacent
         */
        public boolean assertAdjacent() {
            //Assert that the country is adjacent
            for (int adjCountry : Constants.ADJACENT[attackCountryId]) {
                if (defenceCountryId == adjCountry) {
                    return true;
                }
            }
            return false;
        }

        public boolean assertValidAttackers() {
            //Assert that the number of troops used to attack is valid
            return numAttackUnits >= 1 && game.logic.troop_count[attackCountryId] - 1 >= numAttackUnits && numAttackUnits <= 3;
        }

        public boolean assertValidDefenders() {
            //Assert number of defence units is valid
            return numDefenceUnits >= 1 && numDefenceUnits <= game.logic.troop_count[defenceCountryId] && numDefenceUnits <= 2;
        }

        public void calculateBattleSequence(ArrayList<Integer> attackerDice, ArrayList<Integer> defenderDice) {
            int iter = Math.min(numAttackUnits, numDefenceUnits);
            for (int i = 0; i < iter; i++) {
                if (attackerDice.get(i) > defenderDice.get(i)) {
                    game.logic.getTroop_count()[defenceCountryId]--;
                    numDefenceUnits--;
                } else {
                    game.logic.getTroop_count()[attackCountryId]--;
                    numAttackUnits--;
                }
                if (numDefenceUnits == 0 && game.logic.getTroop_count()[defenceCountryId] == 0) {
                    invasionVictory = true;
                    break;
                }
                if (numAttackUnits == 0 && game.logic.getTroop_count()[attackCountryId] == 1) {
                    invasionLoss = true;
                    break;
                }
            }
        }
    }
}