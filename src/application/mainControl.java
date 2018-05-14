package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class mainControl {

    @FXML
    private GridPane ship_menu_border;

    @FXML
    private FlowPane fp_Hints;

    @FXML
    private GridPane player_Border;

    @FXML
    private Button btn_start1;

    @FXML
    private GridPane hints_Border;

    @FXML
    private GridPane ship_menu;

    @FXML
    private FlowPane fp_Game;

    @FXML
    private GridPane player_field;

    @FXML
    private Group pc_Group;

    @FXML
    private Group player_Group;

    @FXML
    private Button btn_end;

    @FXML
    private Button btn_ship1;

    @FXML
    private Button btn_ship2;

    @FXML
    private GridPane pc_field;

    @FXML
    private AnchorPane anchor_Game;

    @FXML
    private Button btn_ship3;

    @FXML
    private Button btn_ship4;

    @FXML
    private GridPane pc_Border;

    @FXML
    private Button btn_placeShip;

    private GameField[][] gfPlayer = new GameField[10][10];
    private GameField[][] gfPC = new GameField[10][10];

    //Brauche hier noch 4 Arrays mit jew. Schiffen

    private Ship[] battShipArr = new Ship[5];
    private Ship[] kreuzerArr = new Ship[4];
    private Ship[] zerstArr = new Ship[3];
    private Ship[] ubootArr = new Ship[4];
    private ArrayList<Ship> allShips = new ArrayList<>();

    private Ship currShip;

    public Ship getCurrShip() {
        return this.currShip;
    }

    @FXML
    void startGame(ActionEvent event) {
        //TODO: f�r Gruppe Control
    }

    @FXML
    void endGame(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void placeShip(ActionEvent event) {
        btn_ship1.setVisible(true);
        btn_ship2.setVisible(true);
        btn_ship3.setVisible(true);
        btn_ship4.setVisible(true);
        allShips.add(currShip);
    }

    @FXML
    void setShip1(ActionEvent event) throws FileNotFoundException {
        //Schlachschiff
        Ship ship1 = new Ship(0);
        //System.out.println(ship.getCurrAnzSchlacht());
        battShipArr[ship1.getCurrAnzSchlacht() - 1] = ship1;
        setShip(ship1.getLength(), battShipArr[ship1.getCurrAnzSchlacht() - 1]);
        System.out.println("Schlachtschiff");
        hideButtons(0);
        this.currShip = battShipArr[ship1.getCurrAnzSchlacht() - 1];
    }

    @FXML
    void setShip2(ActionEvent event) throws FileNotFoundException {
        //Kreuzer
        Ship ship2 = new Ship(1);
        kreuzerArr[ship2.getCurrAnzKreuz() - 1] = ship2;
        setShip(ship2.getLength(), kreuzerArr[ship2.getCurrAnzKreuz() - 1]);
        System.out.println("Kreuzer");
        hideButtons(1);
        this.currShip = kreuzerArr[ship2.getCurrAnzKreuz() - 1];
    }

    @FXML
    void setShip3(ActionEvent event) throws FileNotFoundException {
        //Zerst�rer
        Ship ship3 = new Ship(2);
        zerstArr[ship3.getCurrAnzZerst() - 1] = ship3;
        setShip(ship3.getLength(), zerstArr[ship3.getCurrAnzZerst() - 1]);
        System.out.println("Zerst�rer");
        hideButtons(2);
        this.currShip = zerstArr[ship3.getCurrAnzZerst() - 1];
    }

    @FXML
    void setShip4(ActionEvent event) throws FileNotFoundException {
        //U-Boot
        Ship ship4 = new Ship(3);
        System.out.println(ship4.getCurrAnzUBoot());
        ubootArr[ship4.getCurrAnzUBoot() - 1] = ship4;
        setShip(ship4.getLength(), ubootArr[ship4.getCurrAnzUBoot() - 1]);
        System.out.println("U-Boot");
        //Buttons ausblenden und navigieren erm�glichen
        hideButtons(3);
        this.currShip = ubootArr[ship4.getCurrAnzUBoot() - 1];
    }

    public void hideButtons(int initButton) {
        switch (initButton) {
            case 0:
                btn_ship2.setVisible(false);
                btn_ship3.setVisible(false);
                btn_ship4.setVisible(false);
                break;
            case 1:
                btn_ship1.setVisible(false);
                btn_ship3.setVisible(false);
                btn_ship4.setVisible(false);
                break;
            case 2:
                btn_ship1.setVisible(false);
                btn_ship2.setVisible(false);
                btn_ship4.setVisible(false);
                break;
            case 3:
                btn_ship1.setVisible(false);
                btn_ship2.setVisible(false);
                btn_ship3.setVisible(false);
                break;
        }
    }

    public GridPane getPlayerField() {
        return this.player_field;
    }

    public void moveShipUp() throws FileNotFoundException {
        //Schiff Position zwischenspeichern
        GameField[] gpTemp = new GameField[this.currShip.getLength()];
        //Temp mit Feldern des ausgew. Schiffes f�llen
        gpTemp = this.currShip.getUsedGameFields();
        //showStats(this.currShip);

        //TODO: Pr�fung ob an anderem Schiff?
        //Pr�fung ob an Grenze
        if (gpTemp[0].getYKoord() != 0) {
            //setWater(gpTemp[gpTemp.length-1].getXKoord(),gpTemp[gpTemp.length-1].getYKoord());
            //F�r L�nge des Schiffes
            for (int i = 0; i < this.currShip.getLength(); i++) {
                //Feld mit Wasser �berschreiben
                //setWater(gpTemp[i].getXKoord(),gpTemp[i].getYKoord());
                //Felder in Temp. var in jew. Koord erh�hen
                /*
                 * nach oben: y-1
                 * nach unten: y+1
                 * nach links: x-1
                 * nach rechts: x+1
                 * */
                gpTemp[i].setKoords(gpTemp[i].getXKoord(), gpTemp[i].getYKoord() - 1);
                //switchStats(gpTemp[i].getXKoord(), gpTemp[i].getYKoord(), gpTemp[i].getXKoord(), gpTemp[i].getYKoord() + 1);
            }
            this.currShip.setUsedGameFields(gpTemp);
            renderField();
            //this.currShip.viewUsedGameFields();
            printShipArr();
        } else {
            //TODO: Error abfangen
            System.out.println("Schiff befindet sich ganz oben!");
        }
        //gpTemp in player_Field schreiben
    }

    public void moveShipDown() throws FileNotFoundException {
        //Schiff Position zwischenspeichern
        GameField[] gpTemp = new GameField[this.currShip.getLength()];
        gpTemp = this.currShip.getUsedGameFields();
        //showStats(this.currShip);
        if (gpTemp[gpTemp.length - 1].getYKoord() != 9) {
            for (int i = this.currShip.getLength() - 1; i >= 0; i--) {
                gpTemp[i].setKoords(gpTemp[i].getXKoord(), gpTemp[i].getYKoord() + 1);
                switchStats(gpTemp[i].getXKoord(), gpTemp[i].getYKoord(), gpTemp[i].getXKoord(), gpTemp[i].getYKoord() - 1);
            }
            this.currShip.setUsedGameFields(gpTemp);
            //this.currShip.viewUsedGameFields();
            renderField();
            printShipArr();
        } else {
            //TODO: Error abfangen
            System.out.println("Schiff befindet sich ganz unten!");
        }
    }

    public void moveShipLeft() throws FileNotFoundException {
        //Schiff Position zwischenspeichern
        GameField[] gpTemp = new GameField[this.currShip.getLength()];
        gpTemp = this.currShip.getUsedGameFields();
        //showStats(this.currShip);

        if (gpTemp[0].getXKoord() != 0) {
            for (int i = 0; i < this.currShip.getLength(); i++) {

                gpTemp[i].setKoords(gpTemp[i].getXKoord() - 1, gpTemp[i].getYKoord());
                this.gfPlayer[gpTemp[i].getXKoord()][gpTemp[i].getYKoord()].setStatus(1);
                //switchStats(gpTemp[i].getXKoord(),gpTemp[i].getYKoord(),gpTemp[i].getXKoord()+1,gpTemp[i].getYKoord());

            }
            this.currShip.setUsedGameFields(gpTemp);
            //this.currShip.viewUsedGameFields();
            renderField();
            printShipArr();
        } else {
            //TODO: Error abfangen
            System.out.println("Schiff befindet sich ganz links!");
        }
    }

    public void moveShipRight() throws FileNotFoundException {
        //Schiff Position zwischenspeichern
        GameField[] gpTemp = new GameField[this.currShip.getLength()];
        gpTemp = this.currShip.getUsedGameFields();
        //showStats(this.currShip);

        if (gpTemp[gpTemp.length - 1].getXKoord() != 9) {
            for (int i = this.currShip.getLength() - 1; i >= 0; i--) {
                gpTemp[i].setKoords(gpTemp[i].getXKoord() + 1, (gpTemp[i].getYKoord()));
                //switchStats(gpTemp[i].getXKoord(),gpTemp[i].getYKoord(),gpTemp[i].getXKoord()-1,gpTemp[i].getYKoord());
            }
            this.currShip.setUsedGameFields(gpTemp);
            //this.currShip.viewUsedGameFields();
            renderField();
            printShipArr();
        } else {
            //TODO: Error abfangen
            System.out.println("Schiff befindet sich ganz rechts!");
        }
        //gpTemp in player_Field schreiben
    }

    private void showStats(Ship ship) {
        System.out.println("Schiff: " + ship.getType() + "\n");
        for (int i = 0; i < ship.getUsedGameFields().length; i++) {
            System.out.println("Koord No.:" + i + "\n");
            System.out.println("**XKor: " + ship.getUsedGameFields()[i].getXKoord() + "\n");
            System.out.println("**YKor: " + ship.getUsedGameFields()[i].getYKoord() + "\n");
            System.out.println("____");
        }
    }

    public void rotateShipRight() {

    }

    public void rotateShipLeft() {

    }

    private void switchStats(int x1, int y1, int x2, int y2) throws FileNotFoundException {
        GameField tempGf = new GameField();
        tempGf = this.gfPlayer[x1][y1];
        this.gfPlayer[x1][y1] = this.gfPlayer[x2][y2];
        this.gfPlayer[x2][y2] = tempGf;
    }

    private void renderField() throws FileNotFoundException {
        initGame();
        GameField currShipCoords[] = currShip.getUsedGameFields();
        ArrayList<GameField> usedCoords = new ArrayList<>();

        for (int i = 0; i < allShips.size(); i++) {
            Ship ship = allShips.get(i);
            GameField [] shipUsedGameFields = ship.getUsedGameFields();
            for(int j = 0; j < shipUsedGameFields.length; j++) {
                usedCoords.add(shipUsedGameFields[j]);
                usedCoords.get(j).setStatus(1);
                this.player_field.add(new ImageView(usedCoords.get(j).getImage()), usedCoords.get(j).getXKoord(), usedCoords.get(j).getYKoord());
            }
        }
        for (int i = 0; i < currShipCoords.length; i++) {
            currShipCoords[i].setStatus(1);
            this.player_field.add(new ImageView(currShipCoords[i].getImage()), currShipCoords[i].getXKoord(), currShipCoords[i].getYKoord());
        }
    }

    private void printShipArr() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(gfPlayer[j][i].getStatus() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n****************\n");
    }

    public void setShip(int length, Ship shipId) throws FileNotFoundException {
        GameField gfTemp[] = new GameField[length];

        for (int i = 0; i < length; i++) {
            //Schiff in Array deklarieren
            gfPlayer[0][i].setStatus(1);
            //GameFelder an ship binden
            gfTemp[i] = gfPlayer[0][i];
            //Bilder in player_field anpassen
            this.player_field.add(new ImageView(gfPlayer[0][i].getImage()), 0, i);
        }

        shipId.setUsedGameFields(gfTemp);
        //shipId.viewUsedGameFields();
    }

    public void initGame() throws FileNotFoundException {
        //Spieler Felder mit Wasser f�llen
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.gfPlayer[x][y] = new GameField();
                this.gfPlayer[x][y].setKoords(x, y);
                this.player_field.add(new ImageView(gfPlayer[x][y].getImage()), x, y);
            }
        }
        //PC Felder mit Wasser f�llen
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.gfPC[x][y] = new GameField();
                this.gfPC[x][y].setKoords(x, y);
                this.pc_field.add(new ImageView(gfPC[x][y].getImage()), x, y);
            }
        }
    }
}