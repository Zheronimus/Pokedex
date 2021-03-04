import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.File;

public class PokedexDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Pokedex");
		primaryStage.getIcons().add(new Image(new File("Resources/Pokeball.png").toURI().toURL().toString(), false));
		primaryStage.setResizable(false);

		Pokemon pokemon = new Dex().getPokemonByEntry(1);

		Body body = new Body(pokemon);
		Header header = new Header(pokemon, body);
		Footer footer = new Footer(pokemon, body, header);
		BorderPane bp = new BorderPane(body.getBody(), header.getHeader(), null, footer.getFooter(), null);
		
		Scene scene = new Scene(bp, 500, 750);
		
		scene.getStylesheets().add("Stylesheet.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}