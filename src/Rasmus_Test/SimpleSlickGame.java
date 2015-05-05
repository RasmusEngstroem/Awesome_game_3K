package Rasmus_Test;


import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class SimpleSlickGame extends BasicGame
{
	
	public Image block = null; 
	//block = new Image("Assets/marioblock.png");
	entity newentity = new entity(10, 10, 50, 50, block);
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
		//lol

	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		
		block = new Image("Assets/marioblock.png");
		
		
		
	
	}

	@Override
	public void update(GameContainer gc, int detla) throws SlickException 
	{
		Input input = gc.getInput(); 
		if(input.isKeyDown(Input.KEY_A))
		{
			
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
	
		block.draw(200,200,200,200);
		
	
		//String input = JOptionPane.showInputDialog("question");
	}

	public static void main(String[] args)
	{
		
		
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new SimpleSlickGame("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
