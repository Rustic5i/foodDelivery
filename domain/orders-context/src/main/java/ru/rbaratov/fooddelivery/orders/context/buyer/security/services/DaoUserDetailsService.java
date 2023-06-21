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
import ru.rbaratov.fooddelivery.orders.context.entity.BuyerEntity;
import ru.rbaratov.fooddelivery.orders.context.repository.BuyerEntityRepository;
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
    private BuyerEntityRepository buyerEntityRepository;

    @Lazy
    @Autowired
    protected DaoUserDetailsService daoUserDetailsService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        LOGGER.info("Попытка входа под пользователем {}", phoneNumber);
        return daoUserDetailsService.getUserDetails(phoneNumber);
    }

    @Transactional
    public ArigatoUserDetails getUserDetails(String phoneNumber) {
        String validPhoneNumber = new PhoneNumber(phoneNumber).value();
        Optional<BuyerEntity> currentUser = buyerEntityRepository.findByPhoneNumber(validPhoneNumber); //todo заменить потом Buyer на User
        if (currentUser.isEmpty()) {
            throw new UsernameNotFoundException(MessageFormat.format("Пользователь {0} не найден", phoneNumber));
        }
        return createUserDetail(currentUser.get(), Arrays.asList(new Privilege("Buyer")));
    }

    private ArigatoUserDetails createUserDetail(BuyerEntity currentUser, Collection<Privilege> userPrivileges) {
        ArigatoUserDetails uzedoUserPrincipal = new ArigatoUserDetails(
                userPrivileges,
                currentUser.isActive(),
                "$2a$12$UimqVbH1Kqb0x47aE70eluWSdNKTEmALljdieNnMzlMVyoRObFDAm",//todo 123
                currentUser.getPhoneNumber());
        return uzedoUserPrincipal;
    }
}
