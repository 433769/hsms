package cz.muni.fi.pa165.skupina06.team02.rms.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.skupina06.team02.rms.app.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.skupina06.team02.rms.app.dao.UserDao;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.Household;
import cz.muni.fi.pa165.skupina06.team02.rms.app.entity.User;

/**
 * @author Vojtech Prusa
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTests extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public UserDao userDao;

    private User[] users;
    private final static int USERS_COUNT = 2;

    /*
     * Prepare users
     */
    @BeforeMethod
    public void setUp() {
        users = new User[USERS_COUNT];
        for (int i = 0; i < USERS_COUNT; i++) {
            users[i] = new User();
            users[i].setFirstName("TestFirstName" + i);
            users[i].setLastName("TestLastName" + i);
            users[i].setPassword("TestPassword" + i);
            users[i].setEmail("TestEmail" + i);
        }
    }

    /**
     * Tests raise of ConstraintViolationException exception when Email is null
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullEmailNotAllowed() {
        users[0].setEmail(null);
        userDao.create(users[0]);
    }

    /**
     * Tests raise of ConstraintViolationException exception when FirstName is null
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullFirstNameNotAllowed() {
        users[0].setFirstName(null);
        userDao.create(users[0]);
    }

    /**
     * Tests raise of ConstraintViolationException exception when LastName is null
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullLastNameNotAllowed() {
        users[0].setLastName(null);
        userDao.create(users[0]);
    }

    /**
     * Tests raise of ConstraintViolationException exception when Password is null
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void nullPasswordNotAllowed() {
        users[0].setPassword(null);
        userDao.create(users[0]);
    }

    /**
     * Tests findById method
     */
    @Test
    public void findById() {
        for (int i = 0; i < USERS_COUNT; i++) {
            userDao.create(users[i]);
            User found = userDao.findById(users[i].getId());
            assertUsersEquals(users[i], found);
        }
    }

    /**
     * Tests findByEmail method
     */
    @Test
    public void findByEmail() {
        for (int i = 0; i < USERS_COUNT; i++) {
            userDao.create(users[i]);
            String email = users[i].getEmail();
            User found = userDao.findByEmail(email);
            assertUsersEquals(users[i], found);
        }
    }

    /**
     * Tests findAll method
     */
    @Test
    public void findAll() {
        for (int i = 0; i < USERS_COUNT; i++) {
            userDao.create(users[i]);
        }
        List<User> foundUsers = userDao.findAll();
        Assert.assertEquals(foundUsers.size(), USERS_COUNT);
    }

    /**
     * Tests create method
     */
    @Test
    public void create() {
        userDao.create(users[0]);
        User expceted = users[0];
        User found = userDao.findById(users[0].getId());
        assertUsersEquals(expceted, found);
    }

    /**
     * Tests delete method
     */
    @Test
    public void delete() {
        User tested = users[0];
        userDao.create(tested);
        Long id = tested.getId();
        userDao.delete(tested);
        User notFound = userDao.findById(id);
        Assert.assertNull(notFound, "Found user should have been null");
    }

    /**
     * Tests update method
     */
    @Test
    public void update() {
        int usedUsers = USERS_COUNT; // or 1
        String differenEmail = "DifferenEmail";
        for (int i = 0; i < usedUsers; i++) {
            userDao.create(users[i]);
            User toUpdate = userDao.findById(users[i].getId());
            toUpdate.setEmail(differenEmail + (i + 1));
            userDao.update(toUpdate);
        }

        for (int i = 0; i < usedUsers; i++) {
            User expceted = users[i];
            User found = userDao.findById(expceted.getId());
            assertUsersEquals(expceted, found);
        }
    }

    /**
     * This method is used for asserting values of found User vs expected User
     * 
     * @param expected
     * @param found
     */
    private void assertUsersEquals(User expected, User found) {
        Assert.assertNotNull(found, "Found user can not be null");
        Assert.assertEquals(expected.getId(), found.getId());
        Assert.assertEquals(expected.getFirstName(), found.getFirstName());
        Assert.assertEquals(expected.getLastName(), found.getLastName());
        Assert.assertEquals(expected.getEmail(), found.getEmail());
        Assert.assertEquals(expected.getPassword(), found.getPassword());
    }

}
