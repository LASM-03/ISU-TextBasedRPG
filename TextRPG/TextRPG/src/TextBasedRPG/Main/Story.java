package TextBasedRPG.Main;

import TextBasedRPG.Main.Weapons.Monster_Diablo;
import TextBasedRPG.Main.Weapons.Monster_Goblin;
import TextBasedRPG.Main.Weapons.SuperMonster;
import TextBasedRPG.Main.Weapons.Weapon_GodSword;
import TextBasedRPG.Main.Weapons.Weapon_Knife;
import TextBasedRPG.Main.Weapons.Weapon_LongSword;

public class Story {

    Game game;
    UI ui;
    VisibilityManager vm;
    Player player = new Player();
    SuperMonster monster;

    int silverRing;
    int diabloHead;

    public Story(Game g, UI userInterface, VisibilityManager vManager){

        game = g;
        ui = userInterface;
        vm = vManager;

    }

    public void defaultSetup(){

        player.hp = 10;
        ui.hpNumberLabel.setText("" + player.hp);

        player.currentWeapon = new Weapon_Knife();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        silverRing = 0;

        diabloHead = 0;

    }

    public void selectPosition(String nextPosition){

        switch(nextPosition){
            case "townGate": townGate(); break;
            case "talkGateKeeper": talkGateKeeper(); break;
            case "attackGateKeeper": attackGateKeeper(); break;
            case "crossRoad": crossRoad(); break;
            case "north": north(); break;
            case "east": east(); break;
            case "west": west(); break;
            case "fight": fight(); break;
            case "playerAttack": playerAttack(); break;
            case "monsterAttack": monsterAttack(); break;
            case "win": win(); break;
            case "lose": lose(); break;
            case "ending": ending(); break;
            case "toTitle": toTitle(); break;

        }

    }

    public void townGate(){

        ui.mainTextArea.setText("You are at the gate of the town. \nA GateKeeper is standing in front of you. \n\nWhat do you do?");
        ui.choice1.setText("Talk to GateKeeper");
        ui.choice2.setText("Attack GateKeeper");
        ui.choice3.setText("Leave");
        ui.choice4.setText("");

        game.nextPosition1 = "talkGateKeeper";
        game.nextPosition2 = "attackGateKeeper";
        game.nextPosition3 = "crossRoad";
        game.nextPosition4 = "";
    }

    public void talkGateKeeper(){

        if(silverRing == 0){
            
        ui.mainTextArea.setText("GateKeeper: Hello, strange wanderer. I have never seen you're face around here before. \nI'm sorry, but I cannot let a stranger enter the town.");
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "townGate";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        }
        else if (silverRing == 1){

            ending();

        }
        
        if (diabloHead == 1){

            secretEnding();
        }
    }
    
