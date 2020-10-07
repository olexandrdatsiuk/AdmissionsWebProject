package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request);
}

