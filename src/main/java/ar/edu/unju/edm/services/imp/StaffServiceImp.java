package ar.edu.unju.edm.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.repository.PointRepository;
import ar.edu.unju.edm.services.StaffService;

import java.util.List;

@Service
public class StaffServiceImp implements StaffService {

    private final PointRepository POINT_DATABASE;

    public StaffServiceImp(PointRepository POINT_DATABASE) {
        this.POINT_DATABASE = POINT_DATABASE;
    }

    @Autowired
    Point point;

    @Override
    public List<Point> getPointList() {
        return POINT_DATABASE.findAllByState(true);
    }

    @Override
    public Point getPoint(Integer ID) {
        point = POINT_DATABASE.findById(ID).get();
        return point;
    }

    @Override
    public void addPoint(Point point) {
        POINT_DATABASE.save(point);
    }

    @Override
    public void editPoint(Point point) {
        this.point.setName(point.getName());
        this.point.setDescription(point.getDescription());
        this.point.setLocate(point.getLocate());
        this.point.setType(point.getType());
        POINT_DATABASE.save(this.point);
    }

    @Override
    public void deletePoint(Integer ID) {
        point = POINT_DATABASE.findById(ID).get();
        point.setState(false);
        POINT_DATABASE.save(point);
    }

}