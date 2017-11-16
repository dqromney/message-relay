package com.dqr.messagerelay.repository;

import com.dqr.messagerelay.models.Message;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

}
