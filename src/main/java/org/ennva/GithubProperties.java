package org.ennva;

import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "github")
@Validated
public class GithubProperties {
	
	/**
	 * Github API token ("user:sampletokentest")
	 * */
	@Pattern(regexp = "\\w+:\\w+")
	private String token;

}
