package kz.myproject.onlinePay.security;

import io.jsonwebtoken.Claims;
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
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "2B4B6250655368566D5970337336763979244226452948404D635166546A576E";
    public String extractUsername(String jwtToken) {

        return extractClaim(jwtToken, Claims::getSubject); //subject is email user

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims); // extract извлекать
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // username -- email -- subject
                .setIssuedAt(new Date(System.currentTimeMillis())) // this will help to check expiration date is valid or not
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // token live - 60 min (1000 milli * 60milli = 60 sec)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact(); // will generate and return a token
    }

    public boolean isTokenValid(String token, UserDetails userDetails) { // TODO throws JwtTokenHasExpired 
        final String username = extractUsername(token);
        if (!((username.equals(userDetails.getUsername())) && !isTokenExpired(token)))
            //throw new JwtTokenHasExpired("Jwt token is not valid");
            System.out.println("Jwt token is not valid");
        return true;
    }

    private boolean isTokenExpired(String token)  { // TODO throws JwtTokenHasExpired
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey()) // secret key
                    .build()
                    .parseClaimsJws(token)
                    .getBody(); // get all claims from token

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); //todo decode 
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
