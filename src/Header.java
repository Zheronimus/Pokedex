import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Header {

    private HBox header;
    private Button prevButton, nextButton;
    private HBox headerContent;


    public Header(Pokemon pokemon, Body body, final int DEX_SIZE, ArrayList<Pokemon> megaDex) {

        header = new HBox();
        prevButton = new Button();
        nextButton = new Button();
        headerContent = new HBox(150);

        prevButton.setOnMouseClicked(clickEvent -> {
            if(clickEvent.getButton() == MouseButton.PRIMARY) {
                if(pokemon.getEntryNum() > 1) {
                    Pokemon newPokemon;

                    newPokemon = Dex.getPokemonByEntry(pokemon.getEntryNum() - 1);

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

                    if(pokemon.hasGigantamax()) {
                        body.addGigantamaxBox(pokemon);
                    }

                    else {
                        if(Dex.getPokemonByEntry(pokemon.getEntryNum() + 1).hasGigantamax()) {
                            body.removeGigantamaxBox();
                        }
                    }

                    if(pokemon.hasMega(megaDex)) {
                        if(pokemon.hasSecondMega(megaDex.size())) {
                            body.addMegaBox(pokemon, "MegaSymbolX");
                            body.addMegaBox(pokemon, "MegaSymbolY");
                        }

                        else {
                            if(!Dex.getPokemonByEntry(pokemon.getEntryNum() + 1).hasMega(megaDex)) {
                                body.addMegaBox(pokemon, "MegaSymbol");
                            }
                        }
                    }

                    else {
                        body.removeMegaBox();
                    }

                    body.setNameLabel(pokemon.getName());
                    body.setEntryNumLabel(pokemon.getEntryNum());
                    body.setSprite(pokemon);
                    body.setTyping(pokemon.getTypeOne(), pokemon.getTypeTwo());
                    body.hideStats();
                    setSprite(pokemon.getEntryNum(), DEX_SIZE);
                    setEntryNumLabel(pokemon.getEntryNum(), DEX_SIZE);
                }
            }
        });

        nextButton.setOnMouseClicked(clickEvent -> {
            if(clickEvent.getButton() == MouseButton.PRIMARY) {
                if(pokemon.getEntryNum() < DEX_SIZE) {
                    Pokemon newPokemon;

                    newPokemon = Dex.getPokemonByEntry(pokemon.getEntryNum() + 1);

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
                        if(pokemon.hasSecondMega(megaDex.size())) {
                            body.addMegaBox(pokemon, "MegaSymbolX");
                            body.addMegaBox(pokemon, "MegaSymbolY");
                        }

                        else {
                            if(!Dex.getPokemonByEntry(pokemon.getEntryNum() - 1).hasMega(megaDex)) {
                                body.addMegaBox(pokemon, "MegaSymbol");
                            }
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
                    body.hideStats();
                    setSprite(pokemon.getEntryNum(), DEX_SIZE);
                    setEntryNumLabel(pokemon.getEntryNum(), DEX_SIZE);
                }
            }
        });

        headerContent.setAlignment(Pos.CENTER);
        headerContent.getChildren().addAll(new VBox(5, prevButton, new Label(String.format("#%03d", pokemon.getEntryNum()))), new VBox(5, nextButton, new Label(String.format("#%03d", Dex.getPokemonByEntry(pokemon.getEntryNum() + 1).getEntryNum()))));
        prevButton.setGraphic(new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(pokemon.getEntryNum()).getName(), "Bit Sprites"), 0, 0, true, true)));
        nextButton.setGraphic(new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(pokemon.getEntryNum() + 1).getName(), "Bit Sprites"), 0, 0, true, true)));
        prevButton.getStyleClass().add("navButton");
        nextButton.getStyleClass().add("navButton");
        ((VBox) headerContent.getChildren().get(0)).setAlignment(Pos.CENTER);
        ((VBox) headerContent.getChildren().get(1)).setAlignment(Pos.CENTER);
        headerContent.getChildren().get(0).getStyleClass().add("headerEntryLabel");
        headerContent.getChildren().get(1).getStyleClass().add("headerEntryLabel");

        header.setAlignment(Pos.CENTER);
        header.setMinHeight(90);
        header.getStyleClass().add("header");
        header.getChildren().add(headerContent);
    }


    public HBox getHeader() { return header; }


    public void setSprite(int entryNum, final int DEX_SIZE) {

        ImageView nextSprite = null;
        ImageView prevSprite = null;

        if(entryNum > 1 && entryNum < DEX_SIZE) {
            prevSprite = new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(entryNum - 1).getName(), "Bit Sprites"), 0, 0, true, true));
            nextSprite = new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(entryNum + 1).getName(), "Bit Sprites"), 0, 0, true, true));
        }

        else if(entryNum == 1) {
            prevSprite = new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(entryNum).getName(), "Bit Sprites"), 0, 0, true, true));
            nextSprite = new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(entryNum + 1).getName(), "Bit Sprites"), 0, 0, true, true));
        }

        else if(entryNum == DEX_SIZE) {
            prevSprite = new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(entryNum - 1).getName(), "Bit Sprites"), 0, 0, true, true));
            nextSprite = new ImageView(new Image(Sprite.getImgFile(Dex.getPokemonByEntry(entryNum).getName(), "Bit Sprites"), 0, 0, true, true));

        }

        prevButton.setGraphic(prevSprite);
        nextButton.setGraphic(nextSprite);
    }


    public void setEntryNumLabel(int entryNum, final int DEX_SIZE) {

        Label prevEntryNumLabel = (Label)((VBox) headerContent.getChildren().get(0)).getChildren().get(1);
        Label nextEntryNumLabel = (Label)((VBox) headerContent.getChildren().get(1)).getChildren().get(1);

        if(entryNum > 1 && entryNum < DEX_SIZE) {
            prevEntryNumLabel.setText(String.format("#%03d", entryNum - 1));
            nextEntryNumLabel.setText(String.format("#%03d", entryNum + 1));
        }

        else if(entryNum == 1) {
            prevEntryNumLabel.setText(String.format("#%03d", entryNum));
            nextEntryNumLabel.setText(String.format("#%03d", entryNum + 1));
        }

        else if(entryNum == DEX_SIZE) {
            prevEntryNumLabel.setText(String.format("#%03d", entryNum - 1));
            nextEntryNumLabel.setText(String.format("#%03d", entryNum));
        }
    }
}