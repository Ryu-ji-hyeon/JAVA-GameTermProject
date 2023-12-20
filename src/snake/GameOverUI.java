package snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



public class GameOverUI extends JFrame {
	ImageIcon i = new ImageIcon("gameover.png");
	Image im=i.getImage();		
	JLabel scoreLabel;

	ImageIcon start=new ImageIcon("start.jpg");
	ImageIcon finish=new ImageIcon("finish.jpg");
	

	public GameOverUI(int score){

		this.setTitle("SnakeGame");
		scoreLabel=new JLabel();


		MyPanel panel = new MyPanel();
		this.add(panel);

		scoreLabel.setFont(new Font("FTLAB Hoony",Font.BOLD,40));
		scoreLabel.setBounds(230,500,300,120);
		panel.setLayout(null);
		scoreLabel.setLayout(null);

		scoreLabel.setText("SCORE : " + score);
		scoreLabel.setForeground(Color.red);


		panel.add(scoreLabel);
		JButton btn = new JButton();
		JButton btn1 = new JButton(start);
		JButton btn2 = new JButton(finish);

		btn1.setBounds(100,620,300,93);
		panel.add(btn1);
		btn1.setBorderPainted(false);//외곽선없애기
		btn1.setContentAreaFilled(false);//채우기없애기

		btn2.setBounds(460,620,250,93);
		btn2.setBorderPainted(false);//외곽선없애기
		btn2.setContentAreaFilled(false);//채우기없애기

		panel.add(btn2);
		add(panel);
		this.setSize(800, 800); //창 크기 설정
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		btn1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JButton jb=(JButton)e.getSource();
				if(jb==btn1) {
					Snake.clear();	
					new MyFrame("Snake game");	
					setVisible(false);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		btn2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JButton jb=(JButton)e.getSource();
				if(jb==btn2) {
					System.exit(1);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});

	}
	class MyPanel extends JPanel{

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(im,0,0,getWidth(),getHeight(),this);
		}
	}
}