    public void attackGateKeeper(){

        ui.mainTextArea.setText("GateKeeper: HEY! Don't be stupid! \n\nThe GateKeeper hit you so hard, you gave up. \n(You receive 3 damage)");
        player.hp = player.hp - 3;
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "townGate";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void crossRoad(){

        ui.mainTextArea.setText("You are at a cross road.\nIf you go south, you will go back to the town gate.");
        ui.choice1.setText("Go north");
        ui.choice2.setText("Go east");
        ui.choice3.setText("Go south");
        ui.choice4.setText("Go west");

        game.nextPosition1 = "north";
        game.nextPosition2 = "east";
        game.nextPosition3 = "townGate";
        game.nextPosition4 = "west";

    }

    public void north(){

        ui.mainTextArea.setText("There is a river.\nYou drink the water and rest at the river side\n\n(Your HP is restored by 5)");
        player.hp = player.hp + 5;
        ui.hpNumberLabel.setText("" + player.hp);
        ui.choice1.setText("Go south");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void east(){

        int i = new java.util.Random().nextInt(100) + 1;

        if(i < 90){
            
        ui.mainTextArea.setText("You walked into a ruined structure and found a long sword!\n\n(You obtained a Long Sword)");

        player.currentWeapon = new Weapon_LongSword();
        ui.weaponNameLabel.setText(player.currentWeapon.name);

        }
        else {

            ui.mainTextArea.setText("You walked into a shallow cove underneath a waterfall and stumbled upon the all powerful God Killer!\n\n(You obtained a God Sword)");

            player.currentWeapon = new Weapon_GodSword();
            ui.weaponNameLabel.setText(player.currentWeapon.name);

        }
       
        ui.choice1.setText("Go west");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void west(){

        int i = new java.util.Random().nextInt(100) + 1;

        if(i < 90){

            monster = new Monster_Goblin();

        }
        else{
            
            monster = new Monster_Diablo();

        }

        ui.mainTextArea.setText("You encounter a " + monster.name + "!");
        ui.choice1.setText("Fight");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");
    
        game.nextPosition1 = "fight";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";


    }

    public void fight(){

        ui.mainTextArea.setText(monster.name + ": " + monster.hp + "\n\nWhat do you do?");
        ui.choice1.setText("Attack");
        ui.choice2.setText("Run");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "playerAttack";
        game.nextPosition2 = "crossRoad";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
    }

    public void playerAttack(){

        int playerDamage = new java.util.Random().nextInt(player.currentWeapon.damage);

        ui.mainTextArea.setText("You attacked the " + monster.name + " and gave " + playerDamage + " damage!");

        monster.hp = monster.hp - playerDamage;

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(monster.hp > 0){

        game.nextPosition1 = "monsterAttack";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

        }
        else if (monster.hp < 1 ){

            game.nextPosition1 = "win";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";
    
        }
    }

    public void monsterAttack(){

        int monsterDamage = new java.util.Random().nextInt(monster.attack);
        
        ui.mainTextArea.setText(monster.attackMessage + "\nYou received " + monsterDamage + " damage!");

        player.hp = player.hp - monsterDamage;
        ui.hpNumberLabel.setText("" + player.hp);

        ui.choice1.setText(">");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        if(player.hp > 0){

            game.nextPosition1 = "fight";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";

        }
        else if(player.hp < 1){

            game.nextPosition1 = "lose";
            game.nextPosition2 = "";
            game.nextPosition3 = "";
            game.nextPosition4 = "";

        }
    }

    public void win(){

        if(monster.name == "Goblin" ){

            ui.mainTextArea.setText("You've defeated the " + monster.name + "!\nThe monster dropped a ring!\n\n(You obtained a Silver Ring!)");
        
            silverRing = 1;

        }
        else if(monster.name == "Diablo"){

            ui.mainTextArea.setText("You actually managed to defeat Diablo!\n You just accomplished something IMPOSSIBLE!\n\n(You obtained Diablo's Head!)");

            diabloHead = 1;

        }

        ui.choice1.setText("Go east");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "crossRoad";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void lose(){

        ui.mainTextArea.setText("You were killed by the " + monster.name + "!\nYou are dead!\n\n<GAME OVER>");

        ui.choice1.setText("To the title screen");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";

    }

    public void ending(){

        ui.mainTextArea.setText("GateKeeper: Oh, you killed the goblin!?\nThank you so much. That goblin has been tormenting merchants trying to get here for months.\nWelcome to the Town!\n\n<THE END>");
        
        ui.choice1.setText("<Play Again>");
        ui.choice2.setText("");
        ui.choice3.setText("");
        ui.choice4.setText("");

        game.nextPosition1 = "toTitle";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "";
     
    }

    public void secretEnding(){

        ui.mainTextArea.setText("After managing to defeat Diablo, the Lord of Terror, you become KING of this land...\nand you never see that GateKeeper again.\n\n<THE (secret) END>");
        
        ui.choice1.setText("Play Again?");
        ui.choice2.setText("Why would you?");
        ui.choice3.setText("You're already KING!");
        ui.choice4.setText("<Play Again>");

        game.nextPosition1 = "";
        game.nextPosition2 = "";
        game.nextPosition3 = "";
        game.nextPosition4 = "toTitle";

    }

    public void toTitle(){
       
        defaultSetup();
        vm.showTitleScreen();

    }
}
