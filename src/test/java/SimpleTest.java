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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

public class SimpleTest {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Pattern p;
    private Matcher m;
    private static final String MAX_LIKE_GAME_REGEX = "\\b\\d\\d+";
    ArrayList<String> linksList = new ArrayList();
    ArrayList<String> postsList = new ArrayList();
    @Test
    public void navigate() throws Exception {
        //init
        System.setProperty("webdriver.chrome.driver", "D:\\nodejs\\chromedriver.exe");
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/etsy","artistmdart","ktvbrf282f34");
        st = con.createStatement();
        getData();
        getPostsData();
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
        p = Pattern.compile(MAX_LIKE_GAME_REGEX,Pattern.MULTILINE);
        for (String l: linksList) {
            //max 300 liks in hour
            System.out.println(l);
            driver.get(l);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            WebElement posts = driver.findElement(By.className("subheading"));
            m = p.matcher(posts.getText());

            System.out.println(m.find()?
            "I found "+m.group()+"starting at index"+m.start()+" and ending at index "+m.end()
            : "I found nothing! "
            );
            Integer countLink;
            if(m.find()) countLink = Integer.parseInt(m.group());
            else countLink = 10 ;
            System.out.println("postsList:"+postsList);
            // write to testarea post
           /* WebElement postEl = driver.findElement(By.name("post"));
            postEl.sendKeys(postsList.get(0));
            // click submit
            WebElement submitPost = driver.findElement(By.id("new-post-submit"));
            submitPost.click();*/

            //search last element in pagination
            WebElement paginator = driver.findElement(By.xpath("//*[@id=\"pager-wrapper\"]/div/div[1]/span[last()]"));
            //*[@id="pager-wrapper"]/div/div[1]/span[7]
            System.out.println("paginator:"+paginator.findElement(By.xpath("//a")).getAttribute("href"));

//            URL aURL = new URL(l);

//            System.out.println("test:"+aURL.getPath());

        }

//        driver.quit();
    }

    /**
     *
     */
    public void getData() {
        try{
            String query = "SELECT link FROM links where visible=1";

            rs = st.executeQuery(query);
            System.out.println("Records from database");
            while (rs.next()){
//                String id           = rs.getString("id");
                String link         = rs.getString("link");
//                String created_at   = rs.getString("created_at");
//                String count        = rs.getString("count");
//                System.out.println(id+":->"+link+":->"+created_at+":->"+count);
                linksList.add(rs.getString("link"));
            }
//            System.out.println("linksList:"+linksList);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     *
     */
    public void getPostsData() {
        try{
            String query = "SELECT link FROM post where visible=1 LIMIT 1";

            rs = st.executeQuery(query);
            System.out.println("Records from database posts");
            while (rs.next()){
//                String id           = rs.getString("id");
                String link         = rs.getString("link");
                postsList.add(rs.getString("link"));
            }
//            System.out.println("linksList:"+linksList);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    /**
     *
     */
    public void setCountPosts(String id) {
        try{
//            String query = "UPDATE post SET count=count+1 where id="+id;

//            rs = st.executeQuery(query);
            System.out.println("Update records from database posts");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
