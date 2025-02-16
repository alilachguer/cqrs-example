package com.techbank.account.cmd;

import com.techbank.account.cmd.api.commands.*;
import com.techbank.cqrs.core.infrastructure.CommandDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;
	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, command -> commandHandler.handle((OpenAccountCommand) command));
		commandDispatcher.registerHandler(DepositFundsCommand.class, command -> commandHandler.handle((DepositFundsCommand) command));
		commandDispatcher.registerHandler(WithdrawFundsCommand.class, command -> commandHandler.handle((WithdrawFundsCommand) command));
		commandDispatcher.registerHandler(CloseAccountCommand.class, command -> commandHandler.handle((CloseAccountCommand) command));
		commandDispatcher.registerHandler(RestoreReadDbCommand.class, command -> commandHandler.handle((RestoreReadDbCommand) command));
	}
}
