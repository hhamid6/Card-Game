package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.PokerCard;
import model.PokerDeck;
import java.util.Random;
import model.Suits;
import runner.CasinoGamesRunner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

/**
 * This is the wartable class that extends FlowPane
 * essentially the game is played through this
 * @author hhamid6
 * @version 3.4
 */
public class WarTable extends FlowPane {

    private PokerCard playerCard;
    private PokerCard dealerCard;
    private TextField moneybet;
    private VBox playerBox;
    private VBox dealerBox;
    private VBox spaceBox;
    private VBox morespaceBox;
    private VBox somemorespace;
    private int playerMoney = 1000;
    private Button backBtn;
    private Button betBtn;
    private Button restartBtn;
    private Boolean statement = false;
    private Text currAmount;
    private Text totalMoney;
    private int amountofmoney;
    private PokerDeck pokerDeck;
    private Text cardPlayers;
    private Text cardDealers;
    private Text space;
    private Text spaces;
    private Text morespace;

    /**
     * Constructor that sets up layout of game
     */
    public WarTable() {
        // Initialize the poker deck, takes a moment.
        pokerDeck.initialize();
        // Complimentary HBox for holding the back button
        HBox bottomPanel = new HBox(50);
        this.setMargin(bottomPanel, new Insets(10, 10, 10, 10));
        // HBoxs for holding the cards
        playerBox = new VBox();
        dealerBox = new VBox();
        spaceBox = new VBox();
        morespaceBox = new VBox();
        somemorespace = new VBox();
        Image imagebgr = new Image("file:images/table.jpg");
        ImageView imgbgrView = new ImageView(imagebgr);
        this.setBackground(new Background(new BackgroundImage(imagebgr,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,  BackgroundSize.DEFAULT)));

        spaces = new Text("                                                 ");
        spaceBox.getChildren().addAll(spaces);

        cardPlayers = new Text("The Player's Card:");
        cardPlayers.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 15));
        playerBox.getChildren().addAll(new ImageView(pokerDeck.getCoverCard()),
                                                        cardPlayers);
        cardDealers = new Text("The Dealers's Card:");
        cardDealers.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 15));
        dealerBox.getChildren().addAll(new ImageView(pokerDeck.getCoverCard()),
                                                              cardDealers);

        space = new Text("                                                   ");
        spaceBox.getChildren().addAll(space);

        morespace = new Text("                                               ");
        morespaceBox.getChildren().addAll(morespace);

        backBtn = new Button("Back");
        // This is a lambda, you'll need to use them for buttons.
        // Having provided you with examples for enter and back buttons,
        // you should be able to follow the pattern fairly well.
        // btn.setOnAction(n -> { METHOD CALL OR CODE HERE});
        backBtn.setOnAction(n -> {
                Scene scene = new Scene(new ChooseMenu(),
                    CasinoGamesRunner.WIDTH, CasinoGamesRunner.HEIGHT);
                CasinoGamesRunner.getStage().setScene(scene);
            });
        restartBtn = new Button("Restart");
        restartBtn.setOnAction(n -> restartGame());

        betBtn = new Button("Bet");
        moneybet = new TextField();
        betBtn.setOnAction(n -> startGame());
        currAmount = new Text("Amount: ");
        currAmount.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 35));
        totalMoney = new Text(playerMoney + "");
        totalMoney.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 35));
        // Add buttons to the bottom panel and display
        bottomPanel.setPrefWidth(900);
        bottomPanel.getChildren().addAll(backBtn, restartBtn, betBtn,
                                        moneybet, currAmount, totalMoney);
        this.getChildren().addAll(bottomPanel, spaces, playerBox, spaceBox,
                                                  morespaceBox, dealerBox);
    }
    /**
     * this essentially starts the game and checks to see if
     * the input is actually a number or Not
     * and if the input is not, it alerts the player
     */
    public void startGame() {
        moneybet.setText(moneybet.getText());
        try {
            amountofmoney = Integer.parseInt(moneybet.getText());
            if ((amountofmoney > playerMoney) || (amountofmoney < 0)) {
                Alert bethighAlert = new Alert(AlertType.ERROR);
                bethighAlert.setTitle("Bet Alert");
                bethighAlert.setHeaderText("Cannot place the bet.");
                bethighAlert.setContentText("The bet is impossible to place!");
                bethighAlert.showAndWait();
                betBtn.setDisable(false);
            } else {
                betBtn.setDisable(true);
                deal();
            }
        } catch (NumberFormatException e) {
            Alert invalidEnter = new Alert(AlertType.ERROR);
            invalidEnter.setTitle("Invalid Bet");
            invalidEnter.setHeaderText("Cannot place the bet.");
            invalidEnter.setContentText("The bet placed is invalid!");
            invalidEnter.showAndWait();
            betBtn.setDisable(false);
        }
    }
    /**
     * this essentially deals cards to the two players
     */
    public void deal() {
        Random rd = new Random();
        Suits[] arr = Suits.values();
        playerCard = new PokerCard(rd.nextInt(13) + 1, arr[rd.nextInt(4)]);
        dealerCard = new PokerCard(rd.nextInt(13) + 1, arr[rd.nextInt(4)]);

        ImageView playercardImage = new ImageView(playerCard.getImage());
        playerBox.getChildren().clear();
        playerBox.getChildren().addAll(cardPlayers, playercardImage);

        ImageView dealercardImage = new ImageView(dealerCard.getImage());
        dealerBox.getChildren().clear();
        dealerBox.getChildren().addAll(cardDealers, dealercardImage);

        concludeGame();
    }

    /**
     * compares the two cards of the two players and checks to see who won or
     * lost
     */
    public void concludeGame() {
        int playercardnum = playerCard.getNum();
        int dealercardnum = dealerCard.getNum();
        if (playercardnum != dealercardnum) {
            if (playercardnum > dealercardnum) {
                playerMoney = amountofmoney + playerMoney;
                String moneymoney = String.valueOf(playerMoney);
                totalMoney.setText(moneymoney);
                Alert playerWins = new Alert(AlertType.INFORMATION);
                playerWins.setTitle("Player Wins!");
                playerWins.setHeaderText("The Player has won the game!");
                playerWins.setContentText("You have won: " + amountofmoney);
                playerWins.showAndWait();
            }
            if (playercardnum < dealercardnum) {
                playerMoney = playerMoney - amountofmoney;
                String moneymoney = String.valueOf(playerMoney);
                totalMoney.setText(moneymoney);
                Alert playerWins = new Alert(AlertType.INFORMATION);
                playerWins.setTitle("Dealer Wins!");
                playerWins.setHeaderText("The Dealer has won the game!");
                playerWins.setContentText("You have lost: " + amountofmoney);
                playerWins.showAndWait();
            }
        } else {
            playerMoney = playerMoney;
            String moneymoney = String.valueOf(playerMoney);
            totalMoney.setText(moneymoney);
            Alert playerWins = new Alert(AlertType.INFORMATION);
            playerWins.setTitle("Draw");
            playerWins.setHeaderText("No one Wins or Loses!");
            playerWins.setContentText("It is a draw!");
            playerWins.showAndWait();
        }
    }
    /**
     * if button is clicked, this will essentially start a new game and allows
     */
    public void restartGame() {
        playerBox.getChildren().clear();
        dealerBox.getChildren().clear();
        betBtn.setDisable(false);
        playerBox.getChildren().addAll(cardPlayers,
                                      new ImageView(pokerDeck.getCoverCard()));
        dealerBox.getChildren().addAll(cardDealers,
                                      new ImageView(pokerDeck.getCoverCard()));
    }
}
