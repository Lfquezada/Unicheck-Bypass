
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;




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

            // resize image to bounds of pdf
            System.out.println("<> Resizing pdf...");

            File input = new File("image-0.png");
	        BufferedImage image = ImageIO.read(input);

	        BufferedImage resized = resize(image, 792, 612);

	        File output = new File("image-0.png");
	        ImageIO.write(resized, "png", output);

            // escritura del archivo nuevo
            System.out.println("<> Creating new pdf...");

			PDDocument doc = new PDDocument();
			PDPage page1 = new PDPage();
			doc.addPage(page1);

			// insertar nueva imagen en el doc

			PDImageXObject pdImage = PDImageXObject.createFromFile("image-0.png",doc);
            PDPageContentStream contents = new PDPageContentStream(doc, page1);
            contents.drawImage(pdImage, 0, 0);
            contents.close();

			
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
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}






