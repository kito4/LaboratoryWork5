package kito.lab5.client.user_command_line.Commands;

import kito.lab5.client.Config;
import kito.lab5.client.abstractions.AbstractCommand;
import kito.lab5.client.user_command_line.ErrorMessage;
import kito.lab5.client.user_command_line.SuccessMessage;

public class RemoveByID extends AbstractCommand {

    public RemoveByID() {
        super("remove_by_id", "Удалить человека из коллекции по его ID, принимает на вход [ID]", 1);
    }
    @Override
    public Object execute(String[] args) {
        if (args.length == getAMOUNT_OF_ARGS()) {
            try {
                int id = Integer.parseInt(args[0]);
                if (id <= Config.getCollectionManager().getLength() && id > 0) {
                    Config.getCollectionManager().removeHumanById(id);
                    return new SuccessMessage("Человек с ID " + id + " успешно удален");
                } else {
                    return new ErrorMessage("Человек с таким ID не найден");
                }
            } catch (NumberFormatException e) {
                return new ErrorMessage("Передано неправильное значение ID");
            }
        } else {
            return new ErrorMessage("Передано неверное количество аргументов");
        }
    }
}
