package view;
import java.util.ArrayList;
import controller.PokerController;
import javafx.scene.Scene;
import model.PokerCard;
import model.PokerDeck;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import runner.CasinoGamesRunner;
import javafx.scene.control.TextField;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

/**
 * @author hhamid6
 * @version 3.4
 */
public class BlackjackTable extends FlowPane {

    private ArrayList<PokerCard> playerCard = new ArrayList<>();
    private ArrayList<PokerCard> hostCard = new ArrayList<>();
    private ArrayList<PokerCard> cardDealt = new ArrayList<>();
    private PokerDeck pokerDeck;
    private HBox playerBox;
    private HBox dealerBox;
    private Text status;
    private PokerController controller;
    private int playerMoney = 1000;
    private Button backBtn;
    private Button restartBtn;
    private Button betBtn;
    private Button hitBtn;
    private Button stayBtn;
    private TextField moneybet;
    private Text currAmount;
    private Text totalMoney;
    private int amountofmoney;
    private Text cardPlayers;
    private Text cardDealers;

    /**
     * Extra credit BlackjackTable constructor
     * Complete the constructor for BlackjackTable.
     * Should initialize screen.
     */
    public BlackjackTable() {
        PokerDeck.initialize();
        HBox bottomPanel = new HBox(30);

        playerBox = new HBox();
        dealerBox = new HBox();

        backBtn = new Button("Back");

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
        totalMoney = new Text(playerMoney + "");

        hitBtn = new Button("Hit");

        hitBtn.setOnAction(n -> { });

        stayBtn = new Button("Stay");

        stayBtn.setOnAction(n -> { });

        // Add buttons to the bottom panel and display
        bottomPanel.setPrefWidth(900);
        bottomPanel.getChildren().addAll(backBtn, restartBtn, betBtn,
                        moneybet, currAmount, totalMoney, hitBtn, stayBtn);
        this.getChildren().addAll(bottomPanel);
    }
    /**
     *  Starts a game of Blackjack, extra credit
     *  Implement logic to begin a game of Blackjack
     */
    public void startGame() {
        moneybet.setText(moneybet.getText());
        try {
            amountofmoney = Integer.parseInt(moneybet.getText());
            if (amountofmoney > playerMoney) {
                Alert bethighAlert = new Alert(AlertType.ERROR);
                bethighAlert.setTitle("Bet Alert");
                bethighAlert.setHeaderText("Cannot place the bet.");
                bethighAlert.setContentText("The bet is too high!");
                bethighAlert.showAndWait();
                betBtn.setDisable(false);
            } else {
                Random targetnumber = new Random();
                int numbertarget = targetnumber.nextInt(1);
                deal(numbertarget);
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
     * deals the card to the person based on the input.
     * @param target deals to player if target is 0
     * deals to host if target is 1, extra credit
     * Implement logic to deal to a particular target
     */
    public void deal(int target) {
      /*
        if (target == 0) {
            playerCard = PokerController.dealCard(playerCard);
        } else {
            cardsofDealer = PokerController.dealCard(hostCard);
        }
        */
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


    /**
     * When Dealer is finished with drawing card,
     * determining the result of the game, extra credit
     * Implement logic to end a game of Blackjack
     */
    public void concludeGame() {

    }

    /**
     * getter for playerCard
     * @return the ArrayList that keeps track of player's hand
     */
    public ArrayList<PokerCard> getPlayerCard() {
        return playerCard;
    }
    /**
     * getter for hostCard
     * @return the ArrayList that keeps track of dealer's hand
     */
    public ArrayList<PokerCard> getHostCard() {
        return hostCard;
    }
    /**
     * getter for carDealt
     * @return the ArrayList that keeps track of card that is dealt
     */
    public ArrayList<PokerCard> getCardDealt() {
        return cardDealt;
    }
    /**
     * getter for playerBox
     * @return the HBox that holds info for player
     */
    public HBox getPlayerBox() {
        return playerBox;
    }
    /**
     * getter for hostBox
     * @return the HBox that holds info for host
     */
    public HBox getHostBox() {
        return dealerBox;
    }
    /**
     * getter for status
     * @return the Text that is showing the status of the game
     */
    public Text getStatusText() {
        return status;
    }

}
