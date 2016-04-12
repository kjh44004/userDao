import java.sql.*;

/**
 * Created by MadoGiga on 2016-04-01.
 */
public class UserDao {

    private final ConnectionMaker connectionMaker;

    UserDao(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws SQLException, ClassNotFoundException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionMaker.getConnection();

            preparedStatement = connection.prepareStatement(
                    "select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long id = null;

        try {
            connection = connectionMaker.getConnection();

            String sql = "insert into userinfo(name, password) values(?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            id = getLastInsertId(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
        return id;
    }

    private Long getLastInsertId(Connection connection) throws SQLException {
        PreparedStatement preparedStatement2 = connection.prepareStatement(
                "select last_insert_id()");

        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();
        return id;

    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();

            String sql = "delete from userinfo where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionMaker.getConnection();

            String sql = "update userinfo set name = ?, password = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
