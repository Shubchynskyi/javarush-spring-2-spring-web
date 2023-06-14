package com.example.javarushspring2springweb.lesson8_jpa_commands.controller.comands;

import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.Command;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.IndexCommand;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.OrderCommand;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.OrdersCommand;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.ProductCommand;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.ProductsCommand;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.UserCommand;
import com.example.javarushspring2springweb.lesson8_jpa.controller.comands.UsersCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class CommandResolver {

    private com.example.javarushspring2springweb.lesson8_jpa.controller.comands.IndexCommand indexCommand;
    private com.example.javarushspring2springweb.lesson8_jpa.controller.comands.UsersCommand usersCommand;
    private com.example.javarushspring2springweb.lesson8_jpa.controller.comands.UserCommand userCommand;
    private com.example.javarushspring2springweb.lesson8_jpa.controller.comands.OrdersCommand ordersCommand;
    private com.example.javarushspring2springweb.lesson8_jpa.controller.comands.OrderCommand orderCommand;
    private ProductsCommand productsCommand;
    private com.example.javarushspring2springweb.lesson8_jpa.controller.comands.ProductCommand productCommand;

    @Autowired
    public void setIndexCommand(IndexCommand indexCommand) {
        this.indexCommand = indexCommand;
    }

    @Autowired
    public void setUsersCommand(UsersCommand usersCommand) {
        this.usersCommand = usersCommand;
    }
    @Autowired
    public void setUserCommand(UserCommand userCommand) {
        this.userCommand = userCommand;
    }
    @Autowired
    public void setOrdersCommand(OrdersCommand ordersCommand) {
        this.ordersCommand = ordersCommand;
    }
    @Autowired
    public void setOrderCommand(OrderCommand orderCommand) {
        this.orderCommand = orderCommand;
    }
    @Autowired
    public void setProductsCommand(ProductsCommand productsCommand) {
        this.productsCommand = productsCommand;
    }
    @Autowired
    public void setProductCommand(ProductCommand productCommand) {
        this.productCommand = productCommand;
    }
    @Autowired
    public void setCommands(Map<String, com.example.javarushspring2springweb.lesson8_jpa.controller.comands.Command> commands) {
        this.commands = commands;
    }

    private Map<String, com.example.javarushspring2springweb.lesson8_jpa.controller.comands.Command> commands;

    public String resolveCommand(HttpServletRequest request) {
        String commandName = extractCommandFromUri(request.getRequestURI());
        com.example.javarushspring2springweb.lesson8_jpa.controller.comands.Command commandBean = commands.get(commandName);
        return commandBean.doGet(request);
    }

    public String resolveCommand(HttpServletRequest request, HttpServletResponse response) {
        String commandName = extractCommandFromUri(request.getRequestURI());
        Command commandBean = commands.get(commandName);
        return commandBean.doPost(request, response);
    }

    private String extractCommandFromUri(String uri) {
        uri = uri.replaceFirst(".+?://", "");
        uri = uri.replaceFirst("\\?.*", "");
        String[] segments = uri.split("/");
        if (segments.length > 1) {
            return segments[1].replace("-", "_").toLowerCase();
        } else return "";
    }


    public void fillContextMap() {
        commands = Map.of(
                "", indexCommand,
                "users", usersCommand,
                "user", userCommand,
                "orders", ordersCommand,
                "order", orderCommand,
                "products", productsCommand,
                "product", productCommand
        );
    }
}
