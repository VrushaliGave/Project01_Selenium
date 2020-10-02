
package testScript_SignUp;

import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;

public class SignUp {
	static WebDriver driver;
	static WebDriverWait wait;
	static String weburl="https://www.marathishaadi.com/";
	String csv_file="C:\\Users\\Disha\\eclipse-workspace\\Practice-workspace\\Shaadi.com_Project\\src\\TestData.csv";
	
	@BeforeClass
	public static void openBrowser() {
		//Launching Browser
		System.setProperty("webdriver.chrome.driver","D:\\selenium\\chromedriver_win32 (1)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	
		
	
	}
	@Test
	public void signUp() throws Exception {
		//Entering Url 
		driver.navigate().to(weburl);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//button[contains(text(),\"Let's Begin\")]")).click();
		
		//Reading CSV file
		CSVReader reader=new CSVReader(new FileReader(csv_file));
		String[] cell;
		try {
		while((cell=reader.readNext()) != null) {
			for(int i=0;i<cell.length;i++) {
				String email=cell[i];
				String password=cell[i+1];
				String profileCreater=cell[i+2];
				String motherTongue=cell[i+3];
				String gender=cell[i+4];
				
				
			//Inserting data into input field of WebPage	
			driver.findElement(By.xpath("//body/div[@id='___gatsby']/div[1]/div[7]/form[1]/div[2]/div[1]/input[1]")).sendKeys(email);
		    driver.findElement(By.xpath("//body/div[@id='___gatsby']/div[1]/div[7]/form[1]/div[2]/div[2]/input[1]")).sendKeys(password);
		    
		  
			driver.findElement(By.xpath("//span[contains(@class,'Dropdown-arrow')]")).click();	
			
			List<WebElement> list=driver.findElements(By.xpath("//*[contains(@class,'Dropdown-option')]"));
					
			for(int j=0;j<list.size();j++) {
				if(list.get(j).getText().equals(profileCreater)){
					list.get(j).click();
					WebElement RadioButtonM = driver.findElement(By.xpath("//input[@id='gender_male']"));
					 WebElement RadioButtonF = driver.findElement(By.xpath("//input[@id='gender_female']"));
					 	if(profileCreater.equals("Self")||profileCreater.equals("Friend")||profileCreater.equals("Relative")) {
					 		if (gender.equalsIgnoreCase("male")){
					 			RadioButtonM.click();
					 			break;
					 		}
					  
					 		if (gender.equalsIgnoreCase("Female")){
					 			RadioButtonF.click();
					 			break;
					 		}
					 		break;
					 	}
					 	break;
					}
			}
			Thread.sleep(400);
			//Clicking on next button
			driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
			
			Thread.sleep(200);
			
			//Verifying community name with users mother tongue
			
			String communityName=driver.findElement(By.xpath("//div[contains(text(),'Marathi')]")).getText();
			if(motherTongue.equals(communityName)) {
				System.out.println("The mother tongue of user and the community name is match successfully !!!");
			}
			else
			{
				System.out.println("The mother tongue of user and the community name is not matches ");
			}
		

			}
		  }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
   }
		
}
