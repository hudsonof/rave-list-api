package br.com.ravelist.repository;

import br.com.ravelist.util.token.*;
import br.com.ravelist.model.RefreshToken;
import br.com.ravelist.model.Role;
import br.com.ravelist.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Tests for RefreshTokenRepository")
public class RefreshTokenRepositoryTest {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @DisplayName("findByToken Returns Refresh Token When Successful")
    void findByToken_ReturnsRefreshToken_WhenSuccessful() {
        User userToBeSave = RefreshTokenCreator.createRefreshTokenToBeSave().getUser();

        List<Role> roles = roleRepository.saveAll(userToBeSave.getRoles());

        userToBeSave.setRoles(new HashSet<>(roles));

        User userSaved = userRepository.save(userToBeSave);

        RefreshToken tokenToBeSave = RefreshTokenCreator.createRefreshTokenToBeSave();

        tokenToBeSave.setUser(userSaved);

        RefreshToken tokenSaved = refreshTokenRepository.save(tokenToBeSave);

        Optional<RefreshToken> tokenFound = refreshTokenRepository.findByToken(tokenSaved.getToken());

        assertThat(tokenFound).isNotEmpty();

        assertThat(tokenFound.get()).isNotNull();

        assertThat(tokenFound.get().getToken()).isEqualTo(RefreshTokenCreator.createRefreshTokenToBeSave().getToken());
    }

    @Test
    @DisplayName("deleteByUser Removes Refresh Token When Successful")
    void deleteByUser_RemovesRefreshToken_WhenSuccessful() {
        User userToBeSave = RefreshTokenCreator.createRefreshTokenToBeSave().getUser();

        List<Role> roles = roleRepository.saveAll(userToBeSave.getRoles());

        userToBeSave.setRoles(new HashSet<>(roles));

        User userSaved = userRepository.save(userToBeSave);

        RefreshToken tokenToBeSave = RefreshTokenCreator.createRefreshTokenToBeSave();

        tokenToBeSave.setUser(userSaved);

        refreshTokenRepository.save(tokenToBeSave);

        refreshTokenRepository.deleteByUser(userSaved);

        long totalOfUsers = refreshTokenRepository.count();

        assertThat(totalOfUsers).isEqualTo(0);
    }

}
