import java.sql.*;

/**
 * Created by MadoGiga on 2016-04-01.
 */
public class UserDao {
    public User get(String id) throws SQLException, ClassNotFoundException {
        //사용자는 어디에 저장되어 있는가
        //DataBase를 사용하자
        //어떤 database를 사용하는가
        //Mysql

        //Connection을 맺고
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju", "jeju", "jejupw");
        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from userinfo where id = ?");
        preparedStatement.setString(1, id);

        //실행시키고

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        //결과를 User에 잘 매핑하고

        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        //사용자는 어디에 저장되어 있는가
        //DataBase를 사용하자
        //어떤 database를 사용하는가
        //Mysql

        //Connection을 맺고
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju", "jeju", "jejupw");
        //쿼리를 만들어서
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo(id, name, password) values(?, ?, ?)");
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        //실행시키고

        preparedStatement.executeUpdate();


        //자원을 해지한다.
        preparedStatement.close();
        connection.close();
    }
}
