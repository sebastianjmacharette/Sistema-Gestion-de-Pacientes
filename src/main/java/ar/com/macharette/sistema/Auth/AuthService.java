package ar.com.macharette.sistema.Auth;

import ar.com.macharette.sistema.JWT.JwtService;
import ar.com.macharette.sistema.User.Role;
import ar.com.macharette.sistema.User.User;
import ar.com.macharette.sistema.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRerquest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .lastname(request.getLastname())
                .firstname(request.getFirstname())
                .Country(request.getCountry())
                .role(Role.USER)
                .build();
        //invocar al repositorio para que se cree un nuevo usuario
        userRepository.save(user);

        return  AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();




    }
}
