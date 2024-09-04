package dave.command;

import dave.task.TaskList;
import dave.storage.Storage;
import dave.ui.Ui;

/**
 * Represents the command to exit the application.
 * This command outputs a farewell message and signals the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * This method outputs a farewell message to the user.
     *
     * @param tasks   The {@code TaskList} containing the tasks (not used in this command).
     * @param storage The {@code Storage} object to handle saving the task list (not used in this command).
     * @param ui      The {@code Ui} object to handle user interaction.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return {@code true} since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
