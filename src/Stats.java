import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Stats {

    private VBox stats;


    public Stats(Pokemon pokemon) {

        stats = new VBox(13);

        final int MAX_STAT = 255;
        final Label STATS_HEADER = new Label("Base Stats");
        final String[] STAT_DESC = {"Hp: ", "Attack: ", "Defense: ", "SpAttack: ", "SpDefense: ", "Speed: ", "Total: "};
        int[] statNums = {pokemon.getBaseHp(), pokemon.getBaseAtt(), pokemon.getBaseDef(), pokemon.getBaseSpA(), pokemon.getBaseSpD(), pokemon.getBaseSpeed(), pokemon.getBaseTotal()};
        Label[] statLabels = new Label[7];

        STATS_HEADER.getStyleClass().add("largeLabel");

        stats.getChildren().add(STATS_HEADER);

        for(int i = 0; i < statLabels.length; i++) {
            statLabels[i] = new Label();
            statLabels[i].setText(STAT_DESC[i] + statNums[i]);
            statLabels[i].getStyleClass().add("smallLabel");

            if(i < (statLabels.length - 1)) {
                HBox statBox = new HBox();
                HBox statLineBox = new HBox();
                StatLine statLine = new StatLine();
                StatLine fillLine = new StatLine();

                statBox.setAlignment(Pos.TOP_CENTER);
                statLineBox.setAlignment(Pos.TOP_CENTER);

                statLine.setColor(Color.YELLOWGREEN);
                statLine.setLength(statNums[i]);

                fillLine.setColor(Color.GRAY);
                fillLine.setLength(MAX_STAT - statNums[i]);

                statBox.getChildren().addAll(statLabels[i]);
                statLineBox.getChildren().addAll(statLine, fillLine);

                if(statNums[i] == MAX_STAT) {
                    statLineBox.getChildren().remove(fillLine);
                }

                stats.getChildren().addAll(statBox, statLineBox);
            }

            else {
                stats.getChildren().add(statLabels[i]);
            }
        }

        stats.setPrefHeight(400);
        stats.setAlignment(Pos.TOP_CENTER);
    }


    public VBox getStats() {
        return stats;
    }


    public void setStats(VBox stats) {
        this.stats = stats;
    }
}