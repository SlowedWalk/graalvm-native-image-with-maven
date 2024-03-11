package tech.hidetora.graalvmnativeimage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.hidetora.graalvmnativeimage.entity.AppUser;
import tech.hidetora.graalvmnativeimage.entity.Authority;
import tech.hidetora.graalvmnativeimage.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        log.debug("loadUserByUsername {}", email);
        return userRepository.findByEmailIgnoreCase(email)
                .map(user -> createSpringSecurityUser(email, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));
    }

    private User createSpringSecurityUser(String lowercaseLogin, AppUser user) {
        if (!user.isEnabled()) {
            throw new RuntimeException("User " + lowercaseLogin + " was not activated");
        }
        log.debug("creating spring security user");
        List<SimpleGrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(Authority::getAuthority)
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
