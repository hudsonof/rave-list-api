package br.com.ravelist.util.token;

import br.com.ravelist.model.RefreshToken;
import br.com.ravelist.util.user.UserCreator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RefreshTokenCreator {

    public static RefreshToken createRefreshTokenToBeSave() {
        return RefreshToken
                .builder()
                .id(1)
                .token("token-test")
                .expiryDate(Instant.now().plus(1, ChronoUnit.HOURS))
                .user(UserCreator.createUserToBeSave())
                .build();
    }

}
