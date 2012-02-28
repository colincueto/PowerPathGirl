import java.util.Vector;

public class BSearch {
	public BSearch () {}
	public int[] search (String key, Buildings[] bList) {
        int[] result;
        Vector resultV = new Vector();
        boolean found = false;
        
        for (int i = 0; i < bList.length; i++){
            for (int j = 0; j < 5 && !found; j++){
                if (bList[i].keys[j].equals(key)){
                    found = true;
                    resultV.addElement(new Integer(i));
                }
            }
            found = false;
        }
        if (resultV.isEmpty()){
            result = new int[1];
            result[0] = -1;
        } else {
            result = new int[resultV.size()];
            for (int k = 0; k < resultV.size(); k++){
               Integer x = (Integer) resultV.elementAt(k); 
					result[k] = x.intValue();
            }
        }
        return result;
    }
}
