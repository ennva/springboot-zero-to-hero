package org.ennva.events;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubProject implements Serializable {

	private static final long serialVersionUID = 7092468006657109484L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String orgName;
	
	@Column(unique = true)
	private String repoName;

}
