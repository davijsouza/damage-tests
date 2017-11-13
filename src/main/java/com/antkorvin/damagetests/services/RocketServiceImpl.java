package com.antkorvin.damagetests.services;

import com.antkorvin.damagetests.errorinfos.RocketServiceErrorInfo;
import com.antkorvin.damagetests.exceptions.NotFoundException;
import com.antkorvin.damagetests.models.Rocket;
import com.antkorvin.damagetests.repositories.RocketRepository;
import com.antkorvin.damagetests.utills.Guard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.antkorvin.damagetests.errorinfos.RocketServiceErrorInfo.ROCKET_NOT_FOUND;


/**
 * Created by Korovin Anatolii on 11.11.17.
 *
 * @author Korovin Anatolii
 * @version 1.0
 */
@Service
public class RocketServiceImpl implements RocketService {

    private final RocketRepository rocketRepository;

    @Autowired
    public RocketServiceImpl(RocketRepository rocketRepository) {
        this.rocketRepository = rocketRepository;
    }

    @Override
    public Rocket create(String name) {
        return rocketRepository.save(Rocket.builder()
                                           .name(name)
                                           .launchCode(generateCode())
                                           .build());
    }

    @Override
    public Rocket get(UUID id) {
        return rocketRepository.findById(id)
                               .orElseThrow(() -> new NotFoundException(ROCKET_NOT_FOUND));
    }

    private String generateCode() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}
