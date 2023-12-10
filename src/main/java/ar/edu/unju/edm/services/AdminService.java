package ar.edu.unju.edm.services;

import java.util.List;


import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.model.Usuario;


public interface AdminService {

    public List<Usuario> getUserList();
    public List<Point> getPointList();

}
