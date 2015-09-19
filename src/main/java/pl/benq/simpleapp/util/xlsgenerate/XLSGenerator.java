package pl.benq.simpleapp.util.xlsgenerate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import pl.benq.simpleapp.model.PhoneViewDescriptor;

@SuppressWarnings("deprecation")
public class XLSGenerator extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<PhoneViewDescriptor> phonesXLS = (List<PhoneViewDescriptor>) model.get("phones");
		CellStyle defaultStyle = getStyle(workbook);
		CellStyle dataStyle = getDataStyle(workbook);
		HSSFSheet sheet = workbook.createSheet("Client's numbers");
		sheet.setDefaultColumnWidth(30);		
		setHeader(sheet, defaultStyle);
		int insertedRows = setDataContent(phonesXLS, sheet);
		setInfo(sheet, insertedRows, phonesXLS.size(), defaultStyle,dataStyle);

	}

	
	private CellStyle getDataStyle(HSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		return style;
	}


	private CellStyle getStyle(HSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		style.setFillForegroundColor(HSSFColor.AQUA.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFBorderFormatting.BORDER_MEDIUM);
		style.setBorderTop(HSSFBorderFormatting.BORDER_MEDIUM);
		style.setBorderLeft(HSSFBorderFormatting.BORDER_MEDIUM);
		style.setBorderRight(HSSFBorderFormatting.BORDER_MEDIUM);

		return style;
	}

	private int setDataContent(List<PhoneViewDescriptor> phonesXLS, HSSFSheet sheet) {
		int rowNum = 1;
		for (PhoneViewDescriptor phone : phonesXLS) {
			HSSFRow dataRow = sheet.createRow(rowNum++);
			dataRow.createCell(0).setCellValue(phone.getOwnerName());
			dataRow.createCell(1).setCellValue(phone.getOnwerSurname());
			dataRow.createCell(2).setCellValue(phone.getNumber());
			dataRow.createCell(3).setCellValue(phone.getType());
		}
		
		return rowNum-1;
	}

	private void setHeader(HSSFSheet sheet, CellStyle style) {
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Name");
		row.getCell(0).setCellStyle(style);
		row.createCell(1).setCellValue("Surname");
		row.getCell(1).setCellStyle(style);
		row.createCell(2).setCellValue("Phone Type");
		row.getCell(2).setCellStyle(style);
		row.createCell(3).setCellValue("Number");
		row.getCell(3).setCellStyle(style);
	}

	private void setInfo(HSSFSheet sheet,int insertedRows,int numberOfPhones,CellStyle style,CellStyle dataStyle) {
		int startCell = insertedRows/2;
		HSSFRow headerRow = sheet.getRow(startCell);
		headerRow.createCell(4).setCellValue("Num. of phones");
		headerRow.getCell(4).setCellStyle(style);
		headerRow.createCell(5).setCellValue("Data");
		headerRow.getCell(5).setCellStyle(style);
		
		Calendar calendar = new GregorianCalendar();
		
		HSSFRow dataRow = sheet.getRow(startCell+1);
		dataRow.createCell(4).setCellValue(numberOfPhones);
		dataRow.createCell(5).setCellValue(calendar);
		dataRow.getCell(5).setCellStyle(dataStyle);	
		
	}

}
