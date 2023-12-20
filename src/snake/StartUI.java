package snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Start UI
public class StartUI extends JFrame {
	ImageIcon i = new ImageIcon("snake.png");
	Image im=i.getImage();

    public StartUI() {
    	this.setTitle("SnakeGame");

		MyPanel panel = new MyPanel();

		this.add(panel);


		panel.setLayout(null);
		JButton btn = new JButton();
		btn.setBorderPainted(false);//외곽선없애기
		btn.setContentAreaFilled(false);//채우기없애기
		btn.setBounds(0,0,0,0);//버튼크기, 위치 지정
		panel.add(btn);
		add(panel);

		this. setSize(800, 800); //창 크기 설정
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);


		btn.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					new ExplainUI();
					setVisible(false);	
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
		});

	}
    class MyPanel extends JPanel{

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(im,0,0,getWidth(),getHeight(),this);
		}
	}
    
}
