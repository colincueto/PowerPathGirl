public class MapList{
	MapNode head, tail;

	MapList(){
		this.head = null;
		this.tail = null;
	}
	
	public void addNode(int bindex, int indexFrom, Buildings[] bList){
		if (this.head == null){
			this.head = new MapNode(bindex, indexFrom, bList);
			this.tail = this.head;
		}
		else{
			tail.next = new MapNode(bindex, indexFrom, bList);
			tail = tail.next;
		}
	}
}