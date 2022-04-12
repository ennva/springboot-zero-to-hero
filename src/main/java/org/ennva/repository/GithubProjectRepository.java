package org.ennva.repository;

import org.ennva.events.GithubProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GithubProjectRepository extends JpaRepository<GithubProject, Long> {

	GithubProject findByRepoName(String repoName);
}
