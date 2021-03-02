import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Footer {

	private final VBox footer;


	public Footer(Pokemon pokemon, Body body, Header header, ArrayList<Pokemon> nationalDex, ArrayList<Pokemon> megaDex) {
		
		TextField searchBar = new TextField();
		Button searchButton = new Button("\uD83D\uDD0E");
		footer = new VBox();

		searchButton.setOnMouseClicked(clickEvent -> {

			String input = searchBar.getText().trim();

			if((!input.isEmpty()) && (clickEvent.getButton() == MouseButton.PRIMARY)) {

				Pokemon newPokemon;

				if((isNumeric(input)) && (Integer.parseInt(input) > 0) && (Integer.parseInt(input) != pokemon.getEntryNum()) && (Integer.parseInt(input) <= nationalDex.size())) {
					newPokemon = Dex.getPokemonByEntry(Integer.parseInt(input));
				}

				else {
					newPokemon = Dex.getPokemonByName(input);
				}

				if(newPokemon != null) {
					pokemon.setName(newPokemon.getName());
					pokemon.setEntryNum(newPokemon.getEntryNum());
					pokemon.setTypeOne(newPokemon.getTypeOne());
					pokemon.setTypeTwo(newPokemon.getTypeTwo());
					pokemon.setBaseHp(newPokemon.getBaseHp());
					pokemon.setBaseAtt(newPokemon.getBaseAtt());
					pokemon.setBaseDef(newPokemon.getBaseDef());
					pokemon.setBaseSpA(newPokemon.getBaseSpA());
					pokemon.setBaseSpD(newPokemon.getBaseSpD());
					pokemon.setBaseSpeed(newPokemon.getBaseSpeed());

					if(pokemon.hasMega(megaDex)) {
						if(pokemon.hasSecondMega(megaDex)) {
							body.addMegaBox(pokemon, "MegaSymbolX");
							body.addMegaBox(pokemon, "MegaSymbolY");
						}

						else {
							body.addMegaBox(pokemon, "MegaSymbol");
						}
					}

					else {
						body.removeMegaBox();
					}

					if(pokemon.hasGigantamax()) {
						body.addGigantamaxBox(pokemon);
					}

					else {
						if(Dex.getPokemonByEntry(pokemon.getEntryNum() - 1).hasGigantamax()) {
							body.removeGigantamaxBox();
						}
					}

					body.setNameLabel(pokemon.getName());
					body.setEntryNumLabel(pokemon.getEntryNum());
					body.setSprite(pokemon);
					body.setTyping(pokemon.getTypeOne(), pokemon.getTypeTwo());
					body.setCheckboxState();
					header.setSprite(pokemon.getEntryNum(), nationalDex.size());
					header.setEntryNumLabel(pokemon.getEntryNum(), nationalDex.size());
				}
			}

			searchBar.clear();
		});

		searchBar.setMaxWidth(175);

		searchButton.setMaxWidth(75);
		searchButton.setStyle("-fx-font-size: 15px;");

		footer.setAlignment(Pos.CENTER);
		footer.getStyleClass().add("footer");

		VBox.setMargin(searchBar, new Insets(20, 0, 0, 0));
		VBox.setMargin(searchButton, new Insets(20, 0, 20, 0));
        
		footer.getChildren().addAll(searchBar, searchButton);
	}


	public VBox getFooter() {
		return footer;
	}

	
	private boolean isNumeric(String text) {

		try {
			Integer.parseInt(text);
			return true;
		}

		catch(NumberFormatException e) {
			return false;
		}
	}
}