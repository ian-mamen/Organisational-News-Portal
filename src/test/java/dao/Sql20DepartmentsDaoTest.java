package dao;

import models.Department_News;
import models.Departments;
import models.News;
import models.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql20DepartmentsDaoTest {

    private static Sql20DepartmentsDao sql2oDepartmentsDao;
    private static Sql20UsersDao sql2oUsersDao;
    private static Sql20NewsDao sql2oNewsDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {
        //uncomment the two lines below to run locally and change to your  credentials
//        String connectionString = "jdbc:postgresql://localhost:5432/organisational_news_portal_test";
//        Sql2o sql2o = new Sql2o(connectionString, "mamen", "Theyoungstylist007@");

        //comment the two lines below to run locally
         String connectionString = "jdbc:postgresql://ec2-54-234-28-165.compute-1.amazonaws.com:5432/d90lff6n3emlli"; //!
        Sql2o sql2o = new Sql2o(connectionString, "yrhlomtnrmnmzz", "e6d836bfda3301e46b98ffafbdff064a6b47ed825f1c8b6969fd65bb8526da87");
        sql2oDepartmentsDao=new Sql20DepartmentsDao(sql2o);
        sql2oUsersDao=new Sql20UsersDao(sql2o);
        sql2oNewsDao=new Sql20NewsDao(sql2o);
        System.out.println("connected to database");
        conn=sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        System.out.println("clearing database");
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void idSetForAddedDepartment() {
        Departments department= setUpNewDepartment();
        int originalId=department.getId();
        sql2oDepartmentsDao.add(department);
        assertNotEquals(originalId,department.getId());
    }

    @Test
    public void addUserToDepartment() {
        Departments department=setUpNewDepartment();
        sql2oDepartmentsDao.add(department);
        Users user=setUpNewUser();
        Users otherUser= new Users("Amuga","intern","Paper work");
        sql2oUsersDao.add(user);
        sql2oUsersDao.add(otherUser);
        sql2oDepartmentsDao.addUserToDepartment(user,department);
        sql2oDepartmentsDao.addUserToDepartment(otherUser,department);
        assertEquals(2,sql2oDepartmentsDao.getAllUsersInDepartment(department.getId()).size());
        assertEquals(2,sql2oDepartmentsDao.findById(department.getId()).getSize());
    }

    @Test
    public void getAll() {
        Departments department=setUpNewDepartment();
        Departments otherDepartment=new Departments("printing","printing of books");
        sql2oDepartmentsDao.add(department);
        sql2oDepartmentsDao.add(otherDepartment);
        assertEquals(department,sql2oDepartmentsDao.getAll().get(0));
        assertEquals(otherDepartment,sql2oDepartmentsDao.getAll().get(1));
    }

    @Test
    public void correctDepartmentIsReturnedFindById() {
        Departments department=setUpNewDepartment();
        Departments otherDepartment=new Departments("printing","printing of books");
        sql2oDepartmentsDao.add(department);
        sql2oDepartmentsDao.add(otherDepartment);
        assertEquals(department,sql2oDepartmentsDao.findById(department.getId()));
        assertEquals(otherDepartment,sql2oDepartmentsDao.findById(otherDepartment.getId()));

    }

    @Test
    public void getAllUsersInDepartment() {
        Departments department=setUpNewDepartment();
        sql2oDepartmentsDao.add(department);
        Users user=setUpNewUser();
        Users otherUser= new Users("Amuga","intern","Paper work");
        sql2oUsersDao.add(user);
        sql2oUsersDao.add(otherUser);
        sql2oDepartmentsDao.addUserToDepartment(user,department);
        sql2oDepartmentsDao.addUserToDepartment(otherUser,department);
        assertEquals(2,sql2oDepartmentsDao.getAllUsersInDepartment(department.getId()).size());
        assertEquals(2,sql2oDepartmentsDao.findById(department.getId()).getSize());
    }
    @Test
    public void getDepartmentNews() {
        Users users=setUpNewUser();
        sql2oUsersDao.add(users);
        Departments departments=setUpNewDepartment();
        sql2oDepartmentsDao.add(departments);
        Department_News department_news =new Department_News("Meeting","To nominate new chairman",departments.getId()
                ,users.getId());
        sql2oNewsDao.addDepartmentNews(department_news);
        News news=new News("Meeting","Meeting to set activities for team building",users.getId());
        sql2oNewsDao.addNews(news);

        assertEquals(department_news.getTitle(),sql2oDepartmentsDao.getDepartmentNews(department_news.getId()).get(0).getTitle());
    }

    //helper
    private Departments setUpNewDepartment() {
        return new Departments("Writing","writing of newspaper");
    }
    private Users setUpNewUser() {
        return new Users("Ian Amuga","manager","Writer");
    }
}


