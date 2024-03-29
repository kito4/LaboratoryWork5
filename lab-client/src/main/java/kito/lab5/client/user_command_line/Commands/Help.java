package kito.lab5.client.user_command_line.Commands;

import kito.lab5.client.abstractions.AbstractCommand;
import kito.lab5.client.user_command_line.ErrorMessage;
import kito.lab5.client.user_command_line.SuccessMessage;

import java.util.List;
import java.util.stream.Collectors;

public class Help extends AbstractCommand {


    public Help() {
        super("help", "Вывести справку по доступным командам", 0);
    }

    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            List<AbstractCommand> listToReturn = getCommandsList();
            return new SuccessMessage(listToReturn.stream()
                    .map(AbstractCommand::getDescription)
                    .collect(Collectors.joining("\n")));
        } else {
            return new ErrorMessage("Передано неправильное количество аргументов");
        }
    }
}
