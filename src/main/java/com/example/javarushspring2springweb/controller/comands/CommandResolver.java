package com.example.javarushspring2springweb.controller.comands;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class CommandResolver {

    private Map<String, Command> commands;

    public String resolveCommand(HttpServletRequest request) {
        String commandName = extractCommandFromUri(request.getRequestURI());
        Command commandBean = commands.get(commandName);
        return commandBean.doGet(request);
    }

    private String extractCommandFromUri(String uri) {
        uri = uri.replaceFirst(".+?://", "");
        uri = uri.replaceFirst("\\?.*", "");
        String[] segments = uri.split("/");
        if (segments.length > 1) {
            return segments[1].replace("-", "_").toLowerCase();
        } else return "";
    }

    public void fillContextMap(ApplicationContext applicationContext) {
        commands = Map.of(
                "", applicationContext.getBean(IndexCommand.class),
                "users", applicationContext.getBean(UsersCommand.class),
                "user", applicationContext.getBean(UserCommand.class),
                "orders", applicationContext.getBean(OrdersCommand.class),
                "order", applicationContext.getBean(OrderCommand.class),
                "products", applicationContext.getBean(ProductsCommand.class),
                "product", applicationContext.getBean(ProductCommand.class)
        );
    }
}
