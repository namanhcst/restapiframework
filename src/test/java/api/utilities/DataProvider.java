package api.utilities;

public class DataProvider {
	
	
	
	@org.testng.annotations.DataProvider
	public String[][] getAllUsersData(){
		
		ExcelUtility xlutil = new ExcelUtility(System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx");
		
		int rowCount = xlutil.getRowCount("UserData");
		
		int cellCount = xlutil.getCellCount("UserData", 0);
		
		String[][] data = new String[rowCount-1][cellCount];
		
		for(int i =1;i<rowCount;i++) {
			
			for(int j =0;j<cellCount;j++) {
				
				data[i-1][j] = xlutil.getCellData("UserData", i, j);
				
			}
		}
		
		return data;
	}
	
	
	@org.testng.annotations.DataProvider
	public String[] getAllUserIDs(){
		
		ExcelUtility xlutil = new ExcelUtility(System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx");
		
		int rowCount = xlutil.getRowCount("UserData");
				
		String[] data = new String[rowCount-1];
		
		for(int i =1;i<rowCount;i++) {
			
			data[i-1] = xlutil.getCellData("UserData", i, 0);
		}
		
		return data;
	}
	
	
	

}
