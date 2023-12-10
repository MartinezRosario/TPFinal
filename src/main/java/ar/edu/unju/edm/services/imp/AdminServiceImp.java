package ar.edu.unju.edm.services.imp;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.PointRepository;
import ar.edu.unju.edm.repository.UserRepository;
import ar.edu.unju.edm.services.AdminService;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    private final UserRepository USER_DATABASE;
    private final PointRepository POINT_DATABASE;

    public AdminServiceImp(UserRepository USER_DATABASE, PointRepository POINT_DATABASE) {
        this.USER_DATABASE = USER_DATABASE;
        this.POINT_DATABASE = POINT_DATABASE;
    }

    @Override
    public List<Usuario> getUserList() {
        return USER_DATABASE.findAllByState(true);
    }

    @Override
    public List<Point> getPointList() {
        return POINT_DATABASE.findAllByState(true);
    }

}