
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
		int totalNumOfPages=0;
		String line;

		try {

			// System.out.println(args[0]);

// convertir pdf dado a imagenes
			System.out.println("\n<> Converting pdf...");

			PDDocument document = PDDocument.load(new File(inputFileName));
			totalNumOfPages = document.getNumberOfPages();
			PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                String fileName = "image-" + page + ".png";
                ImageIOUtil.writeImage(bim, fileName, 300);
            }
            document.close();

// resize images to bounds of pdf
            System.out.println("<> Resizing pdf...");

            String imgName;

            for (int img=0; img < totalNumOfPages; img++) {
            	imgName = "image-" + img + ".png";
            	File input = new File(imgName);
            	BufferedImage image = ImageIO.read(input);
	        	BufferedImage resized = resize(image, 792, 612);
	        	File output = new File(imgName);
		        ImageIO.write(resized, "png", output);
            }

// escritura del archivo nuevo con las imagenes
            System.out.println("<> Creating new pdf...");

			PDDocument doc = new PDDocument();
			ArrayList<PDPage> pages = new ArrayList<PDPage>();

			for (int x=0; x<totalNumOfPages; x++) {
				pages.add(new PDPage());
			}

			for (int y=0; y<totalNumOfPages; y++) {
				doc.addPage(pages.get(y));
			}

			imgName = "";
			PDImageXObject pdImage;
			PDPageContentStream contents;

// insertar imagenes por pagina

			for (int imgNum=0; imgNum<totalNumOfPages; imgNum++) {
				imgName = "image-" + imgNum + ".png";
				pdImage = PDImageXObject.createFromFile(imgName,doc);
	            contents = new PDPageContentStream(doc, pages.get(imgNum));
	            contents.drawImage(pdImage, 0, 0);
	            contents.close();
			}
			
// putting 40 random words
			PDPage finalPage = new PDPage();
			doc.addPage(finalPage);

			PDPageContentStream content = new PDPageContentStream(doc, finalPage);

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

			System.out.println("<> PDF finished successfully.\n");

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






