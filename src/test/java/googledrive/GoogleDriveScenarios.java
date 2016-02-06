package googledrive;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.ConstantVariables;
import utils.ObjectPropertyFile;

public class GoogleDriveScenarios{	

	public RemoteWebDriver driver;	
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod(alwaysRun=true)
	@Parameters({ "browserType", "platform" })
	public void GridCaps(String browserType, String platform) throws Throwable {
			Thread.sleep(10000);
			DesiredCapabilities caps = null;
		
		if (browserType.equals("IE") && (platform.equals("WINDOWS"))) {
			caps = DesiredCapabilities.internetExplorer();
			caps.setBrowserName("internet explorer");
			caps.setPlatform(Platform.WINDOWS);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		} else if (browserType.equals("CH") && (platform.equals("WINDOWS"))) {
			caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");		
			caps.setPlatform(Platform.WINDOWS);
			
		} else if (browserType.equals("FF") && (platform.equals("WINDOWS"))) {
			caps = DesiredCapabilities.firefox();
			caps.setBrowserName("firefox");		
			caps.setPlatform(Platform.WINDOWS);
			
		}else if (browserType.equals("IE") && (platform.equals("LINUX"))) {
			caps = DesiredCapabilities.internetExplorer();
			caps.setBrowserName("internet explorer");
			caps.setPlatform(Platform.LINUX);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
		} else if (browserType.equals("FF") && (platform.equals("LINUX"))) {
			caps = DesiredCapabilities.firefox();
			caps.setBrowserName("firefox");	
			caps.setPlatform(Platform.LINUX);
			
		}else if (browserType.equals("CH") && (platform.equals("LINUX"))) {
			caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.LINUX);
			
		} else if (browserType.equals("CH") && (platform.equals("MAC"))) {
			caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");	
			caps.setPlatform(Platform.MAC);
			
		} else if (browserType.equals("FF") && (platform.equals("MAC"))) {
			caps = DesiredCapabilities.firefox();
			caps.setBrowserName("firefox");	
			caps.setPlatform(Platform.MAC);
			
		}else if (browserType.equals("IE") && (platform.equals("MAC"))) {
			caps = DesiredCapabilities.internetExplorer();
			caps.setBrowserName("internet explorer");
			caps.setPlatform(Platform.MAC);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
		driver.manage().window().maximize();		 	   
	}
	
	/*==================================================================
    Method Name: GoogleDriveNegativeScenarioLoginFirstCase
	Description: Enter Invalid UserName and Password and verify the Error Message
 	==================================================================*/
	@Test(priority=1)
	public void GoogleDriveNegativeScenarioLoginFirstCase() throws Exception{		
 		//Calling "EnterUserIdAndPassword" method and Passing Invalid Credentials
		Login(ConstantVariables.UserID,"WrongPassword"); 		
 	    String RuntimeLoginFailedMessage= driver.findElement(By.id(ObjectPropertyFile.LoginFailedErrorMessage_id)).getText();
	    String ExpectedLoginFailedMessage=ConstantVariables.ExpectedLoginFailedMessage; 	    
 	    try {
 		  Assert.assertEquals(RuntimeLoginFailedMessage, ExpectedLoginFailedMessage);
 	    } catch (Error e) {
		verificationErrors.append(e.toString());
		}		
	}
	
	/*========================================================================================
    Method Name: UploadImageInDekstopGoogleDriveAndCheckFileGetsSyncedToTheGoogleDriveWebsite
	Description: Upload Image In Desktop Google Drive And Check File Gets 
	             Synced To The Google Drive Website
 	========================================================================================*/
	@Test(priority=2)
	public void UploadImageInDesktopGoogleDriveAndCheckFileGetsSyncedToTheGoogleDriveWebsite() throws Throwable {	
	 	
		UploadFileOrImageInDesktopGoogleDrive(ConstantVariables.ImageFile);
				
	}
	
	/*========================================================================================
    Method Name: UploadFileInDesktopGoogleDriveAndCheckFileGetsSyncedToTheGoogleDriveWebsite
	Description: Upload File In Desktop Google Drive And Check File Gets 
	             Synced To The Google Drive Website
 	========================================================================================*/
	@Test(priority=3)
	public void UploadFileInDesktopGoogleDriveAndCheckFileGetsSyncedToTheGoogleDriveWebsite() throws Throwable {	
	 	
		UploadFileOrImageInDesktopGoogleDrive(ConstantVariables.UploadFile1);
				
	}
	
