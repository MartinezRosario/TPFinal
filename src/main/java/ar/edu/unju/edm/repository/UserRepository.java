package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Usuario;


@Repository
public interface UserRepository extends CrudRepository<Usuario, Integer> {

    public List<Usuario> findAllByState(Boolean state);
    public Optional<Usuario> findByEmail(String email);

}