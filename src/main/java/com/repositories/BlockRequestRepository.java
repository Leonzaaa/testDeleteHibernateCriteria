package com.repositories;

import com.model.BlockRequest;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class BlockRequestRepository implements PanacheRepository<BlockRequest> {

}
