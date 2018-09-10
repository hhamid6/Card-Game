package view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import runner.CasinoGamesRunner;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

/**
 *@author hhamid6
 *@version 3.4
 */
public class ChooseMenu extends BorderPane {

    private VBox blackjack = new VBox();
    private VBox war = new VBox();
    private HBox greetings = new HBox();
    private HBox icons = new HBox();

    /**
     * choose menu constructor
     */
    public ChooseMenu() {
        this.setLeft(war);
        BorderPane.setMargin(war, new Insets(10, 10, 10, 10));
        this.setRight(blackjack);
        BorderPane.setMargin(blackjack, new Insets(10, 10, 10, 10));
        this.setBottom(greetings);
        BorderPane.setMargin(greetings, new Insets(10, 10, 10, 10));
        this.setCenter(icons);
        BorderPane.setMargin(icons, new Insets(150, 150, 150, 150));
        Image imagebgr = new Image("file:images/table.jpg");
        ImageView imgbgrView = new ImageView(imagebgr);
        this.setBackground(new Background(new BackgroundImage(imagebgr,
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,  BackgroundSize.DEFAULT)));
        setGreeting();
        setBlackJack();
        setWar();
        setIcon();
    }
    /**
     * Sets up the BlackJack portion of the menu
     */
    private void setBlackJack() {
        blackjack.setPrefSize(200, 400);
        Image img = new Image("file:images/construction.jpg",
                300, 300, false, false);
        ImageView imgView = new ImageView(img);
        Text text = new Text("Blackjack");
        text.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 25));
        Button btn = new Button("Enter");
        btn.setOnAction(n -> {
                Scene scene = new Scene(new BlackjackTable(),
                    CasinoGamesRunner.WIDTH, CasinoGamesRunner.HEIGHT);
                CasinoGamesRunner.getStage().setScene(scene);
            });
        blackjack.getChildren().addAll(imgView, text, btn);
    }

    /**
     * Sets up the War portion of the menu
     */
    private void setWar() {
        war.setPrefSize(200, 400);
        Image img = new Image("file:images/cards.jpg",
                300, 300, false, false);
        ImageView imgView = new ImageView(img);
        Text text = new Text("War");
        text.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 25));
        Button btn = new Button("Enter");
        btn.setOnAction(n -> {
                Scene scene = new Scene(new WarTable(),
                    CasinoGamesRunner.WIDTH, CasinoGamesRunner.HEIGHT);
                CasinoGamesRunner.getStage().setScene(scene);
            });
        war.getChildren().addAll(imgView, text, btn);
    }
    /**
     * Sets up the greeting portion
     */
    private void setGreeting() {
        greetings.setPrefSize(200, 400);
        Text greeting = new Text(20, 20, "Welcome to Dr. McDaniel's Casino!");
        greeting.setFont(Font.font("Courier", FontWeight.BOLD,
                                                FontPosture.ITALIC, 55));
        greetings.getChildren().addAll(greeting);
    }
    /**
     * Sets up the logo in center
     */
    private void setIcon() {
        blackjack.setPrefSize(100, 200);
        Image imgsf = new Image("file:images/casino.jpg",
                100, 100, false, false);
        ImageView imgViews = new ImageView(imgsf);
        Text textsaf = new Text("Blackjack");
        icons.getChildren().addAll(imgViews);
    }
}
