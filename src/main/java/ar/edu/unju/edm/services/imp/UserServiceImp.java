package ar.edu.unju.edm.services.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Comment;
import ar.edu.unju.edm.model.Point;
import ar.edu.unju.edm.model.Review;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.CommentRepository;
import ar.edu.unju.edm.repository.PointRepository;
import ar.edu.unju.edm.repository.ReviewRepository;
import ar.edu.unju.edm.repository.UserRepository;
import ar.edu.unju.edm.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository USER_DATABASE;
    private final PointRepository POINT_DATABASE;
    private final CommentRepository COMMENT_DATABASE;
    private final ReviewRepository REVIEW_DATABASE;

    public UserServiceImp(UserRepository USER_DATABASE, PointRepository POINT_DATABASE, CommentRepository COMMENT_DATABASE, ReviewRepository REVIEW_DATABASE) {
        this.USER_DATABASE = USER_DATABASE;
        this.POINT_DATABASE = POINT_DATABASE;
        this.COMMENT_DATABASE = COMMENT_DATABASE;
        this.REVIEW_DATABASE = REVIEW_DATABASE;
    }

    @Autowired
    Usuario user;

    @Override
    public Usuario getUser(Integer ID) {
        user = USER_DATABASE.findById(ID).get();
        return user;
    }

    @Override
    public Usuario getUser(String email) {
        user = USER_DATABASE.findByEmail(email).get();
        return user;
    }

    @Override
    public void editUser(Usuario user) {
        this.user.setName(user.getName());
        this.user.setLastName(user.getLastName());
        this.user.setEmail(user.getEmail());
        this.user.setPassword(user.getPassword());
        this.user.setNationality(user.getNationality());
        this.user.setType(user.getType());
        String password = this.user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        this.user.setPassword(encoder.encode(password));
        USER_DATABASE.save(this.user);
    }

    @Override
    public void deleteUser(Integer ID) {
        user = USER_DATABASE.findById(ID).get();
        user.setState(false);
        USER_DATABASE.save(user);
    }

    @Override
    public Point getPoint(Integer ID) {
        List<Review> reviews = REVIEW_DATABASE.findByPointID(ID);
        Optional<Point> optionalPoint = POINT_DATABASE.findById(ID);
        if (!reviews.isEmpty()) {
            int totalReviews = reviews.size();
            int sum = 0;

            for (Review review : reviews) {
                sum += review.getReview();
            }

            float average = (float) sum / totalReviews;
            if (optionalPoint.isPresent()) {
                Point point = optionalPoint.get();
                point.setRating(average);
                POINT_DATABASE.save(point);
            }
        }
        return optionalPoint.get();
    }

    @Override
    public void addComment(Comment comment) {
        COMMENT_DATABASE.save(comment);
    }

    @Override
    public void addReview(Review review) {
        REVIEW_DATABASE.save(review);
    }

}