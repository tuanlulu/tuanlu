package getdata;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LanAnhTest {
	private OkHttpClient client = new OkHttpClient();
	
	public String doGetRequest (String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	public WebElement getElement () throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("http://priceboard1.vcsc.com.vn/vcsc/vn30");
		WebElement table = driver.findElement(By.xpath("//*[@id=\"stockTable\"]/tbody"));
		System.out.println("GOT!");
		return table;
	}
	
	public void getData() throws InterruptedException, IOException {
		WebElement table = this.getElement();
		FileWriter out = null;
		try {
			out = new FileWriter(new File("D:/datatable.txt"));

			List<WebElement> rows_table = table.findElements(By.tagName("tr"));
			int rows_count = rows_table.size();
			System.out.println(rows_count);
			for (int i = 2; i < rows_count; i++) {
				List <WebElement> columns_row = rows_table.get(i).findElements(By.tagName("td"));
				int columns_count = columns_row.size();
				String name = columns_row.get(0).findElement(By.tagName("div")).findElement(By.tagName("span")).getText();
				//System.out.println(name);
				out.write(name + "\t");
				
				for (int j = 1; j < columns_count; j++) {
					if(j==16 || j==17) continue;
					String color = columns_row.get(j).getAttribute("class");
					String cell_text = columns_row.get(j).getText();
					//System.out.println(cell_text);
					out.write(cell_text + "\t" + color + "\t");
				}
				out.write("\n");
			}
		} finally {
			if (out != null) out.close();
		}
	}
	
	public static void main (String [] args) throws InterruptedException, IOException {
		LanAnhTest app = new LanAnhTest();
		app.getData();
	}
	
}
