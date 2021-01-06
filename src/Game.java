import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener{

	private ImageIcon titleImage;
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;
	private boolean up = false;

	private int snakelength = 3;
	private int score = 0;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon downmouth;
	private ImageIcon upmouth;
	
	private Timer timer;
	private int delay = 150;
	
	private ImageIcon snakeImage;
	
	private int [] eggXposition = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	private int [] eggYposition = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private ImageIcon eggImage;
	
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	private int moves = 0;
	
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		if(moves == 0) {
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;
			
			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}
		
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Score: "+score, 780, 30);
		
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int i = 0; i < snakelength ; i++) {
			
			if(i==0 && right) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
				
			}
			
			if(i==0 && left) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
				
			}
			
			if(i==0 && down) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
				
			}
			
			if(i==0 && up) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
				
			}
			
			if(i!=0 ) {
				snakeImage = new ImageIcon("snakeimage.png");
				snakeImage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}
		
		eggImage = new ImageIcon("enemy.png");
		
		if((eggXposition[xpos] == snakeXlength[0]) && eggYposition[ypos] == snakeYlength[0]) {
			
			score = score + 10;
			snakelength++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		eggImage.paintIcon(this, g, eggXposition[xpos], eggYposition[ypos]);
		
		for(int k = 1; k < snakelength; k++) {
			
			if(snakeXlength[k] == snakeXlength[0] && snakeYlength[k] == snakeYlength[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Space to RESTART", 315, 340);
			}
			
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(right) {
			for(int j = snakelength-1; j>=0; j--) {
				snakeYlength[j+1] = snakeYlength[j];
			}
			
			for(int j = snakelength; j>=0; j--) {
				if(j==0) {
					snakeXlength[j] = snakeXlength[j] + 25;
				}
				else {
					snakeXlength[j] = snakeXlength[j-1];
				}
				
				if(snakeXlength[j] > 850) {
					snakeXlength[j] = 25;
				}
			}
			
			repaint();
		}

		if(left) {
			
			for(int j = snakelength-1; j>=0; j--) {
				snakeYlength[j+1] = snakeYlength[j];
			}
			
			for(int j = snakelength; j>=0; j--) {
				if(j==0) {
					snakeXlength[j] = snakeXlength[j] - 25;
				}
				else {
					snakeXlength[j] = snakeXlength[j-1];
				}
				
				if(snakeXlength[j] < 25) {
					snakeXlength[j] = 850;
				}
			}
			
			repaint();
			
		}

		if(down) {
			
			for(int j = snakelength-1; j>=0; j--) {
				snakeXlength[j+1] = snakeXlength[j];
			}
			
			for(int j = snakelength; j>=0; j--) {
				if(j==0) {
					snakeYlength[j] = snakeYlength[j] + 25;
				}
				else {
					snakeYlength[j] = snakeYlength[j-1];
				}
				
				if(snakeYlength[j] > 625) {
					snakeYlength[j] = 75;
				}
			}
			
			repaint();
			
		}

		if(up) {
			
			for(int j = snakelength-1; j>=0; j--) {
				snakeXlength[j+1] = snakeXlength[j];
			}
			
			for(int j = snakelength; j>=0; j--) {
				if(j==0) {
					snakeYlength[j] = snakeYlength[j] - 25;
				}
				else {
					snakeYlength[j] = snakeYlength[j-1];
				}
				
				if(snakeYlength[j] < 75) {
					snakeYlength[j] = 625;
				}
			}
			
			repaint();
			
		}

		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			snakelength = 3;
			repaint();
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
			
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
			
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			
			right = false;
			left = false;
			
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				down = false;
				up = true;
			}
			
			left = false;
			right = false;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
