package TextBasedRPG.Main;

public class VisibilityManager {

    UI ui;

    public VisibilityManager(UI userInterface){

        ui = userInterface;

    }

    public void showTitleScreen(){
        
        //Show title screen
        ui.titleNamePanel.setVisible(true);
        ui.startButtonPanel.setVisible(true);

        //Hide game screen
        ui.mainTextPanel.setVisible(false);
        ui.choiceButtonPanel.setVisible(false);
        ui.playerPanel.setVisible(false);

    }

    public void titleToTown(){

        //Hide title screen
        ui.titleNamePanel.setVisible(false);
        ui.startButtonPanel.setVisible(false);
 
        //Show game screen
        ui.mainTextPanel.setVisible(true);
        ui.choiceButtonPanel.setVisible(true);
        ui.playerPanel.setVisible(true);
 

    }
    
}
