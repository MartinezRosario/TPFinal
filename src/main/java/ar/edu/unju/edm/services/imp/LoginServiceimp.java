package ar.edu.unju.edm.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UserRepository;
import ar.edu.unju.edm.services.LoginService;


@Service
public class LoginServiceimp implements UserDetailsService,LoginService{

  @Autowired
  UserRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    //busqueda del usuario
    Usuario usuarioenc = usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Usuario invalido"));
    //definir autorizaciones
    List<GrantedAuthority> estados= new ArrayList<>();
    GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(usuarioenc.getType());
    estados.add(grantedAuthority);
    //Definir usuario en sesion
    UserDetails userEnSesion = new User(email,usuarioenc.getPassword(),estados);
    return userEnSesion;
  }
  @Override
    public void addUser(Usuario user) {
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        user.setPassword(encoder.encode(password));
        usuarioRepository.save(user);
    }
}
