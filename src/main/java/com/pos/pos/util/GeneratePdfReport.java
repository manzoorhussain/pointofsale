package com.pos.pos.util;



import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.pos.pos.model.Bill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;


public class GeneratePdfReport {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);
	
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 25,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	
	 public static byte[] getPDF(List<Bill> currentList,String orderNumber,String totalAmount) {

	        Document document = new Document();
	        
	        
	       
	        
	        
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        List<Bill> billList= currentList;
	        try {

	            PdfPTable table = new PdfPTable(3);
	            table.setWidthPercentage(100);
	            table.setWidths(new int[]{3, 3,3});

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Product", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Quantity", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Price", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            

	            for (Bill bill: billList) {

	                //PdfPCell cell;

	                PdfPCell cell = new PdfPCell(new Phrase(bill.getProdName()));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(bill.getQuantity()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);
	                	
	                
	                cell = new PdfPCell(new Phrase(bill.getPrice()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);
	              
	           }

	            PdfWriter.getInstance(document, out);
	            document.open();
	            
	            addTitlePage(document);
	            
	            addEmptyLine(new Paragraph(), 2);
	           
	            document.add(table);
	            
	            
	           
	          PdfPTable totalTable=addFooterPage(document);
	         
	          
	          document.add(totalTable);
	     
	            
	            

	            document.close();

	        } catch (DocumentException ex) {

	            logger.error("Error occurred: {0}", ex);
	        }

	        return out.toByteArray();
	    }
	 
	 
	 private static void addTitlePage(Document document)
	            throws DocumentException {
	        Paragraph preface = new Paragraph();
	       
	        PdfPTable headerTable=headerGenerate();
	        document.add(headerTable);
	        
	        addEmptyLine(preface, 2);
	      
	      
	        document.add(preface);
	       	    }
	 
	 
	 private static PdfPTable addFooterPage(Document document)
	            throws DocumentException {
		    
		 
		 
	        Paragraph preface = new Paragraph();
	        addEmptyLine(preface, 2);
	        document.add(preface);
	       
	        PdfPTable footerTable=totalGenerate();
	        
	       
	        return footerTable;
	       	    }
	 
	 
	 private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	 
	 

	 
	 private static PdfPTable headerGenerate() throws DocumentException {
	   
		 PdfPTable table = new PdfPTable(2);
         table.setWidthPercentage(100);
         table.setWidths(new int[]{3, 3});
         

         Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

         PdfPCell hcell;
         hcell = new PdfPCell(new Phrase("Company Name", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         table.addCell(hcell);

         hcell = new PdfPCell(new Phrase(new Date().toString(), headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);
         
         return table;
		 
	    }
	 
	 private static PdfPTable totalGenerate() throws DocumentException {
		   
		 PdfPTable table = new PdfPTable(2);
         table.setWidthPercentage(100);
         table.setWidths(new int[]{3, 3});

         Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

         PdfPCell hcell;
         hcell = new PdfPCell(new Phrase("Total", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         table.addCell(hcell);

         hcell = new PdfPCell(new Phrase("6000", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);
         

         
         hcell = new PdfPCell(new Phrase("Cash", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         table.addCell(hcell);

         hcell = new PdfPCell(new Phrase("7000", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);
         
         hcell = new PdfPCell(new Phrase("Return", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         
         table.addCell(hcell);

         hcell = new PdfPCell(new Phrase("1000", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);
         

         
         
         return table;
		 
	    }
	
}
