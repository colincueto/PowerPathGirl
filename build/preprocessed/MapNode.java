public class MapNode{
	int dist, bindex, indexFrom;
	MapNode next;
	
	MapNode(int bindex, int indexFrom, Buildings[] b){
		this.bindex = bindex;
		this.dist = (int) Math.sqrt(((b[bindex].absX-b[indexFrom].absX)*(b[bindex].absX-b[indexFrom].absX)) + ((b[bindex].absX-b[indexFrom].absX)*(b[bindex].absX-b[indexFrom].absX)));
		this.next = null;
	}
}