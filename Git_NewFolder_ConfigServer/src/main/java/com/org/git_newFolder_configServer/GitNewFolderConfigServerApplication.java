package com.org.git_newFolder_configServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableConfigServer
@SpringBootApplication
public class GitNewFolderConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitNewFolderConfigServerApplication.class, args);
	}

}
