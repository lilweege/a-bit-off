package main.assets;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

// static factory class manage all assets
public class AssetFactory {
    
	private /*do not allow instances*/ AssetFactory() {}
    
	public static final int BUTTON_PLAY = 0;
	
	public static final int BUTTON_HELP = 1;
	
	public static final int BUTTON_EXIT = 2;
	
	public static final int BUTTON_BACK = 3;
	
	public static final int BUTTON_MENU = 4;
	
	public static final int BACKGROUND_MENU = 5;
	
	public static final int BACKGROUND_HELP = 6;
	
	public static final int BACKGROUND_PLAYING = 7;
	
	public static final int BACKGROUND_PAUSED = 8;
	
	public static final int BACKGROUND_FINISHED = 9;
	
	public static final int SOUND_SFX_BLIP_ONE = 10;
	
	public static final int SOUND_SFX_BLIP_TWO = 11;
	
	public static final int SOUND_SFX_BLIP_THREE = 12;

	public static final int SOUND_BGM_SONG_ONE = 13;
	
	public static final int SOUND_BGM_SONG_TWO = 14;
	
	
	public static final Cursor CURSOR_POINTER = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(AssetFactory.class.getResource("res/mouse/pointer.png")).getImage(), new Point(0, 0), "cusorPointer");
	
	public static final Cursor CURSOR_HAND = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(AssetFactory.class.getResource("res/mouse/hand.png")).getImage(), new Point(8, 0), "cursorHand");
	
	public static final ImageIcon IMAGE_BUTTON_PLAY = new ImageIcon(AssetFactory.class.getResource("res/btn/play.png"));
	
	public static final ImageIcon IMAGE_BUTTON_PLAY_HOVER = new ImageIcon(AssetFactory.class.getResource("res/btn/playh.png"));
	
	public static final ImageIcon IMAGE_BUTTON_HELP = new ImageIcon(AssetFactory.class.getResource("res/btn/help.png"));
	
	public static final ImageIcon IMAGE_BUTTON_HELP_HOVER = new ImageIcon(AssetFactory.class.getResource("res/btn/helph.png"));
	
	public static final ImageIcon IMAGE_BUTTON_EXIT = new ImageIcon(AssetFactory.class.getResource("res/btn/exit.png"));
	
	public static final ImageIcon IMAGE_BUTTON_EXIT_HOVER = new ImageIcon(AssetFactory.class.getResource("res/btn/exith.png"));
	
	public static final ImageIcon IMAGE_BUTTON_BACK = new ImageIcon(AssetFactory.class.getResource("res/btn/back.png"));
	
	public static final ImageIcon IMAGE_BUTTON_BACK_HOVER = new ImageIcon(AssetFactory.class.getResource("res/btn/backh.png"));
	
	public static final ImageIcon IMAGE_BUTTON_MENU = new ImageIcon(AssetFactory.class.getResource("res/btn/menu.png"));
	
	public static final ImageIcon IMAGE_BUTTON_MENU_HOVER = new ImageIcon(AssetFactory.class.getResource("res/btn/menuh.png"));
	
	public static final ImageIcon IMAGE_BACKGROUND_MENU = new ImageIcon(AssetFactory.class.getResource("res/bg/menu.png"));
	
	public static final ImageIcon IMAGE_BACKGROUND_HELP = new ImageIcon(AssetFactory.class.getResource("res/bg/help.png"));
	
	public static final ImageIcon IMAGE_BACKGROUND_PLAYING = new ImageIcon(AssetFactory.class.getResource("res/bg/playing.png"));
	
	public static final ImageIcon IMAGE_BACKGROUND_PAUSED = new ImageIcon(AssetFactory.class.getResource("res/bg/paused.png"));
	
	public static final ImageIcon IMAGE_BACKGROUND_FINISHED = new ImageIcon(AssetFactory.class.getResource("res/bg/finished.png"));
	
	public static final ImageIcon GAME_ASSET_HINT_FRAME = new ImageIcon(AssetFactory.class.getResource("res/game/hint.png"));
	
	public static final ImageIcon GAME_ASSET_CHARACTER_SET = new ImageIcon(AssetFactory.class.getResource("res/game/charset.png"));
	
	public static final ImageIcon GAME_ASSET_BIT_UNSET = new ImageIcon(AssetFactory.class.getResource("res/game/bit0.png"));
	
	public static final ImageIcon GAME_ASSET_BIT_SET = new ImageIcon(AssetFactory.class.getResource("res/game/bit1.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_NOT = new ImageIcon(AssetFactory.class.getResource("res/game/not.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_AND = new ImageIcon(AssetFactory.class.getResource("res/game/and.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_OR = new ImageIcon(AssetFactory.class.getResource("res/game/or.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_XOR = new ImageIcon(AssetFactory.class.getResource("res/game/xor.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_LEFT = new ImageIcon(AssetFactory.class.getResource("res/game/left.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_RIGHT = new ImageIcon(AssetFactory.class.getResource("res/game/right.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_PLUS = new ImageIcon(AssetFactory.class.getResource("res/game/plus.png"));
	
	public static final ImageIcon GAME_ASSET_OPERATOR_MINUS = new ImageIcon(AssetFactory.class.getResource("res/game/minus.png"));
	
	public static final URL SFX_BLIP_ONE_URL = AssetFactory.class.getResource("res/sfx/blip1.wav");
	
	public static final URL SFX_BLIP_TWO_URL = AssetFactory.class.getResource("res/sfx/blip2.wav");
	
	public static final URL SFX_BLIP_THREE_URL = AssetFactory.class.getResource("res/sfx/blip3.wav");

	public static final URL BGM_SONG_ONE_URL = AssetFactory.class.getResource("res/bgm/adiantum.wav");
	
	public static final URL BGM_SONG_TWO_URL = AssetFactory.class.getResource("res/bgm/futsal.wav");
	
	
	
	public static void playSound(int sound, double volume) {
		playSound(sound, volume, 0);
	}
	
	public static void playSound(int sound, double volume, int numLoops) {
		URL url;
		switch (sound) {
			case SOUND_BGM_SONG_ONE:
				url = BGM_SONG_ONE_URL;
				break;
			case SOUND_BGM_SONG_TWO:
				url = BGM_SONG_TWO_URL;
				break;
			case SOUND_SFX_BLIP_ONE:
				url = SFX_BLIP_ONE_URL;
				break;
			case SOUND_SFX_BLIP_TWO:
				url = SFX_BLIP_TWO_URL;
				break;
			case SOUND_SFX_BLIP_THREE:
				url = SFX_BLIP_THREE_URL;
				break;
			default:
				return;
		}
		try {
		    AudioInputStream stream = AudioSystem.getAudioInputStream(url);
		    Clip clip = AudioSystem.getClip();
		    clip.open(stream);
		    clip.loop(numLoops);
		    FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		    gain.setValue(20f * (float) Math.log10(volume));
		    clip.start();
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
    public static JLabel createBackground(int type) {
    	JLabel lbl = new JLabel();
    	switch (type) {
			case BACKGROUND_MENU:
				lbl.setIcon(AssetFactory.IMAGE_BACKGROUND_MENU);
				break;
			case BACKGROUND_HELP:
				lbl.setIcon(AssetFactory.IMAGE_BACKGROUND_HELP);
				break;
			case BACKGROUND_PLAYING:
				lbl.setIcon(AssetFactory.IMAGE_BACKGROUND_PLAYING);
				break;
			case BACKGROUND_PAUSED:
				lbl.setIcon(AssetFactory.IMAGE_BACKGROUND_PAUSED);
				break;
			case BACKGROUND_FINISHED:
				lbl.setIcon(AssetFactory.IMAGE_BACKGROUND_FINISHED);
				break;
    		default:
    			lbl.setText("invalid type");
    			break;
    	}
    	return lbl;
    }
    
    private static void mouseHover(JButton btn, ImageIcon def, ImageIcon hov) {
    	btn.setIcon(def);
    	btn.addMouseListener(new MouseAdapter() {
    		
    		public void mouseExited(MouseEvent e) {
    			btn.setIcon(def);
    		}
    	
    	    public void mouseEntered(MouseEvent e) {
    	    	btn.setIcon(hov);
    	    	playSound(SOUND_SFX_BLIP_THREE, 0.5);
    	    }
    	});
    }
    
    public static JButton createButton(int type, ActionListener l) {
    	JButton btn = new JButton();
    	btn.setBorder(null);
    	btn.setOpaque(false);
    	btn.setContentAreaFilled(false);
    	btn.setCursor(CURSOR_HAND);
    	btn.addActionListener(l);
    	btn.addActionListener(x -> playSound(SOUND_SFX_BLIP_ONE, 0.5));
	    switch (type) {
	    	case AssetFactory.BUTTON_PLAY:
	        	mouseHover(btn, AssetFactory.IMAGE_BUTTON_PLAY, AssetFactory.IMAGE_BUTTON_PLAY_HOVER);
	    		break;
	    	case AssetFactory.BUTTON_HELP:
	        	mouseHover(btn, AssetFactory.IMAGE_BUTTON_HELP, AssetFactory.IMAGE_BUTTON_HELP_HOVER);
	    		break;
	    	case AssetFactory.BUTTON_EXIT:
	        	mouseHover(btn, AssetFactory.IMAGE_BUTTON_EXIT, AssetFactory.IMAGE_BUTTON_EXIT_HOVER);
	    		break;
	    	case AssetFactory.BUTTON_BACK:
	        	mouseHover(btn, AssetFactory.IMAGE_BUTTON_BACK, AssetFactory.IMAGE_BUTTON_BACK_HOVER);
	    		break;
	    	case AssetFactory.BUTTON_MENU:
	        	mouseHover(btn, AssetFactory.IMAGE_BUTTON_MENU, AssetFactory.IMAGE_BUTTON_MENU_HOVER);
	    		break;
    		default:
    			btn.setText("invalid type");
    			break;
    	}
    	return btn;
    }
}
