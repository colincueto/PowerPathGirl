import java.util.Vector;

public class WPF{
	Vector currPath, minPath;
	int minDist;
	MapList[] graph;
	
	WPF(MapList[] graph){
		this.graph = graph;
	}
	public void init() {
		this.currPath = new Vector();
		this.minPath = new Vector();
		this.minDist = 32766;
	}

	public void findPath(int start, int end, int currDist){
		if (start == end){
			currPath.addElement(new Integer(end));
			if (currDist < minDist){
				minDist = currDist;
				minPath = new Vector();
				for (int i = 0; i < currPath.size(); i++)
					minPath.addElement(currPath.elementAt(i));
			}
		}
		else{
			currPath.addElement(new Integer(start));
			MapNode iter = graph[start].head;
			while (iter != null){
				if (!(currPath.contains(new Integer(iter.bindex))) && (currDist < minDist)){
					findPath(iter.bindex, end, (int)(currDist + iter.dist));
					currPath.removeElementAt(currPath.size()-1);
				}
				iter = iter.next;
			}
		}
	}
} 