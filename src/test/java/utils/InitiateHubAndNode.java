package utils;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

public class InitiateHubAndNode {


	@Test
	public void setup() throws IOException, InterruptedException
	{
		String hubpath="/src/test/resources/GridAndNodeSetUpBatchFiles/hub.bat";
		File file = new File(System.getProperty("user.dir")+hubpath);		
		Runtime.getRuntime().exec("cmd /c start "+file);
		
		String windows_CH_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/Windows_CH_Node.bat";
		File windows_CH_File = new File(System.getProperty("user.dir")+windows_CH_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+windows_CH_File);
		
		String Linux_CH_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/LINUX_CH_Node.bat";
		File Linux_CH_File = new File(System.getProperty("user.dir")+Linux_CH_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+Linux_CH_File);
		
		String Mac_CH_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/MAC_CH_Node.bat";
		File Mac_CH_File = new File(System.getProperty("user.dir")+Mac_CH_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+Mac_CH_File);
		
		String windows_FF_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/Windows_FF_Node.bat";
		File windows_FF_File = new File(System.getProperty("user.dir")+windows_FF_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+windows_FF_File);
		
		String Mac_FF_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/MAC_FF_Node.bat";
		File Mac_FF_File = new File(System.getProperty("user.dir")+Mac_FF_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+Mac_FF_File);
		
		String Linux_FF_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/LINUX_FF_Node.bat";
		File Linux_FF_File = new File(System.getProperty("user.dir")+Linux_FF_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+Linux_FF_File);
		
		/*String windows_IE_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/Windows_IE_Node.bat";
		File windows_IE_File = new File(System.getProperty("user.dir")+windows_IE_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+windows_IE_File);				
		
		String Linux_IE_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/LINUX_IE_Node.bat";
		File Linux_IE_File = new File(System.getProperty("user.dir")+Linux_IE_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+Linux_IE_File);		
						
		String Mac_IE_node_path="/src/test/resources/GridAndNodeSetUpBatchFiles/MAC_IE_Node.bat";
		File Mac_IE_File = new File(System.getProperty("user.dir")+Mac_IE_node_path);		
		Runtime.getRuntime().exec("cmd /c start "+Mac_IE_File);*/
	}
		
}
