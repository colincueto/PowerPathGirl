import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Vector;

public class MainMIDlet extends MIDlet implements CommandListener, ItemStateListener {
	private boolean midletPaused = false;
	
	private List startMenu, selectList;
	private Command endApp, backToMenu, searchPath, select, backToInput, clearInput, switcher;
	private Form inputScreen, browseScreen, aboutScreen, directions;
	private TextField startLoc, endLoc;
	private StringItem status, aboutText, idxText, dir;
	private String startText, endText;
	private MapScreen mapScreen;
	
	Buildings[] bList;
	byte signal, sswitch;
	int sidx, eidx;
	int[] bldg, minPath, jMinPath;
	Vector minpath;
	
	WPF wpf;
	JPF jpf;
	MapList[] graph;
	
	public void commandAction(Command command, Displayable displayable) {
		if (displayable == startMenu) {
			if (command == List.SELECT_COMMAND) {
				chooseInMenu();
			}
			else if (command == endApp) {
				exitMIDlet();
			}
		}
		else if (displayable == selectList) {
			if (command == List.SELECT_COMMAND) {
				String selected = selectListAction();
				if (signal==0){
					getStartLoc().setString(selected);
					this.sidx = bldg[sidx];
					System.out.println("Start:"+sidx+" "+bList[sidx].keys[0]);
					this.signal = 1;
				}
				else {
					getEndLoc().setString(selected);
					this.eidx = bldg[eidx];
					System.out.println("End: "+eidx+" "+bList[eidx].keys[0]);
					this.signal = 0;
				}
				getSelectList().deleteAll();
				switchDisplayable(null,getInputScreen());
			}
			else if (command == backToInput) {
				getSelectList().deleteAll();
				switchDisplayable(null,getInputScreen());
			}
		}
		else if (displayable == inputScreen) {
			if (command == backToMenu) {
				getSelectList().deleteAll();
				getStartLoc().setString("");
				getEndLoc().setString("");
				this.signal = 0;
				status.setText("");
				switchDisplayable(null,getStartMenu());
			}
			else if (command == clearInput) {
				getStartLoc().setString("");
				getEndLoc().setString("");
				this.signal = 0;
				status.setText("");
			}
			else if (command == searchPath) {
				this.signal = 0;
				startText = getStartLoc().getString();
				endText = getEndLoc().getString();
				if (startText.equals("") && endText.equals("")) {
					status.setText("Cannot accept empty values.");
				}
				else if (startText.equals("")) {
					status.setText("Starting Location is not specified. Cannot accept empty values.");
				}
				else if (endText.equals("")) {
					status.setText("Ending Location is not specified. Cannot accept empty values.");
				}
				else if (sidx == -1) {
					status.setText("Starting Location is not recognized.");
				}
				else if (eidx == -1) {
					status.setText("Ending Location is not recognized.");
				}
				else {
					try {
						String m = "";						
						wpf.init();
						wpf.findPath(this.sidx, this.eidx, 0);
						minpath = wpf.minPath;
						minPath = new int[minpath.size()+1];
						minPath[0] = -1;
						for (int i=1; i<minpath.size()+1; i++) {
							Integer x = (Integer) minpath.elementAt(i-1);
							minPath[i] = x.intValue();
						}
						status.setText(m);
						jMinPath = jpf.findPath(this.sidx, this.eidx);
						sswitch = 0;
						getMapScreen().setResult(minPath, "Walking");
						
						switchDisplayable(null,getMapScreen());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		else if (displayable == aboutScreen) {
			if (command == backToMenu) {
				switchDisplayable(null,getStartMenu());
			}
		}
		else if (displayable == browseScreen) {
			if (command == backToMenu) {
				switchDisplayable(null,getStartMenu());
			}
		}
		else if (displayable == directions) {
			if (command == backToInput) {
				switchDisplayable(null,getInputScreen());
			}
			else if (command == switcher) {
				if (sswitch == 1) {
					sswitch = 0;
					mapScreen.setResult(minPath, "Walking");
					switchDisplayable(null,mapScreen);
				}
			}
		}
		else if (displayable == mapScreen) {
			if (command == backToInput) {
				switchDisplayable(null,getInputScreen());
			}
			else if (command == switcher){
				if (jMinPath.length == 0 && sswitch == 0) {
						getDirectionText().setText("No Jeep passing both locations.");
						switchDisplayable(null,getDirections());
						sswitch = 1;
				}
				else if (jMinPath.length >0 && sswitch == 0) {
					sswitch = 1;
					mapScreen.setResult(jMinPath, jpf.jeep[jMinPath[0]].name);
					switchDisplayable(null,mapScreen);
				}
				else if (sswitch == 1) {
					sswitch = 0;					
					mapScreen.setResult(minPath, "Walking");
					switchDisplayable(null,mapScreen);
				}
			}
		}
	}
		
	public String selectListAction() {
		String __selectedString = getSelectList().getString(getSelectList().getSelectedIndex());
		if (signal==0) this.sidx = getSelectList().getSelectedIndex();
		else this.eidx = getSelectList().getSelectedIndex();
		return __selectedString;
	}
	public void chooseInMenu() {
		String __selectedString = getStartMenu().getString(getStartMenu().getSelectedIndex());
		if (__selectedString != null) {
			if (__selectedString.equals("Search")) switchDisplayable(null, getInputScreen());
			else if (__selectedString.equals("Help")) switchDisplayable(null, getBrowseScreen());
			else if (__selectedString.equals("About")) switchDisplayable(null, getAboutScreen());
		}
	}
	public MapScreen getMapScreen() throws IOException {
		if (mapScreen == null) {
			mapScreen = new MapScreen(bList);
			mapScreen.addCommand(getBackToInput());
			mapScreen.addCommand(getSwitcher());
			mapScreen.setCommandListener(this);
		}
		return mapScreen;
	}
	public List getStartMenu () {
		if (startMenu == null) {
			startMenu = new List("Powerpath Girl",Choice.IMPLICIT);
			startMenu.append("Search",null);
			startMenu.append("Help",null);
			startMenu.append("About",null);
			startMenu.addCommand(getEndApp());
			startMenu.setCommandListener(this);
		}
		return startMenu;
	}
	public List getSelectList() {
		if (selectList == null) {
			selectList = new List("",Choice.IMPLICIT);
			selectList.addCommand(getBackToInput());
			selectList.setCommandListener(this);
		}
		return selectList;
	}
	public Form getInputScreen () {
		if (inputScreen == null) {
			inputScreen = new Form("Powerpath Girl", new Item[] {getStartLoc(), getEndLoc(), getStatus()});
			inputScreen.addCommand(getBackToMenu());
			inputScreen.addCommand(getSearchPath());
			inputScreen.addCommand(getClearInput());
			inputScreen.setCommandListener(this);
			inputScreen.setItemStateListener(this);
			this.signal = 0;
			this.sidx = -1;
			this.eidx = -1;
		}
		return inputScreen;
	}
	public Form getAboutScreen () {
		if (aboutScreen == null) {
			aboutScreen = new Form("About Powerpath Girl", new Item[] {getAboutText()});
			aboutScreen.addCommand(getBackToMenu());
			aboutScreen.setCommandListener(this);
		}
		return aboutScreen;
	}
	public Form getBrowseScreen () {
		if (browseScreen == null) {
			browseScreen = new Form("Index", new Item[] {getIdxText()});
			browseScreen.addCommand(getBackToMenu());
			browseScreen.setCommandListener(this);
		}
		return browseScreen;
	}
	public Form getDirections () {
		if (directions == null) {
			directions = new Form("Directions", new Item[] {getDirectionText()});
			directions.addCommand(getBackToInput());
			directions.addCommand(getSwitcher());
			directions.setCommandListener(this);
		}
		return directions;
	}
	public Command getSelect (){
		if (select == null) select = new Command("Search", Command.OK, 0);
		return select;
	}
	public Command getBackToInput() {
		if (backToInput == null) backToInput = new Command("Back", Command.BACK, 0);
		return backToInput;
	}
	public Command getEndApp () {
		if (endApp == null) endApp = new Command("Exit", Command.EXIT, 0);
		return endApp;
	}
	public Command getBackToMenu () {
		if (backToMenu == null) backToMenu = new Command("Back", Command.BACK, 0);		
		return backToMenu;
	}
	public Command getClearInput () {
		if (clearInput == null) clearInput = new Command("Clear", Command.ITEM, 0);		
		return clearInput;
	}
	public Command getSearchPath () {
		if (searchPath == null) searchPath = new Command("GO", Command.ITEM, 0); 
		return searchPath;
	}
	public Command getSwitcher () {
		if (switcher == null) switcher = new Command("Switch", Command.ITEM, 0); 
		return switcher;
	}
	public TextField getStartLoc () {
		if (startLoc == null) startLoc = new TextField("Starting Location","",70,TextField.ANY);
		return startLoc;
	}
	public TextField getEndLoc () {
		if (endLoc == null) endLoc = new TextField("Ending Location","",70,TextField.ANY);
		return endLoc;
	}
	public StringItem getStatus() {
		if (status == null) status = new StringItem("",null);
		return status;
	}
	public StringItem getDirectionText() {
		if (dir == null) dir = new StringItem("",null);
		return dir;
	}
	public StringItem getAboutText() {
		if (aboutText == null) aboutText = new StringItem("Powerpath Girl v3.4"
				  + "","\nCS 192 Project\n2nd Semester SY 2011-2012\n\nAuthors:\nCueto, Jan Colin\nMagno, Jenny\nQuilab, Francis Miguel");
		return aboutText;
	}
	public StringItem getIdxText() {
		if (idxText == null) {
			String list = "Powerpath Girl is a shortest path finder between two buildings in the University of the Philippines - Diliman.\n\n"+"To start searching for a path, type inside any of the textboxes. You are allowed to type the name or aliases of the building. You will be directed to a list of buildings if the input is recognized.\n\nYou may clear the contents of the textboxes by pressing CLEAR.\n\nYou may also go back to the menu screen by pressing BACK.\n\nAfter both textboxes are filled, press GO.\n\nThe screen will display the shortest path via walking together with the map of UP Diliman.\n\nPressing UP, DOWN, LEFT and RIGHT keys will pan the map.\n\nThe SWITCH command changes the current display between the shortest path via walking and via riding a jeep.\n\nPressing BACK will redirect the user back to the input screen.";
			idxText = new StringItem(null,list);
		}
		return idxText;
	}
	
	public void initialize() {
		bList = new Buildings[63];
		graph = new MapList[63];
		Initializer init = new Initializer();
		init.initData(bList, graph);
		
		wpf = new WPF (graph);
		jpf = new JPF (graph);
	}
	public void startMIDlet() {
		switchDisplayable(null, getStartMenu());
	}
	public void resumeMIDlet() {}
	
	public void switchDisplayable(Alert alert, Displayable nextDisplayable) {
		Display display = getDisplay();                                               
		if (alert == null) {
			display.setCurrent(nextDisplayable);
		} else {
			display.setCurrent(alert, nextDisplayable);
		}
   }
	public Display getDisplay() {
		return Display.getDisplay(this);
	}
	public void exitMIDlet() {
		try {
			switchDisplayable(null, null);
			destroyApp(true);
			notifyDestroyed();
		} catch (Exception e) {}
	}
	protected void pauseApp() {
		midletPaused = true;
	}
	protected void startApp() throws MIDletStateChangeException {
		if (midletPaused) {
			resumeMIDlet();
		} else {
			initialize();
			startMIDlet();
		}
		midletPaused = false;
	}
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {}

	public void itemStateChanged(Item item) {
		if (item == getStartLoc()) {
			this.signal = 0;
			this.sidx = -1;
			BSearch bs = new BSearch();
			bldg = bs.search(getStartLoc().getString(),bList);
			if (bldg[0] != -1){
				for (int i = 0; i < bldg.length; i++){
					getSelectList().append(bList[bldg[i]].keys[0], null);
				}
				switchDisplayable(null, getSelectList());
			}
		}
		if (item == getEndLoc()) {
			this.signal = 1;
			this.eidx = -1;
			BSearch bs = new BSearch();
			bldg = bs.search(getEndLoc().getString(),bList);
			if (bldg[0] != -1){
				for (int i = 0; i < bldg.length; i++){
					getSelectList().append(bList[bldg[i]].keys[0], null);
				}
				switchDisplayable(null, getSelectList());
			}	
		}
	}
}
