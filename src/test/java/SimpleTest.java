import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class SimpleTest {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    ArrayList<String> linksList = new ArrayList();
    @Test
    public void navigate() throws Exception {
        //init
        System.setProperty("webdriver.chrome.driver", "D:\\nodejs\\chromedriver.exe");
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etsy","artistmdart","ktvbrf282f34");
        st = con.createStatement();
        getData();
        //System.out.println("linksList:"+linksList);
        //Bot job
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://etsy.com");
        //==================================1.logining==================================
        WebElement loginFormOpen = driver.findElement(By.id("sign-in"));
        loginFormOpen.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement addLoginName = driver.findElement(By.id("username-existing"));
        addLoginName.sendKeys("artistmdart");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement addLoginPassword = driver.findElement(By.id("password-existing"));
        addLoginPassword.sendKeys("ktvbrf282f34");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement submitLoginForm = driver.findElement(By.id("signin-button"));
        submitLoginForm.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //==================================2.get links=================================
        for (String l: linksList) {
            //max 300 liks in hour
            System.out.println(l);
            driver.get(l);
            WebElement posts = driver.findElement(By.className("subheading"));
            
        }

        driver.quit();
    }

    public void getData() {
        try{
            String query = "SELECT * FROM links";

            rs = st.executeQuery(query);
            System.out.println("Records from database");
            while (rs.next()){
                String id           = rs.getString("id");
                String link         = rs.getString("link");
                String created_at   = rs.getString("created_at");
                String count        = rs.getString("count");
//                System.out.println(id+":->"+link+":->"+created_at+":->"+count);
                linksList.add(rs.getString("link"));
            }
//            System.out.println("linksList:"+linksList);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
