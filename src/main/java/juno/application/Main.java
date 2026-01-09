package juno.application;
	
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;

@SuppressWarnings("unused")
public class Main extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 800, 800);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Uno");
			
			primaryStage.setMaxWidth(Screen.getPrimary().getVisualBounds().getWidth());
			primaryStage.setMaxHeight(Screen.getPrimary().getVisualBounds().getHeight());
			
			Image icon = new Image("assets/uno.png");
			primaryStage.getIcons().add(icon);
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
