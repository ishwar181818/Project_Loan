package com.man.serviceimpl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.man.model.Creditlimit;
import com.man.model.Enquiry;
import com.man.model.LoanApplication;
import com.man.model.SanctionLetter;
import com.man.repo.LoanApplicationRepo;
import com.man.repo.Repo;
import com.man.servicei.ServiceI;
import org.springframework.core.io.ByteArrayResource;

import jakarta.mail.internet.MimeMessage;
@Service
public class ServiceImpl implements ServiceI {
	
	private String enquirystatus="Approved";
	@Autowired
	LoanApplicationRepo lar;

	@Autowired
	Repo rr;
	
	@Autowired
	JavaMailSender sender;
	
	@Autowired
	RestTemplate rt;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	@Override
	public List<LoanApplication> getAllData() {
		
		
		
		List<LoanApplication>l=lar.findAll();
		
		
		
		return l;
	}
	@Override
	public void generatecreditLimit(Creditlimit cl,int cid) {
		
		List<LoanApplication>l = getAllData();
		
		for(LoanApplication la : l)
			
		{
			if(cid == la.getCustomerid())
				
			{
				
				SanctionLetter s1 = new SanctionLetter();
				
				s1= la.getSanctionletter();
				
				cl.setSanctionletter(s1);
				
				
				
				rr.save(cl);
				
			}
			
			
		}
		
		
		
		
		
		
		
	}
	@Override
	public Creditlimit generateSactionletter(int creditid, SanctionLetter sanctionLetter) {
		
		System.out.println("Executing");
		
		Optional<Creditlimit>op=rr.findById(creditid);
		
		if(op.isPresent())
			
		{
			Creditlimit cl=op.get();
			
			SanctionLetter sl = new SanctionLetter();
			
			
			
			List<LoanApplication>l=getAllData();
			
			for(LoanApplication la : l)
				
			{
			
				
				if (la.getCustomermobileno()== sanctionLetter.getContactdetails())
					
				{
					sl = la.getSanctionletter();
					
					sl.setApplicantname(la.getCustomername());
					
					sl.setContactdetails(la.getCustomermobileno());
					
				}
				
			}
			
			sl.setLoanamtsanctioned(cl.getCreditlimit());
			
			sl.setInteresttype(sanctionLetter.getInteresttype());
			
			sl.setRateofinterest(cl.getInterestrate());
			
			sl.setLoantenureinmonth(cl.getRequiredtenure());
			
			sl.setModeofpayment(sanctionLetter.getModeofpayment());
			
			sl.setRemarks(sanctionLetter.getRemarks());
			
		    sl.setTermscondition(sanctionLetter.getTermscondition());
		
		    sl.setStatus(sanctionLetter.getStatus());
		    
		    cl.setSanctionletter(sl);
		    
		    
			
		  
			
			
		
		
		String title = "ABC Finance Ltd.";
		
		Document document = new Document(PageSize.A4);
		
		String content1 = "\n\n Dear " + cl.getSanctionletter().getApplicantname() + ","
                + "\nABC Finance Ltd. is happy to inform you that your loan application has been approved. "
                + "\nPlease find below the terms and conditions associated with the loan: "
                + "\n\n1. Loan Amount Sanctioned: ₹ " + sl.getLoanamtsanctioned()
                + "\n2. Interest Rate: " + sl.getRateofinterest() + " %"
                + "\n3. Loan Tenure: " + sl.getLoantenureinmonth() + " months"
                + "\n4. Mode of Payment: " + sl.getModeofpayment()
                + "\n5. Repayment Terms: Please refer to the repayment schedule attached."
                + "\n6. Prepayment Options: You may prepay the loan subject to terms."
                + "\n7. Late Payment Fees: A fee of ₹ [3000] will be charged for any late payments beyond [7 days]."
                + "\n8. Default Clause: In case of default, ABC Finance Ltd. reserves the right to initiate legal proceedings."
                + "\n\nPlease ensure that you understand these terms before proceeding with the loan.";

        // Content 2 (End)
        String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory "
                + "and that it will help you meet your financial needs.\n\nIf you have any questions or need any assistance regarding your loan, "
                + "please do not hesitate to contact us.\n\nWe wish you all the best and thank you for choosing us."
                + "\n\nSincerely,\n\n" + "Ishwar Harbade (Credit Manager)";
		

ByteArrayOutputStream opt = new ByteArrayOutputStream();

PdfWriter.getInstance(document, opt);
document.open();
 //C:\Users\Lenovo\Desktop\CJC\project Task\images.png

//C:\\Users\\prati\\OneDrive\\Desktop\\bike-logo.png
Image img = null;

try {
	img = Image.getInstance("C:\\Users\\Lenovo\\Desktop\\CJC\\project Task\\images.png");
	img.scalePercent(50, 50);
	img.setAlignment(Element.ALIGN_RIGHT);
	document.add(img);

} catch (BadElementException e1) {
	e1.printStackTrace();
} catch (IOException e1) {
	e1.printStackTrace();
}

Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
    
Paragraph titlepara = new Paragraph(title, titlefont);
titlepara.setAlignment(Element.ALIGN_CENTER);
document.add(titlepara);

Font titlefont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
Paragraph paracontent1 = new Paragraph(content1, titlefont2);
document.add(paracontent1);

PdfPTable table = new PdfPTable(2);
table.setWidthPercentage(100f);
table.setWidths(new int[] { 2, 2 });
table.setSpacingBefore(10);



PdfPCell cell = new PdfPCell();
cell.setBackgroundColor(CMYKColor.WHITE);
cell.setPadding(5);

Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
font.setColor(5, 5, 161);

Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
font.setColor(5, 5, 161);

cell.setPhrase(new Phrase("Loan amount Sanctioned", font));
table.addCell(cell);

cell.setPhrase(new Phrase(String.valueOf("₹ " + sl.getLoanamtsanctioned()),
		font1));
table.addCell(cell);

cell.setPhrase(new Phrase("loan tenure in months", font));
table.addCell(cell);

cell.setPhrase(new Phrase(String.valueOf(sl.getLoantenureinmonth()), font1));
table.addCell(cell);

cell.setPhrase(new Phrase("interest rate", font));
table.addCell(cell);


cell.setPhrase(
		new Phrase(String.valueOf(sl.getRateofinterest()) + " %", font1));
table.addCell(cell);

cell.setPhrase(new Phrase("Sanction letter generated Date", font));
table.addCell(cell);

Date date = new Date();
String curdate = date.toString();
sl.setSanctiondate(curdate);
cell.setPhrase(
		new Phrase(String.valueOf(sl.getSanctiondate()), font1));
table.addCell(cell);

double monthlyEmiAmount = calculateEMI(
        sl.getLoanamtsanctioned(),
        sl.getRateofinterest(),
        sl.getLoantenureinmonth()
    );
    sl.setMonthlyemiamount(monthlyEmiAmount);
    
    double PayableAmountwithinterest = monthlyEmiAmount *sl.getLoantenureinmonth();
    
    cell.setPhrase(new Phrase("Monthly EMI", font));
	 table.addCell(cell);
	 cell.setPhrase(new Phrase(String.format("₹ %.2f", monthlyEmiAmount), font1));
	 table.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Payable Amount with Interest", font));
	 table.addCell(cell);

	 cell.setPhrase(new Phrase(String.format("₹ %.2f", PayableAmountwithinterest), font1));
	 table.addCell(cell);
	 
	 document.add(table);

		Font titlefont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
		Paragraph paracontent2 = new Paragraph(content2, titlefont3);
		document.add(paracontent2);
		document.close();
		
		ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
		byte[] bytes = byt.readAllBytes();
		
		cl.getSanctionletter().setSanctionLetter(bytes);

		cl.setMonthlyemi(monthlyEmiAmount);

		return rr.save(cl);
		
		
		
		}
		
		
		
		else {
			
			throw new RuntimeException("credt id Not Present");
			
			
		}
		
		
		
		
		
		
	}
	
