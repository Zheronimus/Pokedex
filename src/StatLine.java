import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class StatLine extends Line {
	
	private double fullLine;
	private double partialLine;


	public StatLine() {
		
		this.setStrokeWidth(8);
		this.setStrokeLineCap(StrokeLineCap.ROUND);
	}

	
	public void setLength(int statValue) {
		
		this.fullLine = statValue * 0.83;
		this.partialLine = statValue * 0.8;
		
		this.setStartX(0);
		
		if(statValue == 255) {
			this.setEndX(this.fullLine);
		}

		else {
			this.setEndX(this.partialLine);
		}
	}


	public void setColor(Color color) {
		this.setStroke(color);
	}
}

