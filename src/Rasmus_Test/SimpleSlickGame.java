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
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class SimpleSlickGame extends BasicGame
{
	
	public Image block; 
	//block = new Image("Assets/marioblock.png");
	//public blocks thisblock; 
	public entity myEntity; 
	public blocks myBlocks; 
	public Circle mousepointer; 
	public Circle boundingbox3; 
	public Image background; 
	
	float mouseX; 
	float mouseY; 
	
	float backgroundY = -200; 
	float backgroundX = 0; 

	 
	
	public SimpleSlickGame(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		block = new Image("Assets/marioblock.png");
		//mousepointer = new Image("Assets/marioblock2.png");
		mousepointer = new Circle(1,1,30);
		myEntity = new entity(10,10) {
		};
		myBlocks = new blocks(0,300,50,50,block,boundingbox3) {
		
		};
		background = new Image("Assets/background.png");	
		//block = new Image("Assets/marioblock.png");
		//entity newentity = new entity(10, 10, 50, 50, block);
		// thisblocks = new blocks(10,10,50,50, block);
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		//mouseX = gc.getInput().getMouseX(); 
		//mouseY = gc.getInput().getMouseY(); 
		
		
		
		mousepointer.setCenterX(gc.getInput().getMouseX());
		mousepointer.setCenterY(gc.getInput().getMouseY());
		
		Input input = gc.getInput(); 
		if(input.isKeyDown(Input.KEY_D))
		{
			myBlocks.x_pos += 0.5f*delta;
			backgroundX += 0.03f*delta; 
		}
		if(input.isKeyDown(Input.KEY_A))
		{
			myBlocks.x_pos -= 0.5f*delta;
			backgroundX -= 0.03f*delta; 
		}
		
		if(mousepointer.intersects(blocks.boundingbox))
		{
			System.out.println("HIT DETECTION!");
		}
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		background.draw(backgroundX,backgroundY,8192/2, 1024/2);//,480,3275);
		map.buildground();
		
		//blocks.draw(); 
		//newentity.render(gc, g);
		//block.draw(200,200,200,200);
		//mousepointer.draw(mouseX, mouseY, 10,10);
	
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
