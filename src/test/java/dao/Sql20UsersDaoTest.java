package dao;

import models.Departments;
import models.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql20UsersDaoTest {

    private static Sql20DepartmentsDao sql2oDepartmentsDao;
    private static Sql20UsersDao sql2oUsersDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {

        //uncomment the two lines below to run locally and change to your  credentials
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_portal_test";
        Sql2o sql2o = new Sql2o(connectionString, "mamen", "Theyoungstylist007@");

        //comment the two lines below to run locally
//        String connectionString = "jdbc:postgresql://ec2-18-215-99-63.compute-1.amazonaws.com:5432/da93g9c21mukon";
//        Sql2o sql2o = new Sql2o(connectionString, "fvvikmppgjhovk", "c5fc3da5048cda471e429f687669b2eec7bca3c7c07d83df5681f43d9f5bfc28");
        sql2oDepartmentsDao=new Sql20DepartmentsDao(sql2o);
        sql2oUsersDao=new Sql20UsersDao(sql2o);
        System.out.println("connected to database");
        conn=sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }


    @Test
    public void addingUserToDbSetsUserId() {
        Users user = setUpNewUser();
        int originalId= user.getId();
        sql2oUsersDao.add(user);
        assertNotEquals(originalId,user.getId());
    }

    @Test
    public void addedUserIsReturnedCorrectly() {
        Users user = setUpNewUser();
        sql2oUsersDao.add(user);
        assertEquals(user.getName(),sql2oUsersDao.findById(user.getId()).getName());
    }

    @Test
    public void allInstancesAreReturned() {

        Users users=setUpNewUser();
        Users otherUser= new Users("Wangui","intern","Paper work");
        sql2oUsersDao.add(users);
        sql2oUsersDao.add(otherUser);
        assertEquals(users.getName(),sql2oUsersDao.getAll().get(0).getName());
        assertEquals(otherUser.getName(),sql2oUsersDao.getAll().get(1).getName());
    }
    @Test
    public void getDepartmentsUserIsIn() {
        Departments department=setUpNewDepartment();
        Departments otherDepartment=new Departments("printing","printing of books");
        sql2oDepartmentsDao.add(department);
        sql2oDepartmentsDao.add(otherDepartment);
        Users user=setUpNewUser();
        Users otherUser= new Users("Wangui","intern","Paper work");
        sql2oUsersDao.add(user);
        sql2oUsersDao.add(otherUser);
        sql2oDepartmentsDao.addUserToDepartment(user,department);
        sql2oDepartmentsDao.addUserToDepartment(otherUser,department);
        sql2oDepartmentsDao.addUserToDepartment(user,otherDepartment);
        assertEquals(2,sql2oUsersDao.getAllUserDepartments(user.getId()).size());
        assertEquals(1,sql2oUsersDao.getAllUserDepartments(otherUser.getId()).size());
    }

    //helper
    private Users setUpNewUser() {
        return new Users("Ian Amuga","manager","Writer");
    }
    private Departments setUpNewDepartment() {
        return new Departments("Writing","writing of newspaper");
    }
}

