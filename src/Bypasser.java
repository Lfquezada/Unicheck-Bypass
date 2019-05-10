
import java.io.IOException;
import java.awt.Color;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import java.util.ArrayList;


public class Bypasser {


	public static void main(String args[]) {


		String fileName = "newPDF.pdf";
		WordsGenerator wordsGenerator = new WordsGenerator();
		String line;

		try {

			PDDocument doc = new PDDocument();
			PDPage page = new PDPage();
			doc.addPage(page);

			PDPageContentStream content = new PDPageContentStream(doc, page);

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.RED);
			content.newLineAtOffset(50, 60);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();
			
			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.RED);
			content.newLineAtOffset(50, 50);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.RED);
			content.newLineAtOffset(50, 40);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.RED);
			content.newLineAtOffset(50, 30);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();

			content.close();

			doc.save(fileName);
			doc.close();

		} catch(Exception e) {

			System.out.println("ERROR: " + e);

		}
	}
}