	/*========================================================================================
    Method Name: RenameUploadFileInDekstopGoogleDrivAndCheckFileGetsSyncedToTheGoogleDriveWebsite
	Description: Rename Upload File In Desktop Google Drive And Check File Gets 
	             Synced To The Google Drive Website
 	========================================================================================*/
	@Test(priority=4)
 	public void RenameUploadFileInDekstopGoogleDrivAndCheckFileGetsSyncedToTheGoogleDriveWebsite() throws Throwable {
		
		RenameUploadFile(ConstantVariables.UploadFile1,ConstantVariables.RenameUploadFile2);
	 		
	}
	/*========================================================================================
    Method Name: GoogleDriveUploadImageWhenInternetIsOff
	Description: Turn off Internet and upload file form Web GoogleDrive. 
	             Verify whether the file exist in Desktop google drive or not
 	========================================================================================*/
	@Test(priority=6)
	public void GoogleDriveUploadImageWhenInternetIsOff() throws Throwable {
		//Calling "EnterUserIdAndPassword" method and Passing Invalid Credentials
			    Login(ConstantVariables.UserID,ConstantVariables.Password);
		 	    Thread.sleep(1000);
				if(driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleUserIcon_css)).isDisplayed()){		
					//Click on Google Apps Icon
					driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleAppsIcon_css)).click();
					Thread.sleep(5000);
					//Click on Google Drive Icon
				    driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleDriveIcon_css)).click();
				    Thread.sleep(1000);
				   //Click on My Drive Icon
				    driver.findElement(By.cssSelector(ObjectPropertyFile.GoogeDriveMyDriveTab_css)).click();
				    Thread.sleep(5000);		  
				    //Get Items list under My Drive Tab and click on "Upload Files" Option
				    List<WebElement>Items1=driver.findElements(By.cssSelector(ObjectPropertyFile.GoogeDriveDropdownOptions_css));
				    Items1.get(1).click();
				    
				  //Turn off Internet
				    String path="/src/test/resources/InternetBatchFiles/NetworkDown.bat";
				    File file = new File(System.getProperty("user.dir")+path);
				    Runtime.getRuntime().exec("cmd /c start "+file);
				    Thread.sleep(10000);
				    
				    //Upload Image
				    String FileName="/src/test/resources/UploadFiles/"+ConstantVariables.NetworkDownUploadFile1;
				    UploadFile(FileName);
				    String[] ActualFileName=FileName.split("/");
				    //Verify File in Desktop Google Drive
				    File NetworkDownUploadFilePath = new File(System.getProperty("user.dir")+ActualFileName[5]);
				    if(NetworkDownUploadFilePath.exists()){
						  System.out.println("File existed");
					  }else{
						  System.out.println("File not found!");
					  }			   
				    	
						//Turn On Internet
						String path1="/src/test/resources/InternetBatchFiles/NetworkUP.bat";
					    File file1 = new File(System.getProperty("user.dir")+path1);
					    Runtime.getRuntime().exec("cmd /c start "+file1);
					    Thread.sleep(10000);
					    
					    Logout();
					    
				}
				
			}
				

	/*========================================================================================
    Method Name: GoogleDriveUploadImageWhenInternetIsOff
	Description: Turn off Internet and upload file form Web GoogleDrive. 
	             Verify whether the file exist in Desktop google drive or not
 	========================================================================================*/
	@Test(priority=5)
	public void CloseBrowserWhileUploadingFileFromGoogleDrive() throws Throwable {
		//Calling "EnterUserIdAndPassword" method and Passing Invalid Credentials
			    Login(ConstantVariables.UserID,ConstantVariables.Password);
		 	    Thread.sleep(1000);
				if(driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleUserIcon_css)).isDisplayed()){		
					//Click on Google Apps Icon
					driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleAppsIcon_css)).click();
					Thread.sleep(5000);
					//Click on Google Drive Icon
				    driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleDriveIcon_css)).click();
				    Thread.sleep(1000);
				   //Click on My Drive Icon
				    driver.findElement(By.cssSelector(ObjectPropertyFile.GoogeDriveMyDriveTab_css)).click();
				    Thread.sleep(5000);		  
				    //Get Items list under My Drive Tab and click on "Upload Files" Option
				    List<WebElement>Items1=driver.findElements(By.cssSelector(ObjectPropertyFile.GoogeDriveDropdownOptions_css));
				    Items1.get(1).click();
				   				    
				    //Upload Image
				    String FileName="/src/test/resources/UploadFiles/"+ConstantVariables.NetworkDownUploadFile1;
				    UploadFile(FileName);
				    driver.close();
				    String[] ActualFileName=FileName.split("/");
				  //Verify File in Desktop Google Drive
				    File NetworkDownUploadFilePath = new File(System.getProperty("user.dir")+ActualFileName[5]);
				    if(NetworkDownUploadFilePath.exists()){
						  System.out.println("File existed");
					  }else{
						  System.out.println("File not found!");
					  }			   				    	
										    
				}
				
			}
				
	/*==================================================================
    Method Name: VerifyFilePresentInWebGoogleDrive
	Description: VerifyFilePresentInWebGoogleDrive
 	==================================================================*/	
	public void VerifyFilePresentInWebGoogleDrive(String FileName) throws InterruptedException {
			
		    Thread.sleep(1000);				    
		    String RuntimeinnerHTML=driver.findElement(By.cssSelector(ObjectPropertyFile.GoogeDriveUploadFrame_css)).getAttribute("innerHTML");
		    //System.out.println("RuntimeinnerHTML"+ RuntimeinnerHTML);
		    Assert.assertTrue(RuntimeinnerHTML.contains(FileName));
		   
		    
		}    
	/*==================================================================
	    Method Name: EnterUserIdAndPassword
		Description: Reusable Function-Click on Login after 
					 entering UserName and Password
	 ==================================================================*/
	public void Login(String UserName, String Password) throws InterruptedException {
		//Invoke URL
		driver.navigate().to(ConstantVariables.URL);
		Thread.sleep(1000);
		//Click on Go to Google Diver button
		driver.findElement(By.cssSelector(ObjectPropertyFile.GoToGoogleDrive_css)).click();
 		Thread.sleep(5000);
 		//Enter Email ID
		driver.findElement(By.id(ObjectPropertyFile.Email_id)).clear();
 	    driver.findElement(By.id(ObjectPropertyFile.Email_id)).sendKeys(UserName);
 	    driver.findElement(By.id(ObjectPropertyFile.next_id)).click();
 	    Thread.sleep(5000);
 	    //Enter Password
 	    driver.findElement(By.id(ObjectPropertyFile.Password_id)).clear();
 	    driver.findElement(By.id(ObjectPropertyFile.Password_id)).sendKeys(Password);
 	    //Click SignIn Button
 	    driver.findElement(By.id(ObjectPropertyFile.signIn_id)).click();
 	    Thread.sleep(5000);
	}
	/*==================================================================
    Method Name: UploadFile
	Description: UploadFile using Robot
 	==================================================================*/
	public void UploadFile(String path) throws Throwable{
		Thread.sleep(5000);
		System.out.println("Entered Upload Method");
		System.out.println("Path"+path);   
		File file = new File(System.getProperty("user.dir")+path);
	    String picturePath = file.getAbsolutePath();
	    StringSelection ss= new StringSelection(picturePath);	
	    
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	    Robot robot= new Robot();
	    robot.keyPress(KeyEvent.VK_ENTER);	
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	   
	}
	
	/*==================================================================
    Method Name: UploadFileOrImageInDesktopGoogleDrive
	Description: UploadFileOrImageInDesktopGoogleDrive
 	==================================================================*/
	public void UploadFileOrImageInDesktopGoogleDrive(String FileName) throws Throwable {	
	 	//Upload Image From Project Directory to Desktop Google Drive
		Thread.sleep(1000);
	 	String FilePath="/src/test/resources/MoveFilesToGoogleDrive/"+FileName;	     
	    try{    		
    		File file = new File(System.getProperty("user.dir")+FilePath);
    	    if(file.renameTo(new File(System.getProperty("user.home")+"/Google Drive/" + file.getName()))){
    		System.out.println("File is moved from local to desktop google drive successful!");
    	    }else{
    		System.out.println("File is failed to move!");
    	    }
    	}catch(Exception e){
    		e.printStackTrace();
    	}	 	    
 		//Calling "EnterUserIdAndPassword" method and Passing Invalid Credentials
	    Login(ConstantVariables.UserID,ConstantVariables.Password);
 	    Thread.sleep(1000);
		if(driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleUserIcon_css)).isDisplayed()){		
			//Click on Google Apps Icon
			driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleAppsIcon_css)).click();
			Thread.sleep(500);
			//Click on Google Drive Icon
		    driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleDriveIcon_css)).click();
		    //VerifyFilePresentInWebGoogleDrive
		    VerifyFilePresentInWebGoogleDrive(FileName);
		   		    
		  //Move Desktop Google Drive Image to Project Directory  
			Thread.sleep(5000);
		 	String GoogleDriveFilePath="/Google Drive/"+FileName;    
		    try{    		
	    		File file = new File(System.getProperty("user.home")+GoogleDriveFilePath);
	    	    if(file.renameTo(new File(System.getProperty("user.dir")+"/src/test/resources/MoveFilesToGoogleDrive/" + file.getName()))){
	    		System.out.println("File is moved from desktop google drive to Project Folder successful!");
	    	    }else{
	    		System.out.println("File is failed to move!");
	    	    }
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		    
		    Logout();
		}
	}
		
	/*========================================================================================
    Method Name: RenameUploadFileInDekstopGoogleDrivAndCheckFileGetsSyncedToTheGoogleDriveWebsite
	Description: Rename Upload File In Desktop Google Drive And Check File Gets 
	             Synced To The Google Drive Website
 	========================================================================================*/
	public void RenameUploadFile(String ProjectFileName, String GoogleDriveFileName) throws Throwable {	
	 	//Upload Files From Project Directory to Desktop Google Drive
	 	String OldFilePath="/src/test/resources/MoveFilesToGoogleDrive/"+ProjectFileName;
	 	String NewFilePath="/src/test/resources/MoveFilesToGoogleDrive/"+GoogleDriveFileName;	     
	    try{    		
    		File file = new File(System.getProperty("user.dir")+OldFilePath);
    	    if(file.renameTo(new File(System.getProperty("user.home")+"/Google Drive/" + file.getName()))){
    	    	System.out.println("File is moved from local to desktop google drive successful!");
    		
		    		//Rename the File only if file is moved to Desktop Drive
		    	    File OldFile = new File(System.getProperty("user.home")+"/Google Drive/"+file.getName());
		    	    File NewFile = new File(System.getProperty("user.home")+"/Google Drive/"+GoogleDriveFileName);
				            // Rename file and proceed further only condition is passed
				    	    if(OldFile.renameTo(NewFile)) {
				    	         System.out.println("File name has been renamed");
				    	         Thread.sleep(10000);	
				    	        //Calling "EnterUserIdAndPassword" method and Passing Invalid Credentials
				    	 	    Login(ConstantVariables.UserID,ConstantVariables.Password);
				    	 	    if(driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleUserIcon_css)).isDisplayed()){		
				    	 			//Click on Google Apps Icon
				    	 			driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleAppsIcon_css)).click();
				    	 			Thread.sleep(500);
				    	 			//Click on Google Drive Icon
				    	 		    driver.findElement(By.cssSelector(ObjectPropertyFile.GoogleDriveIcon_css)).click();
				    	 		    Thread.sleep(1000);				    
				    	 		   //VerifyFilePresentInWebGoogleDrive
				    			    VerifyFilePresentInWebGoogleDrive(GoogleDriveFileName);
				    	 		    
				    	 		  //============Move Desktop Google Drive Image to Project Directory============  
				    	 			Thread.sleep(5000);
				    	 		 	String GoogleDriveFilePath="/Google Drive/"+GoogleDriveFileName;    
				    			    try{    		
				    		    		File file1 = new File(System.getProperty("user.home")+GoogleDriveFilePath);
				    		    	    if(file1.renameTo(new File(System.getProperty("user.dir")+"/src/test/resources/MoveFilesToGoogleDrive/" + file1.getName()))){
				    		    		System.out.println("File is moved from desktop google drive to Project Folder successful!");
				    		    	    }else{
				    		    		System.out.println("File is failed to move!");
				    		    	    }
				    		    	}catch(Exception e){
				    		    		e.printStackTrace();
				    		    	}
				    			    Thread.sleep(5000);
				    	 		  //Rename the File Name Back to original
				    	 		    File file1 = new File(System.getProperty("user.dir")+NewFilePath);
				    	    	    File ProjectFile = new File(System.getProperty("user.dir")+"/src/test/resources/MoveFilesToGoogleDrive/" +file1.getName());
				    	    	    File BackToOriginalFileName = new File(System.getProperty("user.dir")+"/src/test/resources/MoveFilesToGoogleDrive/"+ProjectFileName);
				    	            // rename file
				    	    	    if(ProjectFile.renameTo(BackToOriginalFileName)) {
				    	    	         System.out.println("File name has been renamed");
				    	    	      } else {
				    	    	         System.out.println("Error");
				    	    	      }
				    	 		    
				    	 		    Logout();
				    	 		}
		    	 		
		    	      } else {
		    	         System.out.println("Error");
		    	      }
    	    }else{
    		System.out.println("File is failed to move!");
    	    }
    	    
            
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	 
	
	}
	
	/*==================================================================
    Method Name: Logout
	Description: Logout browser
 	==================================================================*/
	public void Logout() throws InterruptedException {
		driver.findElement(By.cssSelector(ObjectPropertyFile.signOutIcon_id)).click();
		Thread.sleep(500);
		driver.findElement(By.id(ObjectPropertyFile.signOutLink_id)).click();
		driver.close();
	
	}
	
	/*==================================================================
    Method Name: teardownafterrun
	Description: Quit browser
 	==================================================================*/
	@AfterSuite
	public void teardownafterrun()
	{
		driver.quit();
	}

}
