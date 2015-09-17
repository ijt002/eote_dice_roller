package gui;

import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dice.abstractDice.AbstractEotEDie;
import dice.enums.Symbol;
import dicePool.DicePool;

public class DiceEventHandlers {

	public static EventHandler<Event> getMinusAction(final NumberTextField inputField) {
		EventHandler<Event> actionEvent = new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (event instanceof KeyEvent) {
					KeyEvent keyEvent = (KeyEvent) event;
					if (keyEvent.getCode() != KeyCode.ENTER) {
						return;
					}
				}
				Integer integer = inputField.getTextValue();
				if (integer > 0) {
					integer--;
				}
				inputField.setText(integer);
			}
		};
		return actionEvent;
	}

	public static EventHandler<Event> getPlusAction(final NumberTextField inputField) {
		EventHandler<Event> actionEvent = new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (event instanceof KeyEvent) {
					KeyEvent keyEvent = (KeyEvent) event;
					if (keyEvent.getCode() != KeyCode.ENTER) {
						return;
					}
				}
				Integer integer = inputField.getTextValue();
				integer++;
				inputField.setText(integer);
			}
		};
		return actionEvent;
	}

	public static EventHandler<ActionEvent> getUpgradeAction(final NumberTextField d8Input, final NumberTextField d12Input) {
		EventHandler<ActionEvent> upgradeActionEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Integer d8Int = d8Input.getTextValue();
				Integer d12Int = d12Input.getTextValue();
				if (d8Int > 0) {
					d8Int--;
					d12Int++;
				} else {
					d8Int++;
				}
				d8Input.setText(d8Int);
				d12Input.setText(d12Int);
			}
		};
		return upgradeActionEvent;
	}

	public static EventHandler<ActionEvent> getDowngradeAction(final NumberTextField d8Input, final NumberTextField d12Input) {
		EventHandler<ActionEvent> downgradeActionEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Integer d8Current = d8Input.getTextValue();
				Integer d12Current = d12Input.getTextValue();
				if (d12Current > 0) {
					d12Current--;
					d8Current++;
				}
				d8Input.setText(d8Current);
				d12Input.setText(d12Current);
			}
		};
		return downgradeActionEvent;
	}

	public static EventHandler<ActionEvent> getResultsEvent(final GridPane grid, final NumberTextField abilityInput,
															final NumberTextField proficiencyInput, final NumberTextField boostInput,
															final NumberTextField difficultyInput, final NumberTextField challengtInput,
															final NumberTextField setbackInput, final NumberTextField forceInput) {
		EventHandler<ActionEvent> resultsEvent = new EventHandler<ActionEvent>() {
			Integer firstGridSlot;
			Integer lastGridSlot;

			@Override
			public void handle(ActionEvent event) {
				removeOldResults(grid, firstGridSlot, lastGridSlot);
				firstGridSlot = grid.getChildren().size();
				DicePool dicePool = new DicePool(boostInput.getTextValue(), setbackInput.getTextValue(), abilityInput.getTextValue(),
						difficultyInput.getTextValue(), proficiencyInput.getTextValue(), challengtInput.getTextValue(),
						forceInput.getTextValue());

				VBox resultInfoBox = getResultInfo(dicePool);
				grid.add(resultInfoBox, 0, DiceRollerApp.RESULTS_START_ROW, DiceRollerApp.FULL_SPAN, 1);
				formatAndDisplayResults(dicePool, grid, DiceRollerApp.RESULTS_START_ROW + 2);
				lastGridSlot = grid.getChildren().size();

			}

			private VBox getResultInfo(DicePool dicePool) {
				VBox resultVBox = new VBox(10);
				HBox resultHBox1 = new HBox(10);
				HBox resultHBox2 = new HBox(60);
				HBox resultHBox3 = new HBox(60);
				Text diceResult = new Text(dicePool.isSuccessful() ? "SUCCESS" : "FAILURE");
				resultHBox1.getChildren().addAll(diceResult);
				resultHBox1.setAlignment(Pos.CENTER);
				resultVBox.getChildren().add(resultHBox1);
				Text successFailureText = new Text("Net Success: " + dicePool.getDegreeOfSuccess());
				Text advantageThreatText = new Text("Net Advantage: " + dicePool.getDegreeOfAdvantage());
				Text triumphText = new Text("Triumph: " + dicePool.getTriumphs());
				Text despairText = new Text("Despair: " + dicePool.getDespairs());
				resultHBox2.getChildren().addAll(successFailureText, advantageThreatText, triumphText, despairText);
				resultHBox2.setAlignment(Pos.CENTER);
				resultVBox.getChildren().add(resultHBox2);
				if (dicePool.getLightForcePoints() > 0 || dicePool.getDarkForcePoints() > 0) {
					Text lightForceText = new Text("Light Force Points: " + dicePool.getLightForcePoints());
					Text darkForceText = new Text("Dark Force Points: " + dicePool.getDarkForcePoints());
					resultHBox3.getChildren().addAll(lightForceText, darkForceText);
					resultHBox3.setAlignment(Pos.CENTER);
					resultVBox.getChildren().add(resultHBox3);
				}
				resultVBox.setAlignment(Pos.TOP_CENTER);
				resultVBox.setMinHeight(50);
				return resultVBox;
			}

			private void removeOldResults(GridPane grid1, Integer firstGridSlot1, Integer lastGridSlot1) {
				if (firstGridSlot1 != null && lastGridSlot1 != null) {
					grid1.getChildren().remove(firstGridSlot1, lastGridSlot1);
				}
			}

			private void formatAndDisplayResults(DicePool dicePool, GridPane grid1, Integer startRow) {

				Integer columnIndex = 0;
				columnIndex = displayEotEDieResults(dicePool.getAbilityDice(), grid1, columnIndex, startRow);
				columnIndex = displayEotEDieResults(dicePool.getProficiencyDice(), grid1, columnIndex, startRow);
				columnIndex = displayEotEDieResults(dicePool.getBoostDice(), grid1, columnIndex, startRow);
				columnIndex++;
				columnIndex = displayEotEDieResults(dicePool.getDifficultyDice(), grid1, columnIndex, startRow);
				columnIndex = displayEotEDieResults(dicePool.getChallengeDice(), grid1, columnIndex, startRow);
				columnIndex = displayEotEDieResults(dicePool.getSetbackDice(), grid1, columnIndex, startRow);
				columnIndex++;
				columnIndex = displayEotEDieResults(dicePool.getForceDice(), grid1, columnIndex, startRow);
			}

			// private <T extends AbstractStandardDie> Integer displayStandardDieResults(Set<T> diceList, GridPane grid, Integer columnIndex,
			// Integer startRow) {
			// Integer rowIndex = startRow;
			//
			// for (T die : diceList) {
			//
			// Text dieText = new Text(die.getResult().toString());
			// dieText.getStyleClass().add("die-symbol");
			// dieText.setFill(die.getTextColor());
			// Polygon dieShape = die.getShape();
			//
			// StackPane dieStackPane = new StackPane(dieShape, dieText);
			// dieStackPane.setAlignment(Pos.CENTER);
			// grid.add(dieStackPane, columnIndex, rowIndex++);
			// }
			// return ++columnIndex;
			// }

			private <T extends AbstractEotEDie> Integer displayEotEDieResults(Set<T> diceList, GridPane resultGrid, Integer columnIndex,
																				Integer startRow) {
				Integer rowIndex = startRow;

				for (T die : diceList) {

					StringBuilder symbolString = new StringBuilder("");
					for (Symbol symbol : die.getResult().getSymbols()) {
						if (symbolString.length() > 0) {
							symbolString.append('\n');
						}
						symbolString.append(symbol.getCharacter());
					}

					Text symbolText = new Text(symbolString.toString());
					symbolText.getStyleClass().add("die-symbol");
					if (!die.isPositive()) {
						symbolText.setFill(Color.WHITE);
					}
					Polygon dieShape = die.getShape();

					StackPane dieStackPane = new StackPane(dieShape, symbolText);
					dieStackPane.setAlignment(Pos.CENTER);
					resultGrid.add(dieStackPane, columnIndex, rowIndex++);
				}
				return columnIndex + 1;
			}
		};

		return resultsEvent;
	}

	public static EventHandler<ActionEvent> getAboutAction() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				final Stage dialogStage = new Stage();
				dialogStage.setTitle("About");
				dialogStage.initModality(Modality.WINDOW_MODAL);

				Label aboutText = new Label(
						"This dice roller is intended for use with Fantasy Flight's Edge of the Empire RPG."
								+ "\n\nPlease enjoy responsibly, I accept no liability for any damages that come from using this app."
								+ "\n\nThe main text font is Arkitech Medium by Ivan Philipov and can be found at http://www.dafont.com/arkitech.font."
								+ "\n\nThe title font is Han Solo by  Dan Zadorozny and can be found at http://www.dafont.com/han-solo.font."
								+ "\n\nThe dice font is EotE Symbols by Aazlain posted on the Fantasy Flight community forums."
								+ "\n\nThe background is Honey I'm Subtle by Alex M. Balling and can be found at subtlepatterns.com/honey-im-subtle."
								+ "\n\nThanks to all mentioned above. No copyright infringement intended. "
								+ "\n\nThis dice roller can be redistributed freely, but should not be modified or sold without prior permission from me.");
				aboutText.setWrapText(true);
				aboutText.getStyleClass().add("about-text");
				Button closeBtn = new Button("Close");

				closeBtn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						dialogStage.close();

					}
				});

				HBox hBox = new HBox();
				hBox.setAlignment(Pos.BASELINE_RIGHT);
				hBox.setPadding(new Insets(10, 10, 0, 10));
				hBox.getChildren().addAll(closeBtn);

				VBox vBox = new VBox();
				vBox.setPadding(new Insets(10));
				vBox.getChildren().addAll(aboutText, hBox);
				Scene aboutScene = new Scene(vBox, 420, 450);
				aboutScene.getStylesheets().add(DiceRollerApp.class.getResource("DiceRoller.css").toExternalForm());
				dialogStage.setScene(aboutScene);
				dialogStage.show();
			}
		};
	}

	public static EventHandler<ActionEvent> getExitAction(Stage primaryStage) {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					primaryStage.close();
				} catch (Exception e) {
					DiceRollerApp.LOG.severe("Failed to close window:" + e.getMessage());
				}
			}
		};
	}

	public static EventHandler<ActionEvent> getNewAction() {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					DiceRollerApp diceRollerApp = new DiceRollerApp();
					diceRollerApp.start(new Stage());
				} catch (Exception e) {
					DiceRollerApp.LOG.severe("Failed to open new window:" + e.getMessage());
				}

			}
		};
	}
}
