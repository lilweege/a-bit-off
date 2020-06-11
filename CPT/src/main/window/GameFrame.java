package main.window;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.assets.AssetFactory;
import main.game.GameEngine;

// java frame containing all things
public class GameFrame extends JFrame implements InputAdapter {
	
	private static final long serialVersionUID = -4455318112702002075L;
	
	public static final int SCREEN_MENU_MAIN = 0;
	
	public static final int SCREEN_MENU_HELP = 1;
	
	public static final int SCREEN_RUNNING_PLAYING = 2;
	
	public static final int SCREEN_RUNNING_PAUSED = 3;
	
	public static final Dimension SIZE = new Dimension(800, 600);
	
	private int screen;
	
	private GameEngine game;
	
	private JButton btnPlay, btnHelp, btnExit, btnBack, btnMenu;
	
	private JLabel bgMenu, bgHelp, bgPaused;
	
	public GameFrame() {
		super("A bit off!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up panel and input
		setContentPane(new RenderPanel());
		getContentPane().setSize(SIZE);
		getContentPane().setFocusable(true);
		getContentPane().setFocusTraversalKeysEnabled(false);
		getContentPane().addKeyListener(this);
		getContentPane().addMouseListener(this);
		getContentPane().addMouseMotionListener(this);
		
		// the order of these matter in older java versions
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		
		// start
		createElements();
		setCursor(AssetFactory.CURSOR_POINTER);
		AssetFactory.playSound(Math.random() > 0.5 ? AssetFactory.SOUND_BGM_SONG_ONE : AssetFactory.SOUND_BGM_SONG_TWO, 0.1, Clip.LOOP_CONTINUOUSLY);
		setVisible(true);
	}
	
	private void createElements() {
		btnPlay = AssetFactory.createButton(AssetFactory.BUTTON_PLAY, l -> play());
		btnHelp = AssetFactory.createButton(AssetFactory.BUTTON_HELP, l -> setScreen(SCREEN_MENU_HELP));
		btnExit = AssetFactory.createButton(AssetFactory.BUTTON_EXIT, l -> System.exit(0));
		btnBack = AssetFactory.createButton(AssetFactory.BUTTON_BACK, l -> setScreen(SCREEN_MENU_MAIN));
		btnMenu = AssetFactory.createButton(AssetFactory.BUTTON_MENU, l -> resetGame());
		bgMenu = AssetFactory.createBackground(AssetFactory.BACKGROUND_MENU);
		bgHelp = AssetFactory.createBackground(AssetFactory.BACKGROUND_HELP);
		bgPaused = AssetFactory.createBackground(AssetFactory.BACKGROUND_PAUSED);
		resetGame();
	}
	
	private boolean firstPlay = true;
	
	// create/reset game engine
	public void resetGame() {
		setScreen(SCREEN_MENU_MAIN);
		game = new GameEngine();
		game.renderer.setManualExit(btnMenu, 50, 450);
		// only show initial hint if first play
		if (firstPlay) {
			firstPlay = false;
			game.world.hint.setShowing(true);
		}
		setIconImage(AssetFactory.GAME_ASSET_BIT_UNSET.getImage());
	}
	
	public void togglePlaying() {
		if (game.isRunning())
			pause();
		else
			play();
	}
	
	private void play() {
		game.play();
		setIconImage(AssetFactory.GAME_ASSET_BIT_SET.getImage());
		setScreen(SCREEN_RUNNING_PLAYING);
	}
	
	private void pause() {
		game.pause();
		game.world.hint.setShowing(false);
		setIconImage(AssetFactory.GAME_ASSET_BIT_UNSET.getImage());
		setScreen(SCREEN_RUNNING_PAUSED);
	}
	
	// direct all input to be handled 
	@Override
	public void keyPressed(KeyEvent e) { handleInput(e); }
	
	@Override
	public void keyReleased(KeyEvent e) { handleInput(e); }
	
	@Override
	public void mousePressed(MouseEvent e) { handleInput(e); }
	
	@Override
	public void mouseReleased(MouseEvent e) { handleInput(e); }
	
	@Override
	public void mouseDragged(MouseEvent e) { handleInput(e); }
	
	@Override
	public void mouseMoved(MouseEvent e) { handleInput(e); }
	
	private void handleInput(InputEvent e) {
		// debugging would go here
		
		if (e instanceof KeyEvent)
			handleKeyEvent((KeyEvent) e);
		else if (e instanceof MouseEvent)
			handleMouseEvent((MouseEvent) e);
	}
	
	private boolean firstPausePress = true;
	
	private void handleKeyEvent(KeyEvent ke) {
		boolean pressed = ke.getID() == KeyEvent.KEY_PRESSED;
		int key = ke.getKeyCode();
		
		// escape key has multiple uses
		if (key == KeyEvent.VK_ESCAPE) {
			if (firstPausePress && pressed &&
				screen != SCREEN_MENU_MAIN) {
				AssetFactory.playSound(AssetFactory.SOUND_SFX_BLIP_ONE, 0.5);
				if (screen == SCREEN_MENU_HELP)
					setScreen(SCREEN_MENU_MAIN);
				else {
					if (game.world.isFinished())
						resetGame();
					else
						togglePlaying();
				}	
			}
			firstPausePress = !pressed;
		}
		
		// otherwise world will handle it
		else {
			game.world.handleKeyEvent(ke);
		}
	}
	
	private void handleMouseEvent(MouseEvent me) {
		// mouse events only matter while playing
		if (screen == SCREEN_RUNNING_PLAYING) {
			// drag and click
			boolean pressed = (me.getButton() == MouseEvent.BUTTON1 && me.getID() == MouseEvent.MOUSE_PRESSED) ||
				(me.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK && me.getID() == MouseEvent.MOUSE_DRAGGED);
			game.world.input.setMouseX(me.getX());
			game.world.input.setMouseY(me.getY());
			game.world.input.setMousePressed(pressed);
		}
	}
	
	public void setScreen(int screen) {
		// set
		this.screen = screen;
		// repaint
		drawScreen();
	}
	
	private void drawScreen() {
		RenderPanel pane = ((RenderPanel)getContentPane());
		pane.fixButtonHoverGlitch(); // jank
		
		// reset
		pane.repaint();
		pane.removeAll();
		
		// add screen elements accordingly
		switch (screen) {
			case SCREEN_MENU_MAIN:
				pane.add(btnPlay, 50, 200);
				pane.add(btnHelp, 50, 325);
				pane.add(btnExit, 50, 450);
				pane.add(bgMenu, 0, 0);
				break;
			case SCREEN_MENU_HELP:
				pane.add(btnBack, 50, 450);
				pane.add(bgHelp, 0, 0);
				break;
			case SCREEN_RUNNING_PLAYING:
				pane.add(game.renderer, 0, 0);
				break;
			case SCREEN_RUNNING_PAUSED:
				pane.add(btnBack, 50, 450);
				pane.add(bgPaused, 0, 0);
				break;
			default:
				setScreen(SCREEN_MENU_MAIN);
				break;
		}
	}
}