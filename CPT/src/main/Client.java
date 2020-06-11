package main;
import javax.swing.SwingUtilities;

import main.window.GameFrame;


/*
 * Created by: Luigi Quattrociocchi and Ahmed El Ashmawy
 * Date Submitted: 9th June 2020
 * Assignment: ICS4U CPT
 * Description: "A bit off" is an educational game about binary arithmetic and operations.
 */

// entry point
public class Client {
	
	public static void main(String[] args) {
		
		// create game in a thread
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame();
            }
        });
	}
	
}