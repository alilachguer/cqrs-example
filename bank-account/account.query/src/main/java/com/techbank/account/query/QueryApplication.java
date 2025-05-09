package com.techbank.account.query;

import com.techbank.account.query.api.queries.*;
import com.techbank.cqrs.core.infrastructure.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	QueryDispatcher queryDispatcher;

	@Autowired
	QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountsByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountsWithBalanceQuery.class, queryHandler::handle);
	}
}
