package com.itechart.call.me.library.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPoolInterf {
    Connection getConnection() throws SQLException;
}
