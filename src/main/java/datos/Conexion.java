package datos;

import java.sql.*;

public class Conexion {
    private static final String JDBC_URL ="jdbc:mysql://localhost:3306/sga?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER ="root";

    private static final String JDBC_PASSWORD ="admin";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    public static void close(Connection connection) throws SQLException {
        connection.close();
    }




}
