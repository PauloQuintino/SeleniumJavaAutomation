package Evidence;

import Page.BasePage;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import datafiles.TestDataReader;
import io.cucumber.java.ca.Cal;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import static Core.DriverFactory.getDriver;

public class EvidenceGenerator extends BasePage {

    private String fileOut;
    private PdfWriter writer;
    private Image image;
    private PdfPTable table;
    private PdfPCell cell;
    private Document document = null;
    private PdfReader reader = null;
    private TestDataReader data = new TestDataReader();
    private String status;
    private static LinkedList<String> descriptions = new LinkedList<>();
    private static LinkedList<Image> images = new LinkedList<>();

    public EvidenceGenerator() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void takeScreenshot(String description){
        byte[] input = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            image = Image.getInstance(input);
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
        image.setAlignment(1);

        descriptions.add(description);
        images.add(image);
        System.out.println("PRINT...");
    }

    public void createHeader() {
        table = new PdfPTable(2);

        //linha 1
        table.addCell("CT");
        table.addCell(data.getCtKey() + " - " + data.getCtName());

        //linha 2
        table.addCell("Usuário");
        table.addCell(System.getProperty("user.name"));

        //linha 3
        table.addCell("Sistema Operacional");
        table.addCell(System.getProperty("os.name"));

        //linha 4
        table.addCell("Status");
        cell = new PdfPCell();
        Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        f1.setColor(BaseColor.GREEN);
        Font f2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        f2.setColor(BaseColor.RED);
        cell.addElement(new Phrase(getStatus(), getStatus().equals("PASSED") ? f1 : f2));
        table.addCell(cell);

        try {
            document.add(table);
            System.out.println("CRIADO TABELA...");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void insertImages() {
        try {
            for (int i = 0; i < images.size(); i++) {
                document.newPage();
                document.add(new Paragraph(descriptions.get(i)));
                document.add(images.get(i));
                System.out.println("Add print " + i);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void resetImages(){
        images.removeAll(images);
        descriptions.removeAll(descriptions);
    }

    public String getTestDateTime(){
//        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
        return sdf.format(calendar.getTime());
    }

    public void createEvidence(){
        String filePath = "src/test/resources/Evidences/";
        fileOut = filePath + data.getCtKey() + " - " + data.getCtName() + " - " + getTestDateTime() + ".pdf";
        document = new Document();
        try {
            if (!document.isOpen()) {
                FileOutputStream fos = new FileOutputStream(fileOut);
                writer = PdfWriter.getInstance(document, fos);
                writer.open();
                document.open();
                document.setMargins(20, 20, 80, 20);
                createHeader();
                insertImages();
                resetImages();
                System.out.println("DOCUMENTO CRIADO....");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveEvidence() {
        createEvidence();
        document.close();
        writer.close();
    }

}