	private double calculateEMI(Double loanAmount, float annualInterestRate, Integer tenureInMonths) {
	    double monthlyInterestRate = annualInterestRate / (12 * 100); 
	    double emi;
	    if (annualInterestRate == 0) {
	        emi = loanAmount / tenureInMonths; 
	    } else {
	        emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureInMonths)) /
	              (Math.pow(1 + monthlyInterestRate, tenureInMonths) - 1);
	    }
	    return emi;
	}
	@Override
	public LoanApplication getCustomerData(Integer customerId) {
		
		
		Optional<LoanApplication>op=lar.findById(customerId);
		
		if(op.isPresent())
			
		{
			LoanApplication loan =op.get(); 
			
			
			
			MimeMessage mimemessage = sender.createMimeMessage();
			
			byte[] sanctionLetter = loan.getSanctionletter().getSanctionLetter();

			try {
				MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimemessage, true);
				mimemessageHelper.setFrom(fromEmail);
				mimemessageHelper.setTo("yogtej.harbade@gmail.com");
				
				mimemessageHelper.setCc("ishwarharbade550@gmail.com");
				mimemessageHelper.setSubject("ABC Finance Ltd. Sanction Letter");
				String text = "Dear " + loan.getCustomername()
						+ ",\n" + "\n"
						+ "This letter is to inform you that we have reviewed your request for a credit loan . We are pleased to offer you a credit loan of "
						+ loan.getSanctionletter().getLoanamtsanctioned() + " for "
						+ loan.getSanctionletter().getLoantenureinmonth() + ".\n" + "\n"
						+ "We are confident that you will manage your credit loan responsibly, and we look forward to your continued business.\n"
						+ "\n"
						+ "Should you have any questions about your credit loan, please do not hesitate to contact us.\n"
						+ "\n" + "Thank you for your interest in our services.";

				mimemessageHelper.setText(text);

				mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter));
				sender.send(mimemessage);

			} catch (Exception e) {
				System.out.println("Email Failed to Send!!!!!!");
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		return null;
	}
	@Override
	public void AcceptedOrRejectedStatus(int customerid, String status) {
		
		
		Optional<LoanApplication>op=lar.findById(customerid);
		
		if(op.isPresent())
			
		{
			
			
			
			  LoanApplication loan= op.get();
			  
			  loan.getSanctionletter().setStatus(status);
			  
			 lar.save(loan);
			
			
		}
		
		
	}
	@Override
	public String setloanstatus(int customerid, String loanstatus) {
		
		
		Optional<LoanApplication>op=lar.findById(customerid);
		
		if(op.isPresent())
			
		{
			LoanApplication loan=op.get();
			
			
			if(loan.getSanctionletter().getLoanamtsanctioned()!= 0)
				
			{
				loan.setLoanstatus(loanstatus);
				
				
				lar.save(loan);
			}
			
			
			
		}
		
		else {
			throw new RuntimeException("Customer Not Present");
			
			
		}
		
		
		return "loan Status Updated";
	}
	

	
	

}
