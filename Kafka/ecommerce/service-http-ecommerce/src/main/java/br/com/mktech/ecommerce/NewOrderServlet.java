package br.com.mktech.ecommerce;

import br.com.mktech.ecommerce.dispatcher.KafkaDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class NewOrderServlet extends HttpServlet {

    private final KafkaDispatcher<Order> orderdispatcher = new KafkaDispatcher<>();

    @Override
    public void destroy() {
        super.destroy();
        orderdispatcher.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // we are not caring about any security issues, we are only
            // showing how to use http as a starting point
            var email = req.getParameter("email");
            var amount = new BigDecimal(req.getParameter("amount"));
            var orderId = req.getParameter("uuid");
            var order = new Order(orderId, amount, email);

            try (var database = new OrdersDatabase()) {
                if (!database.savaNew(order)) {
                    orderdispatcher.send("ECOMMERCE_NEW_ORDER", email,
                            new CorrelationId(NewOrderServlet.class.getSimpleName()), order);

                    System.out.println("New order sent successfully");
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("New order sent");
                } else {
                    System.out.println("Old order received");
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("Old order received");
                }
            }

        } catch (ExecutionException | SQLException | InterruptedException e) {
            throw new ServletException(e);
        }
    }
}
