package br.com.ravelist.util.user;

import br.com.ravelist.model.ERole;
import br.com.ravelist.model.Role;
import br.com.ravelist.model.User;

import java.util.Set;

public class UserCreator {

    public static User createUserToBeSave() {
        return User
                .builder()
                .id(1L)
                .username("SkyG0D")
                .email("test@mail.com")
                .password("password")
                .roles(Set.of(new Role(ERole.ROLE_USER)))
                .build();
    }

}
