package br.com.mktech.ecommerce;

import br.com.mktech.ecommerce.database.LocalDatabase;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

public class OrdersDatabase implements Closeable {

    private final LocalDatabase database;

    OrdersDatabase() throws SQLException {
        this.database = new LocalDatabase("orders_database");
        //you might want to save all data
        this.database.createNotExists("create table Orders (" +
                "uuid varchar(200) primary key,");
    }

    public boolean savaNew(Order order) throws SQLException {
       if (wasProcessed(order)){
           return false;
       }
        database.update("insert iinto Orders (uuid) values (?)", order.getOrderId());
       return true;
    }

    private boolean wasProcessed(Order order) throws SQLException {
        var results = database.query("select uuid from Orders where uuid = ? limit 1", order.getOrderId());
        return results.next();
    }

    @Override
    public void close() throws IOException {
        database.close();
    }
}
