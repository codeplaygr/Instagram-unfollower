
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Instagram 
{
	public static WebDriver driver;
	public static Integer follwings_Count_int;
	public static Integer follwers_Count_int;
	public static List<String> personFollowingNameList ;
	public static List<String> personFollowersNameList ;
	public static List<String> personWhoISNotFollowingMe ;
	public static WebDriverWait wait_exp ;
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException
    {
		
		launch_Browser();
		loadURL(driver);
		EnterCredentialsAndClikLoginBtn("","",driver);    // Please Enter your USer_name and Password Here
		Handle_NotNow_PopUP(driver);
		openMyProfilePage(driver);
		get_theCount_Follwings(driver);
		get_the_count_of_Followers(driver);
		open_list_of_following_PopUP(driver);
		scroll_Till_the_bottom_of_following_popUP(driver);
		get_the_list_of_following_persons(driver);
		open_list_of_followers_PopUP(driver);
		scroll_Till_the_bottom_of_followers_popUP(driver);
		get_the_list_of_followers_persons(driver);
		get_theList_of_Person_who_dont_follow_me_back();
		open_list_of_following_PopUP(driver);
		scroll_Till_the_bottom_of_following_popUP(driver);
		un_follow_them_who_dont_follow_me_back(driver);
		//close_Browser(driver);
    }


public static void launch_Browser() throws MalformedURLException 
{
	
    System.setProperty("webdriver.chrome.driver", "");        //Please Provide the path of Your ChromeDriver into Second Argument
	driver=new ChromeDriver();
	wait_exp = new WebDriverWait(driver,50);
}

public static void loadURL(WebDriver driver)
{
	driver.get("https://www.instagram.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(60, TimeUnit. SECONDS);
}

public static void EnterCredentialsAndClikLoginBtn(String usreName , String password , WebDriver driver)
{
	driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(usreName);
	driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
	driver.findElement(By.xpath("//div[text()='Log In']/parent::button")).click();
}

public static void Handle_NotNow_PopUP(WebDriver driver) throws InterruptedException
{
	wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Not Now']")));
    driver.findElement(By.xpath("//button[text()='Not Now']")).click();
    wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Not Now']")));
	driver.findElement(By.xpath("//button[text()='Not Now']")).click();
	
}


public static void openMyProfilePage(WebDriver driver)
{
	driver.findElement(By.xpath("//img[contains(@alt,'test_user_69_420')]/parent::span")).click();
	driver.findElement(By.xpath("//div[text()='Profile']/parent::div")).click();
}

public static void get_theCount_Follwings(WebDriver driver) throws InterruptedException
{
	wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/test_user_69_420/following/']//span[1]")));
	String  follwings_Count_str = driver.findElement(By.xpath("//a[@href='/test_user_69_420/following/']//span[1]")).getText().trim();
    follwings_Count_int = Integer.valueOf(follwings_Count_str);
	System.out.println("the total number of people who Person who i follow are [Followings]: "+ follwings_Count_int);
}

public static void get_the_count_of_Followers(WebDriver driver)
{
	String  follwers_Count_str = driver.findElement(By.xpath("//a[@href='/test_user_69_420/followers/']/span")).getText().trim();
    follwers_Count_int = Integer.valueOf(follwers_Count_str);
	System.out.println("the total number of people who Person who  follow me are [Followers]: "+ follwers_Count_int);
}

public static void open_list_of_following_PopUP(WebDriver driver)                          
{
	driver.findElement(By.xpath("//a[@href='/test_user_69_420/following/']")).click();
}

public static void scroll_Till_the_bottom_of_following_popUP(WebDriver driver) throws InterruptedException              
{
	 wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Following']")));
	 List<WebElement> PersonFollwings = driver.findElements(By.xpath("//button[text()='Following']"));
     Integer PersonFollwing_int = Integer.valueOf(PersonFollwings.size());
 	
 	while (!(PersonFollwing_int.equals(follwings_Count_int)))
	{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement ListOfFollowersScroll = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]"));
	js.executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight);return arguments[0].scrollHeight; ", ListOfFollowersScroll);
	wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Following']")));
	List<WebElement> PersonFollowing1 = driver.findElements(By.xpath("//button[text()='Following']"));
	PersonFollwing_int=Integer.valueOf(PersonFollowing1.size());
	System.out.println("Scrolling inside Pop-up : following");
	}
}

