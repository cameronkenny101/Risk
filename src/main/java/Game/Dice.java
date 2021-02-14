package Game;


import GameScreen.GameScreenController;

import java.util.Random;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Random;

public class Dice {

    public Dice() {

    }

    public int rollDice(Player player,GameScreenController uiController){

        Random random = new Random();

        uiController.output.appendText(">"+player.getColour().toString()+" would you like to roll the dice?\n");

        return random.nextInt(6) +1;
    }

    public void compare(int playerNumber1,int playerNumber2,GameScreenController uiController,Player player1,Player player2){

        if (playerNumber1 > playerNumber2){
            uiController.output.appendText(">"+player1.getColour().toString()+ " will place first\n");
        }
        if (playerNumber2 > playerNumber1){
            uiController.output.appendText(">"+player2.getColour().toString()+ " will place first\n");
        }else{
            uiController.output.appendText(">EQUAL VALUES ROLL AGAIN");
        }

    }


}
