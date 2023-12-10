package ar.edu.unju.edm.services.imp;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.repository.PointRepository;
import ar.edu.unju.edm.services.AppService;

import java.util.List;

@Service
public class AppServiceImp implements AppService {

    private final PointRepository POINT_DATABASE;

    public AppServiceImp(PointRepository POINT_DATABASE) {
        this.POINT_DATABASE = POINT_DATABASE;
    }

    @Override
    public List<Point> getPointList() {
        return POINT_DATABASE.findAllByState(true);
    }

}