package snake;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
//Snake Move
public class XY {
    public LinkedList<XY> snake = new LinkedList<>();

    int x;
    int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public XY() {
    	
    }
}
