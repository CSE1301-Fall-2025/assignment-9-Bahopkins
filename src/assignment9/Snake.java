package assignment9;

import java.util.LinkedList;

public class Snake {
	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<>();
		BodySegment head = new BodySegment(0.5, 0.5, SEGMENT_SIZE);
		segments.add(head);
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		for (int i = segments.size() - 1; i > 0; i--) {
			BodySegment prev = segments.get(i - 1);
			BodySegment curr = segments.get(i);
			curr.setX(prev.getX());
			curr.setY(prev.getY());
		}
		BodySegment head = segments.getFirst();
		head.setX(head.getX() + deltaX);
		head.setY(head.getY() + deltaY);
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment s : segments) {
        	s.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		double headRadius = SEGMENT_SIZE / 2.0;
		double foodRadius = f.getFoodSize();
		if (dist <= headRadius + foodRadius) {
			BodySegment tail = segments.getLast();
			segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInBounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		return (x >= 0 && x <= 1 && y >= 0 && y <= 1);
	}
}
