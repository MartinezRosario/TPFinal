package ar.edu.unju.edm.services;

import java.util.List;

import ar.edu.unju.edm.model.Point;

public interface StaffService {

    public List<Point> getPointList();
    public Point getPoint(Integer ID);
    public void addPoint(Point point);
    public void editPoint(Point point);
    public void deletePoint(Integer ID);

}