package org.ennva;

import org.ennva.githup.GithubClient;
import org.ennva.githup.RepositoryEvent;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class GithupHealthIndicator implements HealthIndicator {

	private final GithubClient githupClient;
	
	@Override
	public Health health() {
		try {
			ResponseEntity<RepositoryEvent[]> response = this.githupClient.fetchEvents("spring-projects", "spring-boot");
			if(response.getStatusCode().is2xxSuccessful()) {
				return Health.up().build();
			} else {
				return Health.down().build();
			}
			
		} catch (Exception e) {
			return Health.down(e).build();
		}
		
	}

}
