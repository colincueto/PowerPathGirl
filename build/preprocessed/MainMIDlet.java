import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Vector;

public class MainMIDlet extends MIDlet implements CommandListener, ItemStateListener {
	private boolean midletPaused = false;
	
	private List startMenu, selectList;
	private Command endApp, backToMenu, searchPath, select, backToInput;
	private Form inputScreen, browseScreen, aboutScreen;
	private TextField startLoc, endLoc;
	private StringItem status, aboutText, idxText;
	private String startText, endText;
	
	Buildings[] bList;
	byte signal;
	int sidx, eidx;
	int[] bldg;
	Vector minpath;
	
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
					startLoc.setString(selected);
					this.sidx = bldg[sidx];
					System.out.println("Start:"+sidx+" "+bList[sidx].keys[0]);
					this.signal = 1;
				}
				else {
					endLoc.setString(selected);
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
			else if (command == searchPath) {
				this.signal = 0;
				startText = startLoc.getString();
				endText = endLoc.getString();
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
					String m = "";
					m = m.concat("\nFrom: "+bList[sidx].keys[0]);
					m = m.concat("\nTo: "+bList[eidx].keys[0]);
					status.setText(m);
					
					
					
					/**
						PLACE FUNCTIONS HERE
					**/
					
					switchDisplayable(null,inputScreen);
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
			if (__selectedString.equals("Search")) {
				switchDisplayable(null, getInputScreen());
			}
			else if (__selectedString.equals("Browse")) {
				switchDisplayable(null, getBrowseScreen());
			}
			else if (__selectedString.equals("About")) {
				switchDisplayable(null, getAboutScreen());
			}
		}
	}
	public List getStartMenu () {
		if (startMenu == null) {
			startMenu = new List("Powerpath Girl",Choice.IMPLICIT);
			startMenu.append("Search",null);
			startMenu.append("Browse",null);
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
	public Command getBackToInput() {
		if (backToInput == null) {
			backToInput = new Command("Back", Command.BACK, 0);
		}
		return backToInput;
	}
	public Command getEndApp () {
		if (endApp == null) {
			endApp = new Command("Exit", Command.EXIT, 0);
		}
		return endApp;
	}
	public Command getBackToMenu () {
		if (backToMenu == null) {
			backToMenu = new Command("Back", Command.BACK, 0);		
		}
		return backToMenu;
	}
	public Command getSearchPath () {
		if (searchPath == null) {
			searchPath = new Command("GO", Command.ITEM, 0); 
		}
		return searchPath;
	}
	public Command getSelect (){
		if (select == null){
			select = new Command("Search", Command.OK, 0);
		}
		return select;
	}
	public Form getInputScreen () {
		if (inputScreen == null) {
			inputScreen = new Form("Powerpath Girl", new Item[] {getStartLoc(), getEndLoc(), getStatus()});
			inputScreen.addCommand(getBackToMenu());
			inputScreen.addCommand(getSearchPath());
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
	public TextField getStartLoc () {
		if (startLoc == null) {
			startLoc = new TextField("Starting Location",null,30,TextField.ANY);
		}
		return startLoc;
	}
	public TextField getEndLoc () {
		if (endLoc == null) {
			endLoc = new TextField("Ending Location",null,30,TextField.ANY);
		}
		return endLoc;
	}
	public StringItem getStatus() {
		if (status == null) {
			status = new StringItem("",null);
		}
		return status;
	}
	public StringItem getAboutText() {
		if (aboutText == null) {
			aboutText = new StringItem("Powerpath Girl v2.1\n\nCS 192 Project\n2nd Semester SY 2011-2012\n\n","Authors:\nCueto, Jan Colin\nMagno, Jenny\nQuilab, Francis Miguel");
		}
		return aboutText;
	}
	public StringItem getIdxText() {
		if (idxText == null) {
			idxText = new StringItem("List of Buildings:\n",null);
		}
		return idxText;
	}
	
	public void initialize() {
		bList = new Buildings[5];
		bList[0] = new Buildings("Molave Residence Hall", "Molave", "Dormitory", "Residence Hall", "Molav", 0, 0, 0, 0);
		bList[1] = new Buildings("Sanggumay Residence Hall", "Sanggumay", "Dormitory", "Residence Hall", "Sangumay", 0, 0, 0, 0);
		bList[2] = new Buildings("Kalayaan Residence Hall", "Kalayaan", "Dormitory", "Residence Hall", "Kalay", 0, 0, 0, 0);
		bList[3] = new Buildings("Yakal Residence Hall", "Yakal", "Dormitory", "Residence Hall", "Yackal", 0, 0, 0, 0);
		bList[4] = new Buildings("Ipil Residence Hall", "Ipil", "Dormitory", "Residence Hall", "Ippil", 0, 0, 0, 0);
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
