import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.applet.Applet;
import java.sql.SQLException;

/**
 * Created by MadoGiga on 2016-04-01.
 */
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setup() {
//        ApplicationContext applicationContext = new GenericXmlApplicationContext("daoFactory.xml");
        //UserDao userDao = new DaoFactory().getUserDao();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = (UserDao) applicationContext.getBean("userDao");
        //userDao = applicationContext.getBean("userDao");
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
//        UserDao userDao = new UserDao(new SimpleConnectionMaker());


        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);


        validate(id, name, password, user);
    }

    private void validate(Long id, String name, String password, User user) {
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }


    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "her";
        String password = "1234";

        user.setPassword(password);
        user.setName(name);

//        UserDao userDao = new UserDao(new SimpleConnectionMaker());

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);

        validate(id, name, password, resultUser);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name = "her";
        String password = "1234";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);

        User resultUser = userDao.get(id);
        assertThat(resultUser, nullValue());
    }
}