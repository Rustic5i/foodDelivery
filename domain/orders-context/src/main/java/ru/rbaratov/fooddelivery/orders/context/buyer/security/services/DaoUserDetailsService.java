package ru.rbaratov.fooddelivery.orders.context.buyer.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.ArigatoUserDetails;
import ru.rbaratov.fooddelivery.orders.context.domain.Buyer;
import ru.rbaratov.fooddelivery.orders.context.repository.BuyerRepository;
import ru.rbaratov.fooddelivery.orders.context.domain.valueobject.PhoneNumber;
import ru.rbaratov.fooddelivery.orders.context.buyer.security.Privilege;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DaoUserDetailsService.class);

    @Autowired
    private BuyerRepository buyerRepository;

    @Lazy
    @Autowired
    protected DaoUserDetailsService daoUserDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Попытка входа под пользователем {}", username);
        return daoUserDetailsService.getUserDetails(username);
    }

    @Transactional
    public ArigatoUserDetails getUserDetails(String username) {
        Optional<Buyer> currentUser = buyerRepository.findByPhoneNumber(new PhoneNumber(username)); //todo заменить потом Buyer на User
        if (currentUser.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format("Пользователь {0} не найден", username));
        }
        return createUserDetail(currentUser.get(), Arrays.asList(new Privilege("Buyer")));
    }

    private ArigatoUserDetails createUserDetail(Buyer currentUser, Collection<Privilege> userPrivileges) {
        ArigatoUserDetails uzedoUserPrincipal = new ArigatoUserDetails(
                userPrivileges,
                currentUser.isActive(),
                "$2a$12$UimqVbH1Kqb0x47aE70eluWSdNKTEmALljdieNnMzlMVyoRObFDAm",//todo 123
                currentUser.getPhoneNumber().value());
        return uzedoUserPrincipal;
    }
}
