package com.ryo33.z2d.util;

public class Quad {

	Point[] points;

	public Quad(Point a, Point b, Point c, Point d) {
		points[0] = a;
		points[1] = b;
		points[2] = c;
		points[3] = d;
	}

	public Quad() {
		this(new Point(), new Point(), new Point(), new Point());
	}

	public PointPair getBoundingBox() {
		float minX, minY, maxX, maxY;
		minX = maxX = points[0].x;
		minY = maxY = points[0].y;
		for (int i = 1; i < 4; i++) {
			if (points[i].x < minX) {
				minX = points[i].x;
			} else if (points[i].x > maxX) {
				maxX = points[i].x;
			}
			if (points[i].y < minY) {
				minY = points[i].y;
			} else if (points[i].y > maxY) {
				maxY = points[i].y;
			}
		}
		return new PointPair(minX, minY, maxX, maxY); 
	}

}
