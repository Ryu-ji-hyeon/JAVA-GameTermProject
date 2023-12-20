package snake;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.LinkedList;


public class MyFrame extends JFrame {
	static JPanel panelNorth;
	static JPanel panelCenter;
	static JLabel labelTitle;
	static JLabel labelMessage;
	static JPanel[][] panels = new JPanel[20][20];
	static int [][] map = new int [20][20]; //Fruit 위치

	static int dir = 3;// 뱀 진행방향 0: up 1:down 2:left 3: right 왼쪽에서 오른쪽으로 가는 방향으로 초기화함
	int score = 0;
	static int time=0;// gametime(unit 1 second)
	static int timeTickCount = 0; // per 200ms
	static Timer timer =null;
	static int countE=0;
	static boolean isMainScreen;
	public static Image initUi;

	static Snake  snake = new Snake();

	public MyFrame(String title) {
		super(title);
		this.setSize(400,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initUI();
		
		snake.makeSnakeList();// snake 몸통
		startTimer();
		setKeyListener();
		makeFruit();//뱀이 먹는 열매
		makeBomb();
	}

	public void makeFruit() {
		Random rand = new Random(); //0~19 x , 0~ 19 y 배치
		int randX = rand.nextInt(19);
		int randY = rand.nextInt(19);
		map[randX][randY]=9; // 첫 초기 배치는 [9][9]로 지정
	}
	public void makeBomb() {
		Random rand = new Random(); //0~19 x , 0~ 19 y 배치
		int randX = rand.nextInt(19);
		int randY = rand.nextInt(19);
		map[randX][randY]=5; // 첫 초기 배치는 [9][9]로 지정
	}
	public void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) { //0: up 1:down 2:left 3: right
				if(e.getKeyCode()== KeyEvent.VK_UP) {
					if(dir != 1)// down중에는 up 불가능
						dir =0;
				}
				else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
					if(dir != 0)
						dir = 1;
				}
				else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
					if(dir != 3)
						dir=2;
				}
				else if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
					if(dir != 2)
						dir=3;
				}
			}
		});
	}
	public void startTimer() {
		timer=new Timer(200,new ActionListener() {// 1초에 200ms 간격
			@Override
			public void actionPerformed(ActionEvent e) {
				timeTickCount +=1;

				if(timeTickCount % 5 == 0) { //5개일 때  1초
					time ++; // 1초 증가
				}
				moveSnake();// move snake
				updateUI(); // UI갱신
			}
		});
		timer.start();// 시작
	}
	public void moveSnake() {
		XY headXY= new XY();// 머리 가져옴
		headXY = snake.getSnake(0);
		int headX= headXY.x;
		int headY= headXY.y;

		if(dir==0) {// 위로 가고있을때
			boolean isColl = checkCollision(headX,headY-1);
			if(isColl == true) {//gameover
				labelMessage.setText("GAME OVER !");
				timer.stop();
				return;
			}
			snake.add(0,new XY(headX,headY-1));// 머리 추가
			snake.remove(snake.size()-1); // 꼬리 지움
		}
		else if(dir==1) {// 아래로 가고있을때
			boolean isColl = checkCollision(headX,headY+1);
			if(isColl == true) {//gameover
				labelMessage.setText("GAME OVER !");
				timer.stop();
				return;
			}
			snake.add(0,new XY(headX,headY+1));// 머리 추가
			snake.remove(snake.size()-1); // 꼬리 지움
		}
		else if(dir==2) {// 왼쪽으로 가고있을때
			boolean isColl = checkCollision(headX-1,headY);
			if(isColl == true) {//gameover
				labelMessage.setText("GAME OVER !");
				timer.stop();
				return;
			}
			snake.add(0,new XY(headX-1,headY));// 머리 추가
			snake.remove(snake.size()-1); // 꼬리 지움
		}
		else if(dir==3) {// 오른쪽으로 가고있을때
			boolean isColl = checkCollision(headX+1,headY);
			if(isColl == true) {//gameover
				labelMessage.setText("GAME OVER !");
				timer.stop();
				return;
			}
			snake.add(0,new XY(headX+1,headY));// 머리 추가
			snake.remove(snake.size()-1); // 꼬리 지움
		}
	}
	public boolean checkCollision(int headX,int headY) {
		if(headX<0 || headX>19 || headY<0 || headY>19) {//벽에 충돌

			new GameOverUI(score);
			setVisible(false); 
			return true;
		}
		// 자기 몸에 부딪혔을때
		for(XY xy : snake ) {
			if(headX == xy.x && headY == xy.y) {
				timer.stop();
				new GameOverUI(score);
				setVisible(false);
				return true;

			}
		}
		if(map[headY][headX]==9) {//열매에 충돌할때
			map[headY][headX]=0;
			snake.addTail();
			makeFruit();
			score +=100;
		}
		if(map[headY][headX]==5) {
			timer.stop();
			new GameOverUI(score);
			setVisible(false);
			return true;
		}
		return false;
	}
	public void updateUI() {
		labelTitle.setText("SCORE : " + score + " " + "TIME: " + time);

		//clear title(panel)
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				if(map[i][j]== 0) { // 빈 공간
					panels[i][j].setBackground(Color.WHITE);
				}
				else if(map[i][j]==9) {//열매, 혹여나 열매 위치가 snake 머리 위에 생기면,,,, 나중에,,,
					panels[i][j].setBackground(Color.red);
				}
				else if(map[i][j]==5) {
					panels[i][j].setBackground(Color.black);

				}
			}

		}
		// draw snake
		int index=0;


		for(XY xy : snake) {
			if(index==0) {//머리
				panels[xy.y][xy.x].setBackground(Color.yellow);

			}else {// 몸통..(몸통 여러개), 꼬리
				panels[xy.y][xy.x].setBackground(Color.green);
			}
			index++;
		}


	}
	public void initUI() {
		this.setLayout(new BorderLayout());

		panelNorth = new JPanel();
		panelNorth.setPreferredSize(new Dimension(400,100));
		panelNorth.setBackground(Color.BLACK);
		panelNorth.setLayout(new FlowLayout());

		labelTitle = new JLabel("Score : 0 , Time : 0 Sec");
		labelTitle.setPreferredSize(new Dimension(400,50));
		labelTitle.setFont(new Font("TimesNorman",Font.BOLD,20));
		labelTitle.setForeground(Color.WHITE);// 글씨 색깔
		labelTitle.setHorizontalAlignment(JLabel.CENTER);// 글씨 가운데 위치
		panelNorth.add(labelTitle);

		labelMessage = new JLabel("Eat Fruit");
		labelMessage.setPreferredSize(new Dimension(400,20));
		labelMessage.setFont(new Font("TimesNorman",Font.BOLD,20));
		labelMessage.setForeground(Color.YELLOW);// 글씨 색깔
		labelMessage.setHorizontalAlignment(JLabel.CENTER);// 글씨 가운데 위치
		panelNorth.add(labelMessage);

		this.add("North",panelNorth);

		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(20,20)); //바둑판
		for(int i=0;i<20;i++) { // i 루프 = 가로
			for(int j=0; j<20;j++) {// j 루프 = 세로
				map[i][j]=0; // init 0 = blank 공백
				panels[i][j]= new JPanel();
				panels[i][j].setPreferredSize(new Dimension(20,20));
				panels[i][j].setBackground(Color.WHITE);
				panelCenter.add(panels[i][j]);

			}
		}
		this.add("Center",panelCenter);
		this.pack(); //빈 공간 다없애줌

	}
}

