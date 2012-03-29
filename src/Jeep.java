public class Jeep{
	String name;
	int route [];
	int routeLen;

	Jeep (String name, int [] route){
		this.name = name;
		this.route = route;
		this.routeLen = route.length;
	}
	
	public int[] findPath (int start, int end, int id){
		int startIndex = -1;
		int[] currPath= new int[1];
		currPath[0] = -1;
		for (int i = 0; i < this.routeLen-1; i++){
			if (this.route[i] == start){
				startIndex = i;
				break;
			}
		}

		if (startIndex == -1) return currPath;

		int endIndex = -1;
		for (int i = 1; i < this.routeLen; i++){
			if (this.route[i] == end){
				endIndex = i;
				break;
			}
		}

		if (endIndex == -1) return currPath;

		int pathLen = 0;
		if (endIndex > startIndex) pathLen = endIndex-startIndex+1;
		else if (endIndex < startIndex){
			if (this.route[this.routeLen-1] == -1) pathLen = this.routeLen+endIndex-startIndex-1;
			else return currPath;
		}
		else if (endIndex == startIndex) {
			currPath = new int [2];
			currPath[0] = id;
			currPath[1] = start;
			return currPath;
		}
	
		currPath = new int [pathLen+1];
		currPath[0] = id;
		int j = startIndex;
		for (int i = 1; i <= pathLen; i++){
			currPath[i] = this.route[j];
			j = ((j+1 == this.routeLen-1) && (this.route[j+1] == -1))? 0 : j+1;
		}
		return currPath;
	}
}
