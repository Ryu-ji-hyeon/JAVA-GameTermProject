package snake;

import java.util.Iterator;
import java.util.LinkedList;
//JAVA GameTermProject
public class Snake implements Iterable<XY>{
	 private static LinkedList<XY> snake;


    public Snake() {
        snake = new LinkedList<>();
    }

    public LinkedList<XY> getSnakeList() {
        return snake;
    }
    
    @Override
    public Iterator<XY> iterator() {
        return snake.iterator();
    }
    
    public void makeSnakeList() {
        snake.add(new XY(10, 10));
        snake.add(new XY(9, 10));
        snake.add(new XY(8, 10));
    }

    public void addTail() {
        int tailX = snake.get(snake.size() - 1).x;
        int tailY = snake.get(snake.size() - 1).y;
        int tailX2 = snake.get(snake.size() - 2).x;
        int tailY2 = snake.get(snake.size() - 2).y;

        if (tailX < tailX2) {
            snake.add(new XY(tailX - 1, tailY));
        } else if (tailX > tailX2) {
            snake.add(new XY(tailX + 1, tailY));
        } else if (tailY < tailY2) {
            snake.add(new XY(tailX, tailY - 1));
        } else if (tailY > tailY2) {
            snake.add(new XY(tailX, tailY + 1));
        }
    }

    public XY getSnake(int i) {
        return snake.get(i);
    }

    public void add(int i, XY xy) {
        snake.add(i, xy);
    }

    public int size() {
        return snake.size();
    }

    public void remove(int i) {
        snake.remove(i);
    }
    public static void clear() {
       snake.clear();
    }
}
