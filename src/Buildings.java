public class Buildings{
	String[] keys;
	int absX, absY, jX, jY;
	
	Buildings (String name, String key1, String key2, String key3, String key4, int absX, int absY, int jX, int jY){
		this.keys = new String [5];
		this.keys[0] = name;
		this.keys[1] = key1;
		this.keys[2] = key2;
		this.keys[3] = key3;
		this.keys[4] = key4;
		this.absX = absX; this.absY = absY;
		this.jX = jX; this.jY = jY;
	}
}