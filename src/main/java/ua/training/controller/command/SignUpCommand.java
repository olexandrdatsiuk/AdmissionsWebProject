package ua.training.controller.command;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("SignUpCommand.execute");
        return "/signup.jsp";
    }
}
