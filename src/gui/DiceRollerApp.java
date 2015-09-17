package gui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DiceRollerApp extends Application {

	private static final int APP_HEIGHT = 700;

	private static final int APP_WIDTH = 870;

	protected static final Logger LOG = Logger.getLogger(DiceRollerApp.class.getName());

	protected static final int FULL_SPAN = 9;

	protected static final Integer RESULTS_START_ROW = 8;

	public static void main(String[] args) {

		try {
			// LOG.addHandler(new FileHandler("EotE-Dice.log"));
			LOG.setLevel(Level.SEVERE);
			launch(args);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.toString());
		}
	}

	@Override
	public void init() throws Exception {
		super.init();
		Font.loadFont(DiceRollerApp.class.getResourceAsStream("/resources/fonts/EotE_Symbols.otf"), 20);
		Font.loadFont(DiceRollerApp.class.getResourceAsStream("/resources/fonts/Arkitech Medium.ttf"), 10);
		Font.loadFont(DiceRollerApp.class.getResourceAsStream("/resources/fonts/hansolov3.ttf"), 10);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Edge of the Empire Dice Roller");
		final GridPane grid = new GridPane();
		grid.getStyleClass().add("grid");

		Text sceneTitle = new Text("Star Wars: edge of the empire");
		Text sceneSubtitle = new Text("dice roller");
		VBox titleBox = new VBox();
		titleBox.setId("scene-title");
		titleBox.setAlignment(Pos.CENTER);
		titleBox.getChildren().addAll(sceneTitle, sceneSubtitle);
		grid.add(titleBox, 0, 0, FULL_SPAN, 1);
		addSmallGap(grid, 0, 1, FULL_SPAN, 1);

		final NumberTextField abilityInput = createDiceInput(grid, "Ability", 0);
		final NumberTextField proficiencyInput = createDiceInput(grid, "Proficiency", 1);
		final NumberTextField boostInput = createDiceInput(grid, "Boost", 2);
		addSmallGap(grid, 3, 1, 1, 4);

		final NumberTextField difficultyInput = createDiceInput(grid, "Difficulty", 4);
		final NumberTextField challengtInput = createDiceInput(grid, "Challenge", 5);
		final NumberTextField setbackInput = createDiceInput(grid, "Setback", 6);
		addSmallGap(grid, 7, 1, 1, 4);

		final NumberTextField forceInput = createDiceInput(grid, "Force", 8);
		addSmallGap(grid, 9, 1, 1, 4);

		addPlusAndMinusButtons(grid, abilityInput, 0);
		addPlusAndMinusButtons(grid, proficiencyInput, 1);
		addPlusAndMinusButtons(grid, boostInput, 2);

		addPlusAndMinusButtons(grid, difficultyInput, 4);
		addPlusAndMinusButtons(grid, challengtInput, 5);
		addPlusAndMinusButtons(grid, setbackInput, 6);

		addPlusAndMinusButtons(grid, forceInput, 8);

		createUpgradeDowngradeButtons(grid, abilityInput, proficiencyInput, 0, 5, 3, 1);
		createUpgradeDowngradeButtons(grid, difficultyInput, challengtInput, 4, 5, 3, 1);

		addRollDiceButton(grid, abilityInput, proficiencyInput, boostInput, difficultyInput, challengtInput, setbackInput, forceInput);

		MenuBar menuBar = createMenuBar(primaryStage);

		ScrollPane scrollpane = createScrollPane(grid);

		VBox root = new VBox(menuBar, scrollpane);
		root.getStyleClass().add("master-vbox");

		Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);

		scene.getStylesheets().add(DiceRollerApp.class.getResource("DiceRoller.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setMinWidth(APP_WIDTH);
		primaryStage.setMaxWidth(APP_WIDTH);
		primaryStage.show();

	}

	public ScrollPane createScrollPane(final GridPane grid) {
		ScrollPane scrollpane = new ScrollPane();
		scrollpane.setContent(grid);
		scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollpane.setPrefSize(APP_WIDTH, APP_HEIGHT);
		return scrollpane;
	}

	public MenuBar createMenuBar(Stage primaryStage) {
		MenuBar menuBar = new MenuBar();

		Menu menu = new Menu("Menu");

		MenuItem menuNew = new MenuItem("New");
		menuNew.setOnAction(DiceEventHandlers.getNewAction());
		MenuItem menuExit = new MenuItem("Exit");
		menuExit.setOnAction(DiceEventHandlers.getExitAction(primaryStage));
		MenuItem menuAbout = new MenuItem("About");
		menuAbout.setOnAction(DiceEventHandlers.getAboutAction());

		menu.getItems().addAll(menuNew, menuExit, menuAbout);

		menuBar.getMenus().addAll(menu);
		return menuBar;
	}

	public void addPlusAndMinusButtons(final GridPane grid, final NumberTextField input, Integer column) {
		HBox hbox = new HBox(10);
		Button minusButton = createMinusButton(input);
		hbox.getChildren().add(minusButton);
		Button plusButton = createPlusButton(input);
		hbox.getChildren().add(plusButton);
		grid.add(hbox, column, 4);
	}

	private void addRollDiceButton(final GridPane grid, final NumberTextField abilityInput, final NumberTextField proficiencyInput,
									final NumberTextField boostInput, final NumberTextField difficultyInput,
									final NumberTextField challengtInput, final NumberTextField setbackInput,
									final NumberTextField forceInput) {
		Button rollButton = new Button("Roll Dice Pool");
		rollButton.setId("roll-button");
		rollButton.setOnAction(DiceEventHandlers.getResultsEvent(	grid, abilityInput, proficiencyInput, boostInput, difficultyInput,
																	challengtInput, setbackInput, forceInput));
		HBox rollHBox = new HBox();
		rollHBox.setAlignment(Pos.BOTTOM_CENTER);
		rollHBox.getChildren().add(rollButton);
		grid.add(rollHBox, 0, 7, FULL_SPAN, 1);
	}

	private void addSmallGap(GridPane grid, Integer columnIndex, Integer rowIndex, Integer colspan, Integer rowspan) {
		Region smallGap = new Region();
		smallGap.getStyleClass().add("small-gap");
		grid.add(smallGap, columnIndex, rowIndex, colspan, rowspan);
	}

	private void createUpgradeDowngradeButtons(GridPane grid, final NumberTextField d8Input, final NumberTextField d12Input,
												Integer columnIndex, Integer rowIndex, Integer colspan, Integer rowspan) {
		Button upgradeButton = new Button("Upgrade");
		upgradeButton.getStyleClass().add("upgrade-downgrade-button");
		upgradeButton.setOnAction(DiceEventHandlers.getUpgradeAction(d8Input, d12Input));
		Button downgradeButton = new Button("Downgrade");
		downgradeButton.getStyleClass().add("upgrade-downgrade-button");
		downgradeButton.setOnAction(DiceEventHandlers.getDowngradeAction(d8Input, d12Input));

		HBox upgradeDowngradeHBox = new HBox(10);
		upgradeDowngradeHBox.getChildren().add(downgradeButton);
		upgradeDowngradeHBox.getChildren().add(upgradeButton);
		grid.add(upgradeDowngradeHBox, columnIndex, rowIndex, colspan, rowspan);
	}

	private NumberTextField createDiceInput(GridPane grid, String textString, Integer column) {
		Text text = new Text(textString);
		final NumberTextField input = new NumberTextField();
		input.setText(0);

		grid.add(text, column, 2);
		grid.add(input, column, 3);

		return input;
	}

	private Button createPlusButton(final NumberTextField inputField) {
		Button plusButton = new Button("+");
		plusButton.getStyleClass().add("plus-minus-button");
		plusButton.setOnMouseClicked(DiceEventHandlers.getPlusAction(inputField));
		plusButton.setOnKeyPressed(DiceEventHandlers.getPlusAction(inputField));
		return plusButton;
	}

	private Button createMinusButton(final NumberTextField inputField) {
		Button minusButton = new Button("-");
		minusButton.getStyleClass().add("plus-minus-button");
		minusButton.setOnMouseClicked(DiceEventHandlers.getMinusAction(inputField));
		minusButton.setOnKeyPressed(DiceEventHandlers.getMinusAction(inputField));
		return minusButton;
	}

}
