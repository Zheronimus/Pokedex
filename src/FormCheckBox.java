import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class FormCheckBox {

    private VBox formCheckBox;
    private CheckBox checkBox;



    public FormCheckBox(String imgFilePath, String id) {

        formCheckBox = new VBox();
        checkBox = new CheckBox();

        formCheckBox.setId(id);

        VBox.setMargin(checkBox, new Insets(0, 0, 0, 5));

        formCheckBox.getChildren().addAll(getSymbol(imgFilePath), checkBox);
        formCheckBox.setAlignment(Pos.CENTER);
        formCheckBox.setSpacing(8);
    }



    public VBox getFormCheckBox() {
        return formCheckBox;
    }



    private ImageView getSymbol(String imgFilePath) {

        try {
            FileInputStream imgFile = new FileInputStream("Resources/" + imgFilePath + ".png");
            ImageView symbol = new ImageView(new Image(imgFile));

            symbol.setFitHeight(36);
            symbol.setFitWidth(36);
            symbol.setPreserveRatio(true);
            symbol.setSmooth(true);

            try {
                imgFile.close();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

            return symbol;
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }



    public void addMegaListener(Pokemon pokemon, Body body) {

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {

            Pokemon newPokemon;

            if(newValue) {
                if(formCheckBox.getId().equals("megaBoxY")) {
                    newPokemon = Dex.getMegaByIndex(Dex.getMegaIndex(pokemon) + 1);
                } else {
                    newPokemon = Dex.getMegaByIndex(Dex.getMegaIndex(pokemon));
                }

                pokemon.setMega(true);
            } else {
                pokemon.setMega(false);
                newPokemon = Dex.getPokemonByEntry(pokemon.getEntryNum());
            }

            pokemon.setName(newPokemon.getName());
            pokemon.setTypeOne(newPokemon.getTypeOne());
            pokemon.setTypeTwo(newPokemon.getTypeTwo());
            pokemon.setBaseHp(newPokemon.getBaseHp());
            pokemon.setBaseAtt(newPokemon.getBaseAtt());
            pokemon.setBaseDef(newPokemon.getBaseDef());
            pokemon.setBaseSpA(newPokemon.getBaseSpA());
            pokemon.setBaseSpD(newPokemon.getBaseSpD());
            pokemon.setBaseSpeed(newPokemon.getBaseSpeed());

            body.setNameLabel(pokemon.getName());
            body.setSprite(pokemon);
            body.setTyping(pokemon.getTypeOne(), pokemon.getTypeTwo());
            body.setCheckboxState();
            body.hideStats();
        });
    }



    public void addGigantamaxListener(Pokemon pokemon, Body body) {

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {

            if(newValue) {
                pokemon.setName("Gigantamax " + pokemon.getName());
                pokemon.setGigantamax(true);
            } else {
                pokemon.setGigantamax(false);
                pokemon.setName(pokemon.getName().replace("Gigantamax", "").trim());
            }

            body.setNameLabel(pokemon.getName());
            body.setSprite(pokemon);
            body.setCheckboxState();
        });
    }
}