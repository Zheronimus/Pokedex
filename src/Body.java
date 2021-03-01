import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Body {

    private Label nameLabel, entryLabel, showStatsLabel;
    private ImageView typeOneImg, typeTwoImg, sprite;
    private VBox topContent, bottomContent, bodyContent;
    private HBox checkboxContent, typing;
    private ScrollPane body;



    public Body(Pokemon pokemon) {

        nameLabel = new Label(pokemon.getName());
        entryLabel = new Label(String.format("#%03d", pokemon.getEntryNum()));
        showStatsLabel = new Label("\u2014" + " Show Stats " + "\u2014");
        typeOneImg = new ImageView(new Image(Sprite.getImgFile(pokemon.getTypeOne(), "Type Images"), 100, 25, true, true));
        typeTwoImg = new ImageView(new Image(Sprite.getImgFile(pokemon.getTypeTwo(), "Type Images"), 100, 25, true, true));
        typing = new HBox(30, typeOneImg, typeTwoImg);
        sprite = new ImageView(new Image(Sprite.getImgFile(pokemon.getName(), "Sprites"), 225, 225, true, true));
        checkboxContent = new HBox(25);
        topContent = new VBox(nameLabel, entryLabel, typing, sprite, checkboxContent, showStatsLabel);
        bottomContent = new VBox();
        bodyContent = new VBox(topContent);
        body = new ScrollPane();

        VBox.setVgrow(bodyContent, Priority.NEVER);

        showStatsLabel.setOnMouseClicked(clickEvent -> showStats(pokemon, showStatsLabel));

        topContent.setAlignment(Pos.TOP_CENTER);
        bottomContent.setAlignment(Pos.TOP_CENTER);
        checkboxContent.setAlignment(Pos.TOP_CENTER);
        typing.setAlignment(Pos.TOP_CENTER);

        nameLabel.getStyleClass().add("largeLabel");
        entryLabel.getStyleClass().add("largeLabel");
        showStatsLabel.getStyleClass().add("statsVisibilityLabel");
        body.getStyleClass().add("body");

        VBox.setMargin(nameLabel, new Insets(25, 0, 0, 0));
        VBox.setMargin(entryLabel, new Insets(25, 0, 0, 0));
        VBox.setMargin(typing, new Insets(25, 0, 0, 0));
        VBox.setMargin(sprite, new Insets(25, 0, 0, 0));
        VBox.setMargin(checkboxContent, new Insets(25, 0, 0, 0));
        VBox.setMargin(showStatsLabel, new Insets(80, 0, 0, 0));

        body.setContent(bodyContent);
        body.setPannable(true);
        body.setFitToWidth(true);
        body.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }



    public ScrollPane getBody() {
        return body;
    }



    public void setNameLabel(String name) {
        nameLabel.setText(name);
    }



    public void setEntryNumLabel(int entryNum) {
        entryLabel.setText(String.format("#%03d", entryNum));
    }



    public void setTyping(String typeOne, String typeTwo) {

        typing.getChildren().clear();

        typeOneImg = new ImageView(new Image(Sprite.getImgFile(typeOne, "Type Images"), 100, 25, true, true));

        typing.getChildren().add(typeOneImg);

        if(typeTwo != null) {
            typeTwoImg = new ImageView(new Image(Sprite.getImgFile(typeTwo, "Type Images"), 100, 25, true, true));

            typing.getChildren().add(typeTwoImg);
        }
    }



    public void setSprite(Pokemon pokemon) {

        if(pokemon.isMega()) {
            sprite = new ImageView(new Image(Sprite.getImgFile(pokemon.getName(), "Mega Sprites"), 225, 225, true, true));
        }

        else if(pokemon.isGigantamax()) {
            sprite = new ImageView(new Image(Sprite.getImgFile(pokemon.getName(), "Gigantamax Sprites"), 225, 225, true, true));
        }

        else {
            sprite = new ImageView(new Image(Sprite.getImgFile(pokemon.getName(), "Sprites"), 225, 225, true, true));
        }

        topContent.getChildren().set(3, sprite);

        VBox.setMargin(sprite, new Insets(25, 0, 0, 0));
    }



    public void setCheckboxState() {

        if(checkboxContent.getChildren().size() > 1) {
            VBox formBox;
            CheckBox checkBox;

            for(int i = 0; i < checkboxContent.getChildren().size(); i++) {
                formBox = (VBox)checkboxContent.getChildren().get(i);
                checkBox = (CheckBox)formBox.getChildren().get(1);

                if(checkBox.isSelected()) {
                    for(int j = 0; j < checkboxContent.getChildren().size(); j++) {
                        formBox = (VBox)checkboxContent.getChildren().get(j);
                        checkBox = (CheckBox)formBox.getChildren().get(1);

                        if(!checkBox.isSelected()) {
                            checkBox.setDisable(true);
                        }
                    }

                    break;
                } else {
                    checkBox.setDisable(false);
                }
            }
        }
    }



    public void addMegaBox(Pokemon pokemon, ArrayList<Pokemon> nationalDex, ArrayList<Pokemon> megaDex, String imgFile) {

        FormCheckBox megaBox;

        if(checkboxContent.getChildren().size() > 0) {
            megaBox = new FormCheckBox(imgFile, "megaBoxY");
        } else {
            megaBox = new FormCheckBox(imgFile, "megaBox");
        }

        megaBox.addMegaListener(pokemon, this);
        checkboxContent.getChildren().add(megaBox.getFormCheckBox());

        VBox.setMargin(showStatsLabel, new Insets(19, 0, 0, 0));
    }



    public void removeMegaBox() {

        for(int i = 0; i < checkboxContent.getChildren().size(); i++) {
            VBox formBox = (VBox) checkboxContent.getChildren().get(i);
            CheckBox checkBox = (CheckBox) formBox.getChildren().get(1);

            if(formBox.getId().contains("megaBox")) {
                checkboxContent.getChildren().remove(i);
                i--;
            }

            checkBox.setSelected(false);
        }

        if(checkboxContent.getChildren().isEmpty()) {
            VBox.setMargin(showStatsLabel, new Insets(80, 0, 0, 0));
        }
    }



    public void addGigantamaxBox(Pokemon pokemon) {

        FormCheckBox gigantamaxBox = new FormCheckBox("GigantamaxSymbol", "gigantamaxBox");

        checkboxContent.getChildren().add(gigantamaxBox.getFormCheckBox());
        gigantamaxBox.addGigantamaxListener(pokemon, this);

        VBox.setMargin(showStatsLabel, new Insets(19, 0, 0, 0));
    }



    public void removeGigantamaxBox() {

        for (int i = 0; i < checkboxContent.getChildren().size(); i++) {
            VBox formBox = (VBox) checkboxContent.getChildren().get(i);
            CheckBox checkBox = (CheckBox) formBox.getChildren().get(1);

            if (formBox.getId().equals("gigantamaxBox")) {
                checkboxContent.getChildren().remove(i);
                i--;
            }

            checkBox.setSelected(false);
        }

        if(checkboxContent.getChildren().isEmpty()) {
            VBox.setMargin(showStatsLabel, new Insets(80, 0, 0, 0));
        }
    }



    private void showStats(Pokemon pokemon, Node target) {

        Label hideStatsLabel = new Label("\u2014" + " Hide Stats " + "\u2014");
        Stats stats = new Stats(pokemon);

        hideStatsLabel.setOnMouseClicked(clickEvent -> hideStats());

        hideStatsLabel.getStyleClass().add("statsVisibilityLabel");

        VBox.setMargin(hideStatsLabel, new Insets(0, 0, 18, 0));

        stats.setStats(new Stats(pokemon).getStats());

        VBox.setMargin(stats.getStats(), new Insets(125, 0, 55, 0));

        if(target.onMouseClickedProperty() != null) {
            scrollAnimation();

            topContent.getChildren().remove(showStatsLabel);

            bottomContent.getChildren().addAll(stats.getStats(), hideStatsLabel);

            bodyContent.getChildren().add(bottomContent);
        }
    }



    public void hideStats() {

        if(!bottomContent.getChildren().isEmpty()) {
            bottomContent.getChildren().clear();

            topContent.getChildren().add(showStatsLabel);

            bodyContent.getChildren().remove(bottomContent);
        }
    }



    private void scrollAnimation() {

        final Timeline TIME_LINE = new Timeline();
        final KeyValue KEY_VALUE = new KeyValue(body.vvalueProperty(), 1.0);
        final KeyFrame KEY_FRAME = new KeyFrame(javafx.util.Duration.millis(400), KEY_VALUE);

        TIME_LINE.getKeyFrames().add(KEY_FRAME);
        TIME_LINE.play();
    }
}