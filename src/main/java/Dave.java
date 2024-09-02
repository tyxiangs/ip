import java.io.IOException;

public class Dave {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dave(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                if (c == null) { continue; }
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showLine();
                System.out.println("An error occurred while trying to write to the file: " + e.getMessage());
                e.printStackTrace();
            }
            finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Dave("C:\\Users\\thamy\\OneDrive\\data\\daveData.txt").run();
    }
}
