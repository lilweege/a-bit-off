package main.game;

/*
 * very helpful page concerning non-blocking game loop
 * https://stackoverflow.com/questions/13739693/java-swing-based-game-framework-any-advice
 * */

// threaded world and renderer updates
public class GameEngine {
	
	private static final int ONE_SECOND = 1000000000;
	
	private static final double GAME_FR = 60.;
	
	private static final double NS_BETW_UPDATES = ONE_SECOND / GAME_FR;

	private static final int MAX_UPDATES = 5;

	private static final double TARGET_FPS = 120.;
	
	private static final double TARGET_NS_BETW_RENDERS = ONE_SECOND / TARGET_FPS;

	private boolean running = false;
	
	private int frameCount = 0;
	
	public final GamePhysics world;
	
	public final GameRenderer renderer;
	
	// engine must contain world and renderer
	// because renderer has access to the world
	public GameEngine() {
		world = new GamePhysics();
		renderer = new GameRenderer(world);
	}
	
	public void pause() {
		running = false;
	}
	
	public void play() {
		running = true;
		runGameLoop();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void runGameLoop() {
		// new thread
		Thread loop = new Thread(new Runnable() {
			@Override
			public void run() {
				gameLoop();
			}
		});
		loop.start();
	}
	
	// run at fixed framerate
	private void gameLoop() {
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		
		while (running) {
			double now = System.nanoTime();
			
			// update
			int updateCnt = 0;
			while (updateCnt < MAX_UPDATES && now - lastUpdateTime > NS_BETW_UPDATES) {
				lastUpdateTime += NS_BETW_UPDATES;
				++updateCnt;
				update();
			}
			
			if (now - lastUpdateTime > NS_BETW_UPDATES)
				lastUpdateTime = now - NS_BETW_UPDATES;
			
			// render
			lastRenderTime = now;
			render();
			
			
			// yield
			while (now - lastRenderTime < TARGET_NS_BETW_RENDERS && now - lastUpdateTime < NS_BETW_UPDATES) {
				Thread.yield();
				try {
					Thread.sleep(1);
				}
				catch (Exception e) {
					e.printStackTrace(System.out);
				}
				now = System.nanoTime();
			}
		}
	}
	
	private void update() {
		world.update();
	}
	
	private void render() {
		world.setTime((int) (++frameCount / TARGET_FPS));
		renderer.repaint();
	}
}