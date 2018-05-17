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
    private static boolean shipCanBeSet;
    private static ArrayList<GameField> usedCoords = new ArrayList<>();

    private GameField[][] gfPlayer = new GameField[10][10];
    private GameField[][] gfPC = new GameField[10][10];

    //Speichern wieviele Schiffe von welchem Typ vorhanden sind
    private static int currAnzSchlacht = 0;
    private static int currAnzZerst = 0;
    private static int currAnzKreuz = 0;
    private static int currAnzUBoot = 0;
    private static final int MAX_ANZAHL_SCHLACHT = 1;
    private static final int MAX_ANZAHL_KREUZ = 2;
    private static final int MAX_ANZAHL_ZERST = 3;
    private static final int MAX_ANZAHL_UBOOT = 4;


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
    void placeShip(ActionEvent event) throws FileNotFoundException {
        if (shipCanBeSet) {
            allShips.add(currShip);
            switch (currShip.getType()) {
                case 0:
                    currAnzSchlacht++;
                    break;
                case 1:
                    currAnzKreuz++;
                    break;
                case 2:
                    currAnzZerst++;
                    break;
                case 3:
                    currAnzUBoot++;
                    break;
            }
            setVisibilityOfShipButtons();
            //Benutzten Koord befüllt
            GameField[] shipUsedGameFields = currShip.getUsedGameFields();
            for (int j = 0; j < shipUsedGameFields.length; j++) {
                if (currShip.getDir() == -1) {
                    shipUsedGameFields[j].setStatus(ImageStatus.SCHIFF_MITTE_HOR);
                } else {
                    shipUsedGameFields[j].setStatus(ImageStatus.SCHIFF_MITTE_VER);
                }
                this.player_field.add(new ImageView(shipUsedGameFields[j].getImage()), shipUsedGameFields[j].getXKoord(), shipUsedGameFields[j].getYKoord());
                usedCoords.add(shipUsedGameFields[j]);
            }
        }
    }


    //Blendet Schiffburtons aus falls Max Anzahl erreicht wurde
    void setVisibilityOfShipButtons() {
        btn_ship1.setVisible(false);
        btn_ship2.setVisible(false);
        btn_ship3.setVisible(false);
        btn_ship4.setVisible(false);

        if (currAnzSchlacht < MAX_ANZAHL_SCHLACHT) {
            btn_ship1.setVisible(true);
        }
        if (currAnzKreuz < MAX_ANZAHL_KREUZ) {
            btn_ship2.setVisible(true);
        }
        if (currAnzZerst < MAX_ANZAHL_ZERST) {
            btn_ship3.setVisible(true);
        }
        if (currAnzUBoot < MAX_ANZAHL_UBOOT) {
            btn_ship4.setVisible(true);
        }
    }

    @FXML
    void setShip1(ActionEvent event) throws FileNotFoundException {
        //Schlachschiff
        Ship ship1 = new Ship(0);
        this.currShip = ship1;
        setShip(ship1.getLength(), currShip);
        hideButtons(0);
        renderField();
    }

    @FXML
    void setShip2(ActionEvent event) throws FileNotFoundException {
        //Kreuzer
        Ship ship2 = new Ship(1);
        this.currShip = ship2;
        setShip(ship2.getLength(), currShip);
        hideButtons(1);
        renderField();
    }

    @FXML
    void setShip3(ActionEvent event) throws FileNotFoundException {
        //Zerstörer
        Ship ship3 = new Ship(2);
        this.currShip = ship3;
        setShip(ship3.getLength(), currShip);
        hideButtons(2);
        renderField();
    }

    @FXML
    void setShip4(ActionEvent event) throws FileNotFoundException {
        //U-Boot
        Ship ship4 = new Ship(3);
        currShip = ship4;
        setShip(ship4.getLength(), currShip);
        hideButtons(3);
        renderField();
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
        //Temp mit Feldern des ausgew. Schiffes füllen
        gpTemp = this.currShip.getUsedGameFields();

        //TODO: Pr�fung ob an anderem Schiff?
        //Prüfung ob an Grenze
        if (gpTemp[0].getYKoord() != 0) {
            for (int i = 0; i < this.currShip.getLength(); i++) {
                gpTemp[i].setKoords(gpTemp[i].getXKoord(), gpTemp[i].getYKoord() - 1);
            }
            this.currShip.setUsedGameFields(gpTemp);
            renderField();
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
        if (gpTemp[gpTemp.length - 1].getYKoord() != 9) {
            for (int i = this.currShip.getLength() - 1; i >= 0; i--) {
                gpTemp[i].setKoords(gpTemp[i].getXKoord(), gpTemp[i].getYKoord() + 1);
            }
            this.currShip.setUsedGameFields(gpTemp);
            renderField();
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
            }
            this.currShip.setUsedGameFields(gpTemp);
            renderField();
        } else {
            //TODO: Error abfangen
            System.out.println("Schiff befindet sich ganz links!");
        }
    }

    public void moveShipRight() throws FileNotFoundException {
        //Schiff Position zwischenspeichern
        GameField[] gpTemp = new GameField[this.currShip.getLength()];
        gpTemp = this.currShip.getUsedGameFields();
        if (gpTemp[gpTemp.length - 1].getXKoord() != 9) {
            for (int i = this.currShip.getLength() - 1; i >= 0; i--) {
                gpTemp[i].setKoords(gpTemp[i].getXKoord() + 1, (gpTemp[i].getYKoord()));
            }
            this.currShip.setUsedGameFields(gpTemp);
            renderField();
        } else {
            //TODO: Error abfangen
            System.out.println("Schiff befindet sich ganz rechts!");
        }
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

    public void rotateShip() throws FileNotFoundException {
        currShip.setDir(currShip.getDir() * -1);
        GameField[] gpTemp = new GameField[currShip.getLength()];
        gpTemp = this.currShip.getUsedGameFields();
        for (int i = this.currShip.getLength() - 1; i >= 0; i--) {
            gpTemp[i].setKoords(gpTemp[i].getYKoord(), (gpTemp[i].getXKoord()));
        }
        this.currShip.setUsedGameFields(gpTemp);
        renderField();
    }

    private void switchStats(int x1, int y1, int x2, int y2) throws FileNotFoundException {
        GameField tempGf = new GameField();
        tempGf = this.gfPlayer[x1][y1];
        this.gfPlayer[x1][y1] = this.gfPlayer[x2][y2];
        this.gfPlayer[x2][y2] = tempGf;
    }

    private void renderField() throws FileNotFoundException {
        refreshGame();
        GameField currShipCoords[] = currShip.getUsedGameFields();
        for (int i = 0; i < usedCoords.size(); i++) {
            this.player_field.add(new ImageView(usedCoords.get(i).getImage()), usedCoords.get(i).getXKoord(), usedCoords.get(i).getYKoord());
            gfPlayer[usedCoords.get(i).getXKoord()][usedCoords.get(i).getYKoord()].setStatus(usedCoords.get(i).getStatus());
        }
        printShipArr();
        shipCanBeSet = true;
        for (int i = 0; i < currShipCoords.length; i++) {
            //umliegende Felder prüfen
            if (checkAdjacentFields(currShipCoords[i])) {
                //ausrichtung des Schiffs prüfen
                if (i == 0){
                    if (currShip.getDir() == -1
                }else{
                    if (currShip.getDir() == -1) {
                        currShipCoords[i].setStatus(ImageStatus.CURR_MITTE_HOR);
                    } else {
                        currShipCoords[i].setStatus(ImageStatus.CURR_MITTE_VER);
                    }
                this.player_field.add(new ImageView(currShipCoords[i].getImage()), currShipCoords[i].getXKoord(), currShipCoords[i].getYKoord());
            } else {
                shipCanBeSet = false;
                if (currShip.getDir() == -1){
                    currShipCoords[i].setStatus(ImageStatus.NOPE_HOR);
                }else{
                    currShipCoords[i].setStatus(ImageStatus.NOPE_VER);
                }
                this.player_field.add(new ImageView(currShipCoords[i].getImage()), currShipCoords[i].getXKoord(), currShipCoords[i].getYKoord());
            }
        }
    }

    private boolean checkAdjacentFields(GameField shipCoords) {
        int x_min = shipCoords.getXKoord() - 1;
        int y_min = shipCoords.getYKoord() - 1;
        if (x_min < 0) {
            x_min = 0;
        }
        if (y_min < 0) {
            y_min = 0;
        }
        int x_max = shipCoords.getXKoord() + 1;
        int y_max = shipCoords.getYKoord() + 1;
        if (x_max > 9) {
            x_max = 9;
        }
        if (y_max > 9) {
            y_max = 9;
        }
        for (int x = x_min; x <= x_max; x++) {
            for (int y = y_min; y <= y_max; y++) {
                if (gfPlayer[x][y].getStatus() != ImageStatus.WASSER) {
                    return false;
                }
            }
        }
        return true;
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
            gfPlayer[0][i].setStatus(ImageStatus.CURR_MITTE_VER);
            //GameFelder an ship binden
            gfTemp[i] = gfPlayer[0][i];
            //Bilder in player_field anpassen
            this.player_field.add(new ImageView(gfPlayer[0][i].getImage()), 0, i);
        }
        shipId.setUsedGameFields(gfTemp);
    }

    public void initGame() throws FileNotFoundException {
        //Spieler Felder mit Wasser fuellen
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.gfPlayer[x][y] = new GameField();
                this.gfPlayer[x][y].setKoords(x, y);
                this.player_field.add(new ImageView(gfPlayer[x][y].getImage()), x, y);
            }
        }
        //PC Felder mit Wasser fuellen
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.gfPC[x][y] = new GameField();
                this.gfPC[x][y].setKoords(x, y);
                this.pc_field.add(new ImageView(gfPC[x][y].getImage()), x, y);
            }
        }
    }

    public void refreshGame() throws FileNotFoundException {
        //Spieler Felder mit Wasser fuellen
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                this.gfPlayer[x][y] = new GameField();
                this.gfPlayer[x][y].setKoords(x, y);
                this.player_field.add(new ImageView(gfPlayer[x][y].getImage()), x, y);
            }
        }
    }
}