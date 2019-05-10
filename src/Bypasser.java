
import java.io.IOException;
import java.awt.Color;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;



public class Bypasser {


	public static void main(String args[]) {

		String inputFileName = args[0] + ".pdf";
		String newFileName = args[0] + "-BYPASSED.pdf";
		WordsGenerator wordsGenerator = new WordsGenerator();
		String line;

		try {

			// System.out.println(args[0]);

			// convertir pdf dado a imagen
			System.out.println("\n<> Converting pdf...");

			PDDocument document = PDDocument.load(new File(inputFileName));
			PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = "image-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            }
            document.close();

            // escritura del archivo nuevo
            System.out.println("<> Creating new pdf...");

			PDDocument doc = new PDDocument();
			PDPage page1 = new PDPage();
			doc.addPage(page1);

			// insertar nueva imagen en el doc

			PDXObjectImage image = new PDJpeg(doc, new FileInputStream("image-0.png"));
            PDPageContentStream content = new PDPageContentStream(doc, page1);
            content.drawImage(image, 180, 700);
            content.close();

			PDPage page2 = new PDPage();
			doc.addPage(page2);

			PDPageContentStream content = new PDPageContentStream(doc, page2);

			System.out.println("<> Generating unique ID...");

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.WHITE);
			content.newLineAtOffset(50, 60);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();
			
			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.WHITE);
			content.newLineAtOffset(50, 50);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.WHITE);
			content.newLineAtOffset(50, 40);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();

			content.beginText();
			content.setFont(PDType1Font.TIMES_ROMAN, 12);
			content.setNonStrokingColor(Color.WHITE);
			content.newLineAtOffset(50, 30);
			line = wordsGenerator.getWords(10);
			content.showText(line);
			content.endText();

			content.close();

			doc.save(newFileName);
			doc.close();

			System.out.println("<> PDF finished...\n");

		} catch(Exception e) {

			System.out.println("ERROR: " + e);

		}
	}
}