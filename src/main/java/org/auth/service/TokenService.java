package org.auth.service;

import io.quarkus.logging.Log;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.auth.common.exception.CustomException;
import org.auth.persistence.entity.UserEntity;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenService {

    @Inject
    JWTParser jwtParser;

    @ConfigProperty(name = "smallrye.jwt.time-to-live")
    int timeExpires;

    public Uni<String> generate(UserEntity user, String uuid) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
                String token = Jwt
                        .subject(user.getUsername())
                        .upn(user.getEmail())
                        .claim(Claims.phone_number, user.getPhone())
                        .claim(Claims.birthdate.name(), user.getBirthDate())
                        .groups(new HashSet<>(Arrays.asList(user.getRole())))
                        .expiresAt(Instant.now().getEpochSecond() + timeExpires)
                        .sign();
                return token;
            } catch (Exception e) {
                Log.error("UUID: " + uuid + " ::: Error al crear el Token ::: ", e);
                throw new CustomException("", uuid, "", e);
            }
        }));
    }

    public Uni<String> refresh(String token, String uuid) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
                JsonWebToken jwtOld = jwtParser.parse(token);

                String tokenNew = Jwt
                        .subject(jwtOld.getSubject())
                        .upn(jwtOld.getClaim(Claims.upn))
                        .claim(Claims.phone_number.name(), jwtOld.getClaim(Claims.phone_number))
                        .claim(Claims.birthdate.name(), jwtOld.getClaim(Claims.birthdate))
                        .groups(jwtOld.getGroups())
                        .expiresAt(Instant.now().getEpochSecond() + timeExpires)
                        .sign();
                return tokenNew;
            } catch (ParseException e) {
                Log.error("UUID: " + uuid + " ::: Error al hacer el Refresh Token ::: ", e);
                throw new CustomException("", uuid, "", e);
            }
        }));
    }

}