public static void  get_the_list_of_following_persons(WebDriver driver)
{
	personFollowingNameList = new ArrayList<String>();
     List<WebElement> PersonFollwings1 = driver.findElements(By.xpath("//button[text()='Following']"));
 	for(int i=0; i<PersonFollwings1.size(); i++)
 	{	
 	if(PersonFollwings1.get(i).getText().trim().equals("Following"))
 	{
 		String nameOfFollowingPerson = driver.findElements(By.xpath("//button[text()='Following']/parent::div/preceding-sibling::div[1]//span/a/parent::span")).get(i).getText().trim();
 		personFollowingNameList.add(nameOfFollowingPerson);	
 	}
 	}
 	
 	System.out.println("List of Followings:   --------------------------------------------");
 	for(String following_User_name :personFollowingNameList)
 	{
 		System.out.println(following_User_name);
 	}
 	driver.findElement(By.xpath("//h1[text()='Following']/parent::div//button")).click();
}

public static void open_list_of_followers_PopUP(WebDriver driver)
{
	driver.findElement(By.xpath("//a[@href='/test_user_69_420/followers/']")).click();
}


public static void scroll_Till_the_bottom_of_followers_popUP(WebDriver driver) throws InterruptedException
{
	
	wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Remove']")));
	List<WebElement> PersonFollwers = driver.findElements(By.xpath("//button[text()='Remove']"));
	Integer PersonFollwers_int = Integer.valueOf(PersonFollwers.size());
	
	while (!(follwers_Count_int.equals(PersonFollwers_int)))
	{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement ListOfFollowersScroll = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]"));
	js.executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight);return arguments[0].scrollHeight; ", ListOfFollowersScroll);
	wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Remove']")));
	List<WebElement> PersonFollwers1 = driver.findElements(By.xpath("//button[text()='Remove']"));
	PersonFollwers_int=Integer.valueOf(PersonFollwers1.size());
	System.out.println("Scrolling inside Pop-up : followers");
	}
}


public static void  get_the_list_of_followers_persons(WebDriver driver)
{
	    personFollowersNameList = new ArrayList<String>();
	    List<WebElement> PersonFollwers1 = driver.findElements(By.xpath("//button[text()='Remove']"));
	    
		for(int i=0; i<PersonFollwers1.size(); i++)
		{	
		if(PersonFollwers1.get(i).getText().trim().equals("Remove"))
		{
			String nameOfFollowingPerson = driver.findElements(By.xpath("//button[text()='Remove']/parent::div/preceding-sibling::div[1]//span/a/parent::span")).get(i).getText().trim();
			personFollowersNameList.add(nameOfFollowingPerson);	
		}
		}
		
		System.out.println("List of Followers:   --------------------------------------------");
		for(String follower_user_name :personFollowersNameList)
		{
			System.out.println(follower_user_name);
		}
		
		driver.findElement(By.xpath("//h1[text()='Followers']/parent::div//button")).click();
}


public static void get_theList_of_Person_who_dont_follow_me_back()
{
	  personWhoISNotFollowingMe = new ArrayList<String>();
		
		HashSet<String> hs = new HashSet<String>(personFollowersNameList);
		 for (String match : personFollowingNameList) 
		 {
	         
	         if (hs.contains(match)) 
	         {
	             System.out.println("Person who Follows me back : " + match);
	         }
	         else 
	         {     
	        	 personWhoISNotFollowingMe.add(match);
	             System.out.println("Person who Don't Follows me back : " + match);
	         }
	     }
}

public static void un_follow_them_who_dont_follow_me_back(WebDriver driver) throws InterruptedException
{
	 for (String user_name : personWhoISNotFollowingMe)
	 {
		 WebElement to_unFollow = driver.findElement(By.xpath("//a[text()='"+user_name+"']/parent::span/ancestor::li//button"));
		 Actions action = new Actions(driver);
		 action.moveToElement(to_unFollow).click().perform();
		 System.out.println(user_name +" has been unfollowed");
		 driver.findElement(By.xpath("//button[text()='Unfollow']")).click();
		 wait_exp.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Follow']")));
	 }
}

public  static void close_Browser(WebDriver driver)
{
	driver.quit();
}

}
