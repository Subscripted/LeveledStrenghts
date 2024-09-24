package dev.subscripted.leveledStrenghts.annotations;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommandAnnotation implements CommandExecutor, TabCompleter {

    private final Object commandClassInstance;

    public CommandAnnotation(Object commandClassInstance) {
        this.commandClassInstance = commandClassInstance;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        Method[] methods = commandClassInstance.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                // Überprüfen, ob die Signatur der Methode gültig ist
                if (!isValidCommandMethod(method)) {
                    throw new IllegalArgumentException("Method " + method.getName()
                            + " annotated with @Command does not match the required signature.");
                }

                if (command.name().equalsIgnoreCase(cmd.getName())) {
                    if (!command.permission().isEmpty() && !sender.hasPermission(command.permission())) {
                        sender.sendMessage("You don't have permission to execute this command.");
                        return true;
                    }
                    try {
                        method.invoke(commandClassInstance, sender, cmd, label, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // Methode zur Überprüfung der Methodensignatur
    private boolean isValidCommandMethod(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        // Prüfe, ob die Methode 4 Parameter hat und die richtige Signatur
        return parameterTypes.length == 4
                && CommandSender.class.isAssignableFrom(parameterTypes[0])
                && org.bukkit.command.Command.class.isAssignableFrom(parameterTypes[1])
                && String.class.isAssignableFrom(parameterTypes[2])
                && String[].class.isAssignableFrom(parameterTypes[3]);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] args) {
        Method[] methods = commandClassInstance.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                if (command.name().equalsIgnoreCase(cmd.getName()) && command.tabCompleter()) {
                    List<String> completions = new ArrayList<>();

                    for (TabCompleteArgument arg : command.tabCompletionArgs()) {
                        if (args.length - 1 == arg.argIndex()) {
                            if (arg.permission().isEmpty() || sender.hasPermission(arg.permission())) {
                                completions.add(arg.argText());
                            }
                        }

                        if (args.length > arg.argIndex() && args.length - 1 == arg.argIndex() + 1) {
                            if (args[arg.argIndex()].equals(arg.argText())) {
                                for (SubTabCompleteArgument subArg : arg.subTabCompletionArgs()) {
                                    if (subArg.permission().isEmpty() || sender.hasPermission(subArg.permission())) {
                                        completions.add(subArg.argText());
                                    }
                                }
                            }
                        }
                    }

                    return completions;
                }
            }
        }
        return List.of();
    }

    public String getCommandName() {
        Method[] methods = commandClassInstance.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Command.class)) {
                return method.getAnnotation(Command.class).name();
            }
        }
        return null;
    }
}
