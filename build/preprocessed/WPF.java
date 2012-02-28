import java.util.Vector;

public class WPF{
	Vector currPath, minPath;
	short minDist;
	MapList[] graph;
	
	WPF(MapList[] graph){
		this.currPath = new Vector();
		this.minPath = new Vector();
		this.minDist = 32766;
		this.graph = graph;
	}

	public void findPath(byte start, byte end, short currDist){
		if (start == end){
			if (currDist < minDist){
				minDist = currDist;
				minPath = new Vector();
				for (int i = 0; i < currPath.size(); i++)
					minPath.addElement(currPath.elementAt(i));
			}
		}
		else{
			currPath.addElement(new Byte(start));
			MapNode iter = graph[start].head;
			while (iter != null){
				if (!(currPath.contains(new Byte(iter.bindex))) && (currDist < minDist)){
					findPath(iter.bindex, end, (short)(currDist + iter.dist));
					currPath.removeElementAt(currPath.size()-1);
				}
				iter = iter.next;
			}
		}
	}
} 