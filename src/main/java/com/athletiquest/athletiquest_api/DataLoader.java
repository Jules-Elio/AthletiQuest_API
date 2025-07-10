package com.athletiquest.athletiquest_api;

import com.athletiquest.athletiquest_api.dto.entity.Role;
import com.athletiquest.athletiquest_api.dto.repository.RoleRepository;
import com.athletiquest.athletiquest_api.dto.service.StadiumService;
import com.athletiquest.athletiquest_api.dto.service.StadiumsUpdateService;
import com.athletiquest.athletiquest_api.enums.RoleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StadiumService stadiumService;
    private final StadiumsUpdateService stadiumsUpdateService;
    private final RoleRepository roleRepository;

    @Value("${retrieve-stadiums-on-start}")
    private Boolean retrieveStadiumsOnStart;
    @Value("${retrieve-stadiums-on-start.if-days-since-last-update}")
    private Integer retrieveStadiumsOnStartIfDaysSinceLastUpdate;
    @Value("${retrieve-stadiums-on-start.if-collection-empty}")
    private Boolean retrieveStadiumsOnStartIfCollectionEmpty;

    @Override
    public void run(String... args) throws JsonProcessingException {
        ensureRoleTypes();
        updateStadiums();
    }

    private void ensureRoleTypes() {
        for (RoleType roleType : RoleType.values()) {
            Role entity = new Role(roleType);
            if (roleRepository.findByRoleType(roleType) == null) {
                roleRepository.save(entity);
            }
        }
    }

    private void updateStadiums() throws JsonProcessingException {
        if ((Boolean.TRUE.equals(retrieveStadiumsOnStartIfCollectionEmpty) && stadiumService.getStadiumsCount() == 0) ||
            (retrieveStadiumsOnStartIfDaysSinceLastUpdate != null &&
             stadiumsUpdateService.isLastUpdateBeforeXDays(retrieveStadiumsOnStartIfDaysSinceLastUpdate)) ||
            Boolean.TRUE.equals(retrieveStadiumsOnStart)) {
            log.atInfo().log("Updating stadiums into MongoDB");

            if (stadiumService.retrieveStadiumsFromGouvAPI()) {
                log.atInfo().log("Stadiums in MongoDB updated");
            } else {
                log.atError().log("Error updating stadiums into MongoDB");
            }
        }
    }
}
