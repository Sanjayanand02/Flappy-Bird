
package flappybird;

/**
 *
 * @author Sanjay Anand
 */
 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Flappybird implements ActionListener, MouseListener, KeyListener
{

	public static Flappybird flappyBird;

	public final int WIDTH = 800, HEIGHT = 800;

	public Renderer renderer;

        String name,second;
        
        public int secondscore;
        
	public Rectangle bird;

	public ArrayList<Rectangle> columns;

	public int ticks, yMotion, score;

	public boolean gameOver, started , touch;

	public Random rand;
       

	public Flappybird()
	{
		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();
                
		jframe.add(renderer);
		jframe.setTitle("Flappy Bird ...... By:- Sanjay Anand");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
                name = JOptionPane.showInputDialog("Name");
		timer.start();
	}

	public void addColumn(boolean start)
	{
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
                
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump()
	{
		if (gameOver)
		{
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
                }

		if (!started)
		{
			started = true;
		}
		else if (!gameOver)
		{
			if (yMotion > 0)
			{
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int speed = 10;

		ticks++;

		if (started)
		{
			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0)
				{
					columns.remove(column);

					if (column.y == 0)
					{
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;

			for (Rectangle column : columns)
			{
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{  
                                    if(gameOver)
                                    { 
                                        started=false;
                                    score=score;
                                                
                                        if(!started)
                                    { 
                                     //  gamedata.setName(name);
                                         
                                     int input = JOptionPane.showOptionDialog(null, name+" you have scored "+score, "Your Score", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

                                     if(input == JOptionPane.OK_OPTION)
                                     {
                                         
                                       //if(input == JOptionPane.OK_OPTION)
            
        // { 
            int inpt = JOptionPane.showOptionDialog(null, "<html> you have to click or press space for start the game .<br>  Click OK for continue as "+name +".  <br> Click CANCEL for continue as New User </html>  " , "Choose", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
  
            {
                if(inpt == JOptionPane.OK_OPTION)
                { int mark;
                    if(score>secondscore)
                    {
                        mark=JOptionPane.showOptionDialog(null, "<html> 1. Aman"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"58<br> 2. "+ name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+score+"<br> 3. "+ second+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+secondscore+"<br><br> Click OK for continue  </html>  " , "Scores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }
                    else
                    {
                        mark=JOptionPane.showOptionDialog(null, "<html> 1. Aman"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"58<br> 2. "+ second+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+secondscore+"<br> 3. "+ name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+score+"<br><br> Click OK for continue  </html>  " , "Scores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }
                   if(mark == JOptionPane.OK_OPTION)
                {
          //String nameusrer= g.getName();
            //System.out.println(""+usernan);
            infoBox("Continue  "+name,"");
            // JOptionPane.showConfirmDialog(, f)
                                        // JlableScoreone.setText(f.name);
         //  JOptionPane.showOptionDialog(null, " You wish to continue as  " +f.name, "The title", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
     // JOptionPane.showMessageDialog(null,f.name, "InfoBox: " + "title", JOptionPane.INFORMATION_MESSAGE);
                }
                }
                   else if(inpt == JOptionPane.CANCEL_OPTION)
                                     {
                                          second=name;
                                          secondscore=score;
                                          String str = JOptionPane.showInputDialog("New User");
                                        // System.out.println(str);
                                         name=str;
                                        // g.setName(str);
                                     //  String mj = g.getName();
                                    ///   f.name=str;
                                     }
        
                                         // String strin=gamedata.getName();
                                         //  name=strin;
//                                       
//                                        ScoresRecord s=new ScoresRecord();
//                                      // s.JlableScoreone.setText(name);
//                                        s.setVisible(true);
                                        //System.out.println(""+name);
                                       
                                                                                
                                     }
            //}
        }
    
                                     else if(input == JOptionPane.CANCEL_OPTION)
                                     {
                                        System.exit(0);
                                       
                                     }

                                    }
                                    }
                                    else
                                    {
                                    score++;
                                    }
                                }

				if (column.intersects(bird))
				{
					gameOver = true;
                                        touch = true;
					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;

					}
					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height)
						{
							bird.y = column.height;
						}
					}
				}
                            
//                                   if(gameOver||touch)
//                                    {
//                                        touch=true;
//                                        
//
//                                    }
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}
		}

		renderer.repaint();
	}

        public void repaint(Graphics g)
	{
            //background color
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

                // bottom color
                
		g.setColor(Color.blue);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.yellow);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

                //bird
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
                
                if(gameOver||!started)
                {
                Font font1 = new Font("Verdana", Font.BOLD, 20);
                g.setColor(Color.blue);
                g.setFont(font1);
                g.drawString("Game By :- Sanjay Anand", 300, 450);
                }
		
                for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));

		if (!started)
		{
			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
		}

		if (gameOver)
		{
                    if (gameOver &&!started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		} else
                    {
			g.drawString("Game Over!", 100, HEIGHT / 2 -150);
                        g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
                    }
                }

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
	}

	public static void main(String[] args)
	{
		flappyBird = new Flappybird();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
            		   
		jump();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
           	if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
		 
                    jump();
                        
		}
            
        }
	
	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}
        
  public  void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }


}
