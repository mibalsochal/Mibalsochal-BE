package com.mibalsochal.dopame_be.domain.user.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findBySub(String sub);
}
