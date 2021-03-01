import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

public class Header {

    private HBox header;
    private Button prevButton, nextButton;
    private HBox headerContent;
    private ImageView sprite;



    public Header(Pokemon pokemon, Body body, Dex nationalDex, Dex megaDex) {

        header = new HBox();
        prevButton = new Button();
        nextButton = new Button();
        headerContent = new HBox(150);

        prevButton.setOnMouseClicked(clickEvent -> {
            if(clickEvent.getButton() == MouseButton.PRIMARY) {
                if(pokemon.getEntryNum() > 1) {
                    Pokemon newPokemon;

                    newPokemon = nationalDex.getPokemonByEntry(pokemon.getEntryNum() - 1);

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
                            body.addMegaBox(pokemon, nationalDex, megaDex, "MegaSymbolX");
                            body.addMegaBox(pokemon, nationalDex, megaDex, "MegaSymbolY");
                        } else {
                            if(!nationalDex.getPokemonByEntry(pokemon.getEntryNum() + 1).hasMega(megaDex)) {
                                body.addMegaBox(pokemon, nationalDex, megaDex, "MegaSymbol");
                            }
                        }
                    } else {
                        body.removeMegaBox();
                    }

                    if(pokemon.hasGigantamax()) {
                        body.addGigantamaxBox(pokemon);
                    } else {
                        if(nationalDex.getPokemonByEntry(pokemon.getEntryNum() + 1).hasGigantamax()) {
                            body.removeGigantamaxBox();
                        }
                    }

                    body.setNameLabel(pokemon.getName());
                    body.setEntryNumLabel(pokemon.getEntryNum());
                    body.setSprite(pokemon);
                    body.setTyping(pokemon.getTypeOne(), pokemon.getTypeTwo());
                    body.hideStats();
                    body.setStats(pokemon);
                    setSprite(nationalDex, pokemon.getEntryNum());
                    setEntryNumLabel(pokemon.getEntryNum(), nationalDex.size());
                }
            }
        });

        nextButton.setOnMouseClicked(clickEvent -> {
            if(clickEvent.getButton() == MouseButton.PRIMARY) {
                if(pokemon.getEntryNum() < nationalDex.size()) {
                    Pokemon newPokemon;

                    newPokemon = nationalDex.getPokemonByEntry(pokemon.getEntryNum() + 1);

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
                            body.addMegaBox(pokemon, nationalDex, megaDex, "MegaSymbolX");
                            body.addMegaBox(pokemon, nationalDex, megaDex, "MegaSymbolY");
                        } else {
                            if(!nationalDex.getPokemonByEntry(pokemon.getEntryNum() - 1).hasMega(megaDex)) {
                                body.addMegaBox(pokemon, nationalDex, megaDex, "MegaSymbol");
                            }
                        }
                    } else {
                        body.removeMegaBox();
                    }

                    if(pokemon.hasGigantamax()) {
                        body.addGigantamaxBox(pokemon);
                    } else {
                        if(nationalDex.getPokemonByEntry(pokemon.getEntryNum() - 1).hasGigantamax()) {
                            body.removeGigantamaxBox();
                        }
                    }

                    body.setNameLabel(pokemon.getName());
                    body.setEntryNumLabel(pokemon.getEntryNum());
                    body.setSprite(pokemon);
                    body.setTyping(pokemon.getTypeOne(), pokemon.getTypeTwo());
                    body.hideStats();
                    body.setStats(pokemon);
                    setSprite(nationalDex, pokemon.getEntryNum());
                    setEntryNumLabel(pokemon.getEntryNum(), nationalDex.size());
                }
            }
        });

        headerContent.setAlignment(Pos.CENTER);
        headerContent.getChildren().addAll(new VBox(prevButton, new Label(String.format("#%03d", pokemon.getEntryNum()))), new VBox(nextButton, new Label(String.format("#%03d", nationalDex.getPokemonByEntry(pokemon.getEntryNum() + 1).getEntryNum()))));
        sprite = new ImageView(new Image(Sprite.getImgFile(nationalDex.getPokemonByEntry(pokemon.getEntryNum()).getName(), "Bit Sprites"), 0, 0, true, true));
        prevButton.setGraphic(sprite);
        sprite = new ImageView(new Image(Sprite.getImgFile(nationalDex.getPokemonByEntry(pokemon.getEntryNum() + 1).getName(), "Bit Sprites"), 0, 0, true, true));
        nextButton.setGraphic(sprite);
        prevButton.getStyleClass().add("navButton");
        nextButton.getStyleClass().add("navButton");
        ((VBox) headerContent.getChildren().get(0)).setAlignment(Pos.CENTER);
        ((VBox) headerContent.getChildren().get(1)).setAlignment(Pos.CENTER);
        headerContent.getChildren().get(0).getStyleClass().add("headerEntryLabel");
        headerContent.getChildren().get(1).getStyleClass().add("headerEntryLabel");

        header.setAlignment(Pos.CENTER);
        header.setMinHeight(80);
        header.getStyleClass().add("header");
        header.getChildren().add(headerContent);
    }



    public HBox getHeader() { return header; }



    public void setSprite(Dex nationalDex, int entryNum) {

        ImageView sprite;

        if(entryNum > 1 && entryNum < nationalDex.size()) {
            sprite = new ImageView(new Image(Sprite.getImgFile(nationalDex.getPokemonByEntry(entryNum - 1).getName(), "Bit Sprites"), 0, 0, true, true));
            prevButton.setGraphic(sprite);

            sprite = new ImageView(new Image(Sprite.getImgFile(nationalDex.getPokemonByEntry(entryNum + 1).getName(), "Bit Sprites"), 0, 0, true, true));
            nextButton.setGraphic(sprite);
        }
    }



    public void setEntryNumLabel(int entryNum, final int DEX_SIZE) {

        if(entryNum > 1 && entryNum < DEX_SIZE) {
            ((Label)((VBox)(headerContent.getChildren().get(0))).getChildren().get(1)).setText(String.format("#%03d", entryNum - 1));
            ((Label)((VBox)(headerContent.getChildren().get(1))).getChildren().get(1)).setText(String.format("#%03d", entryNum + 1));
        }
    }
}