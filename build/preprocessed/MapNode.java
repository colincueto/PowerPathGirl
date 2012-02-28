public class MapNode{
	byte bindex;
	short dist;
	MapNode next;
	
	MapNode(byte bindex, short dist){
		this.bindex = bindex;
		this.dist = dist;
		this.next = null;
	}
}