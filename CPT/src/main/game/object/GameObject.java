package main.game.object;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Area;

// generic game object
public abstract class GameObject {
	
	private int x, y;
	
	protected Image image = null;
	
	protected Polygon hitbox = null;
	
	public void draw(Graphics2D g2d) {
		if (image != null)
			g2d.drawImage(image, getX() - image.getWidth(null)/2, getY() - image.getHeight(null)/2, null);
		else if (hitbox != null)
			g2d.draw(hitbox);
	}
	
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public GameObject(int x, int y, Image image) {
		this.x = x;
		this.y = y;
		this.image = image;
	}

	public GameObject(int x, int y, Image image, Polygon hitbox) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.hitbox = hitbox;
	}
	
	public boolean collidesWith(int pointX, int pointY) {
		if (hitbox == null)
			return false;
		return hitbox.contains(pointX, pointY);
	}
	
	public boolean collidesWith(GameObject other) {
		if (this.hitbox == null || other.hitbox == null)
			return false;
		Area intersection = new Area(this.hitbox);
		intersection.intersect(new Area(other.hitbox));
		return !intersection.isEmpty();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (hitbox != null)
			hitbox.translate(x - this.x, 0);
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (hitbox != null)
			hitbox.translate(0, y - this.y);
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Polygon getHitbox() {
		return hitbox;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setHitbox(Polygon hitbox) {
		this.hitbox = hitbox;
	}
}