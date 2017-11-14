package com.dqr.messagerelay.configurations;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class ControllerResourceProcessor implements ResourceProcessor<RepositoryLinksResource> {

	@Override
	public RepositoryLinksResource process(RepositoryLinksResource resource) {
//		resource.add(ControllerLinkBuilder.linkTo(UserController.class).withRel("users"));
		return resource;
	}
}
