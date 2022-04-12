package org.ennva.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.ennva.events.DashboardEntry;
import org.ennva.events.GithubProject;
import org.ennva.githup.GithubClient;
import org.ennva.githup.RepositoryEvent;
import org.ennva.repository.GithubProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class EventsController {

	private final GithubProjectRepository repository;
	private final GithubClient githubClient;

	@GetMapping("/events/{repoName}")
	@ResponseBody
	public RepositoryEvent[] fectEvents(@PathVariable String repoName) {
		GithubProject project = repository.findByRepoName(repoName);

		return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
	}

	@GetMapping("/")
	public String dashboard(Model model) {

		Iterable<GithubProject> projects = this.repository.findAll();

		List<DashboardEntry> entries = StreamSupport.stream(projects.spliterator(), true)
				.map(p -> new DashboardEntry(p, this.githubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
				.collect(Collectors.toList());

		model.addAttribute("entries", entries);
		return "dashboard";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("projects", repository.findAll());
		return "admin";
	}
}
