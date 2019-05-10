
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Bypasser {


	public static void main(String args[]) {


		String fileName = "EmptyPDF.pdf";

		try {

			PDDocument doc = new PDDocument();
			doc.addPage(new PDPage());

			doc.save(fileName);
			doc.close();

		} catch(Exception e) {

			System.out.println("ERROR: " + e);

		}
	}
}