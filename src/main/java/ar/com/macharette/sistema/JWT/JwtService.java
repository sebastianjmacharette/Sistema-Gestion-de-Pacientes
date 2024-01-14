package ar.com.macharette.sistema.JWT;

import ar.com.macharette.sistema.User.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY ="FSDFSDF54411754FDS8789444548544544454SDFFFFF211FFFF95FSDFF";
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    public String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //fecha de creacion, hora de sistema
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24)) //fecha de experacion sumado un dia
                .signWith(getKey(), SignatureAlgorithm.ES256)  //clave de encrptacion
                .compact() //crea el objeto y lo serializa

                ;
    }

    private Key getKey(){
        byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
