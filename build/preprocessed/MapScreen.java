import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MapScreen extends Canvas {
	Buildings[] b;
	int[] result;
	int focusX, focusY, moverX, moverY;
	String title;
	Image[][] im;
	Image ppg;
	int pressX, pressY;
	int pulldown, fontheight, strwidth;
	
	MapScreen(Buildings[] b) throws IOException{
		this.b = b;
		this.focusX = 0;
		this.focusY = 0;
		this.title = "";
		this.im = new Image[2][3];
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < 3; j++){
				im[i][j] = Image.createImage("/img/map-"+i+"-"+j+".png");
			}
		}
		this.ppg = Image.createImage("/img/PPGico.jpg");
		this.pulldown = 0;
		this.fontheight = 0;
		this.strwidth = 0;
	}

	public void paint (Graphics g){
		if (result.length != 0){
			this.fontheight = g.getFont().getHeight();
			this.strwidth = g.getFont().stringWidth(title);
			int fromX = (focusX-(focusX%240))/240;
			int fromY = (focusY-(focusY%320))/320;
			int toX = focusX+getWidth(); toX = (toX-(toX%240))/240;
			int toY = focusY+getHeight(); toY = (toY-(toY%320))/320;
			
			g.fillRect(0,0,getWidth(),getHeight());
			for (int i = fromX; i <= toX; i++){ for (int j = fromY; j <= toY; j++){
				if (i<3 && j<2) {
					g.drawImage(im[j][i], (i*240)-focusX, (j*320)-focusY, Graphics.TOP | Graphics.LEFT);
				}
			}}
			g.setColor(0, 0, 0);
			if (this.title.equals("Walking")) {
				g.drawRect(b[result[1]].absX-focusX-5, b[result[1]].absY-focusY-5, 10, 10);
				for (int i = 2; i < result.length; i++){
					g.setColor(0, 0, 0);
					g.drawLine(b[result[i-1]].absX-focusX, b[result[i-1]].absY-focusY, b[result[i]].absX-focusX, b[result[i]].absY-focusY);
					g.setColor(130, 0, 0);
					g.fillArc(b[result[i]].absX-focusX-5, b[result[i]].absY-focusY-5, 10, 10, 0, 360);
					//g.setColor(255, 255, 255);
					//g.drawString(""+(i-1), b[result[i]].absX-focusX-(g.getFont().stringWidth(""+(i-1))/2), b[result[i]].absY-focusY-(g.getFont().getHeight()), Graphics.TOP | Graphics.LEFT);
					//g.setColor(250, 50, 50);
					//g.drawRect(b[result[i]].absX-focusX-5, b[result[i]].absY-focusY-5, 10,10);
				}
			}
			else {
				g.drawRect(b[result[1]].jX-focusX-5, b[result[1]].jY-focusY-5, 10, 10);
				for (int i = 2; i < result.length; i++){
					g.setColor(0, 0, 0);
					g.drawLine(b[result[i-1]].jX-focusX, b[result[i-1]].jY-focusY, b[result[i]].jX-focusX, b[result[i]].jY-focusY);
					g.setColor(130, 0, 0);
					g.fillArc(b[result[i]].jX-focusX-5, b[result[i]].jY-focusY-5, 10, 10, 0, 360);
					//g.setColor(255, 255, 255);
					//g.drawString(""+(i-1), b[result[i]].jX-focusX-(g.getFont().stringWidth(""+(i-1))/2), b[result[i]].jY-focusY-(g.getFont().getHeight()), Graphics.TOP | Graphics.LEFT);
					//g.setColor(250, 50, 50);					
					//g.drawRect(b[result[i]].jX-focusX-5, b[result[i]].jY-focusY-5, 10,10);
				}
			}
		}
		
		g.setColor(0, 0, 0);
		g.fillRect(getWidth()-g.getFont().stringWidth(title)-10, 0, getWidth(), g.getFont().getHeight()+10);
		g.setColor(255, 255, 255);
		g.drawString(title,getWidth()- g.getFont().stringWidth(title)-5, 5, 0);
		
		if (pulldown==1) {
			g.setColor(250,240,240);
			g.fillRect(10, 0, getWidth()-20, getHeight());
			g.setFont(Font.getFont(Font.FONT_STATIC_TEXT,Font.STYLE_PLAIN,Font.SIZE_MEDIUM));
			g.setColor(0,0,0);	
			int pos = 20;
			for (int i = 1; i < result.length; i++){
				pos = pos + g.getFont().getHeight();
				if (g.getFont().stringWidth(""+(i)+". "+b[result[i]].keys[0])>getWidth()-30) {
					boolean lenOK = false;
					int cutOff = 0;
					String toWrite = b[result[i]].keys[0];
					while (!lenOK) {
						cutOff = toWrite.lastIndexOf(' ');
						toWrite = toWrite.substring(0,cutOff);
						if (g.getFont().stringWidth(toWrite)<getWidth()-50) {
							g.drawString(""+(i)+". "+toWrite, 20, pos-moverY, 0);
							pos = pos + g.getFont().getHeight();
							g.drawString(b[result[i]].keys[0].substring(cutOff), 20, pos-moverY, 0);
							lenOK = true;
						}
					}
				}
				else {
					g.drawString(""+(i)+". "+b[result[i]].keys[0], 20, pos-moverY, 0);
				}
			}
			g.setColor(250,240,240);
			g.fillRect(10, 0, getWidth()-20, 2*g.getFont().getHeight());
			g.setColor(130, 0, 0);
			g.fillRect(10, 0, 5, getHeight());
			g.fillRect(getWidth()-15, 0, 5, getHeight());
			g.setFont(Font.getFont(Font.FONT_STATIC_TEXT,Font.STYLE_BOLD,Font.SIZE_MEDIUM));
			g.drawString("Directions", 20, 10, 0);
			g.fillArc(getWidth()-30, 10, 10, 10, 0, 360);
		}
	}
	
	public void setResult(int[] result, String title){
		this.pulldown = 0;
		this.result = result;
		this.title = title;
		this.moverY = 0;
		if (title.equals("Walking")){
			focusX = b[result[1]].absX-(getWidth()/2);
			focusY = b[result[1]].absY-(getHeight()/2);
		}
		else{
			focusX = b[result[1]].jX-(getWidth()/2);
			focusY = b[result[1]].jY-(getHeight()/2);
		}
		if (this.focusX<0) this.focusX = 0;
		else if (this.focusX>(720-getWidth())) this.focusX = (720-getWidth());
		if (this.focusY<0) this.focusY = 0;
		else if (this.focusY>(640-getHeight())) this.focusY = (640-getHeight());
		repaint();
	}
	
	public void keyPressed (int keyCode) {
		int key = getGameAction(keyCode);
		if (pulldown == 0) {
			if (key == Canvas.LEFT) this.focusX -= 10;
			else if (key == Canvas.RIGHT) this.focusX += 10;
			else if (key == Canvas.UP) this.focusY -= 10;
			else if (key == Canvas.DOWN) this.focusY += 10;
			else if (keyCode == Canvas.KEY_NUM1) this.pulldown = 1;
			if (this.focusX<0) this.focusX = 0;
			else if (this.focusX>(720-getWidth())) this.focusX = (720-getWidth());
			if (this.focusY<0) this.focusY = 0;
			else if (this.focusY>(640-getHeight())) this.focusY = (640-getHeight());
		}
		else if (pulldown == 1) {
			if (key == Canvas.UP) this.moverY -= 10;
			else if (key == Canvas.DOWN) this.moverY += 10;
			else if (keyCode == Canvas.KEY_NUM1) this.pulldown = 0;
		}
		repaint();
	}
	public void keyRepeated (int keyCode) {}
	public void pointerPressed (int x, int y) {
		this.pressX = x;
		this.pressY = y;
		if (pulldown == 0 && this.pressX>(getWidth()-strwidth) && this.pressY<fontheight+10) this.pulldown = 1;
		else if (pulldown ==1 && this.pressX>(getWidth()-50) && this.pressY<fontheight+10) this.pulldown = 0;
		repaint();
	}
	public void pointerDragged (int x, int y) {
		if (pulldown == 0) {
			this.focusX += (pressX-x)/2;
			this.focusY += (pressY-y)/2;
			if (this.focusX<0) this.focusX = 0;
			else if (this.focusX>(720-getWidth())) this.focusX = (720-getWidth());
			if (this.focusY<0) this.focusY = 0;
			else if (this.focusY>(640-getHeight())) this.focusY = (640-getHeight());
			this.pressX = x;
			this.pressY = y;
			repaint();
		}
		else if (pulldown == 1) {
			this.moverY += (pressY-y)/2;
			this.pressY = y;
			repaint();
		}
	}
}
