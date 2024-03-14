package org.poo.cb.Classes.Commands;

import org.poo.cb.Interfaces.CommandInterface;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private final List<CommandInterface> eBankOperations = new ArrayList<CommandInterface>();

    public void addOperation(CommandInterface command) {
        eBankOperations.add(command);
    }

    public void executeOperations() {
        for (CommandInterface o : eBankOperations) {
            o.execute();
        }
    }
}
