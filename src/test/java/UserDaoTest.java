import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by MadoGiga on 2016-04-01.
 */
public class UserDaoTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        String id = "1";
        User user = userDao.get(id);
        assertEquals("1", user.getId());
        assertEquals("김준호", user.getName());
        assertEquals("1234", user.getPassword());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId("10");
        user.setName("김준호");
        user.setPassword("1234");
        UserDao userDao = new UserDao();
        userDao.add(user);
        User addedUser = userDao.get("10");
        assertEquals("10", addedUser.getId());
        assertEquals("김준호", addedUser.getName());
        assertEquals("1234", addedUser.getPassword());
    }
}
