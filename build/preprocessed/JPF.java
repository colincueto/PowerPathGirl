public class JPF{
	Jeep[] jeep;
	MapList[] graph;
	
	JPF (MapList[] graph){
		this.jeep = new Jeep[4];
		int[] ikot = {36, 8, 55, 54, 57, 59, 60, 53, 31, 33, 32, 7, 6, 34, 23, 30, 17, 52, 51, 50, 47, 9, 5, 25, 12, 2, 1, 0, 3, 14, 45, 43, 40, 39, -1};
		int[] toki = {3, 0, 1, 2, 11, 12, 25, 5, 9, 47, 50, 24, 48, 27, 29, 30, 23, 34, 6, 7, 32, 33, 31, 53, 60, 59, 57, 54, 55, 56, 55, 54, 59, 60, 53, 16, 38, 37, 39, 40, 43, 45, 44, 18, 19, 22, 48, -1};
		int[] katips = {47, 9, 5, 25, 12, 11, 2, 1, 0, 3, 48, 46, 18, 19, 22, 27, 29, 30, 17, 52, 51, 50, 0};
		int[] mrt = {18, 19, 22, 27, 29, 30, 17, 52, 51, 50, 47, 9, 5, 25, 12, 11, 2, 1, 0, 3, 48, 46, 10, 45, 0};
		this.jeep[0] = new Jeep("Ikot", ikot);
		this.jeep[1] = new Jeep("Toki", toki);
		this.jeep[2] = new Jeep("Katipunan", katips);
		this.jeep[3] = new Jeep("MRT/Philcoa/SM", mrt);
		this.graph = graph;
	}
	
	public int[] findPath (int start, int end){
		int[] result = {};
		int minDist = 32766;
		
		for (int i = 0; i < 4; i++){
			int[] currPath = jeep[i].findPath(start, end, i);
			if (!((currPath.length == 1) && (currPath[0] == -1))){
				int currDist = 0;
				for (int j = 1; j < currPath.length; j++){
					MapNode iter = graph[currPath[j-1]].head;
					while (iter != null){
						if (iter.bindex == currPath[j]){
							currDist = currDist + iter.dist;
							iter = null;
						}
						if (iter != null) iter = iter.next;
					}
				}
				if (currDist < minDist){
					minDist = currDist;
					result = currPath;
				}
			}
		}
		return result;
	}
}
