package ar.edu.unju.edm.services;


import ar.edu.unju.edm.model.Comment;
import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.model.Review;
import ar.edu.unju.edm.model.Usuario;

public interface UserService {

    public Usuario getUser(Integer ID);
    public Usuario getUser(String email);
    public void editUser(Usuario user);
    public void deleteUser(Integer ID);
    public Point getPoint(Integer ID);
    public void addComment(Comment comment);
    public void addReview(Review review);

}