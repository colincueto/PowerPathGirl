public class MapList{
	MapNode head, tail;

	MapList(){
		this.head = null;
		this.tail = null;
	}
	
	public void addNode(byte bindex, short dist){
		if (this.head == null){
			this.head = new MapNode(bindex, dist);
			this.tail = this.head;
		}
		else{
			tail.next = new MapNode(bindex, dist);
			tail = tail.next;
		}
	}
}