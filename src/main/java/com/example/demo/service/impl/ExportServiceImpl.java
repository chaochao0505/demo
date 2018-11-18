package com.example.demo.service.impl;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.demo.bean.ExportBean;
import com.example.demo.service.IExportService;
import com.example.demo.utils.DateUtil;


@Service
public class ExportServiceImpl implements IExportService{
	private static Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);
	
	@Override
	public void exportData(List<?> source, ExportBean exportBean, HttpServletResponse response) {
		logger.debug("导出Excel数据");
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet(exportBean.getFileName());
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = workbook.createCellStyle();
		// 第五步 设置表头
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		if(exportBean.getHead() != null && exportBean.getHead().length>0){
			int index=0;
			for(String head:exportBean.getHead()){
				HSSFCell cell = row.createCell(index);
				cell.setCellValue(head);
				cell.setCellStyle(style);
				index++;
			}
		}
		// 第六步 填充数据
		if(source != null){
			for(int index=0;index < source.size();index++){
				Object obj = source.get(index);
				row = sheet.createRow(index+1);
				int idx=0;
				for(String field:exportBean.getField()){
					Object fieldVal = invokeVal(field, obj);
					String cellVal = fieldVal == null ?null:String.valueOf(fieldVal);
					row.createCell(idx).setCellValue(cellVal);
					idx++;
				}
			}
		}
		// 第七部  导出
		try {
			logger.info("开始导出数据");
			String fileName=java.net.URLEncoder.encode(exportBean.getFileName(), "UTF8");
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setContentType("application/octet-stream;charset=utf-8");
			/*response.setHeader("Content-disposition",
					"attachment; filename=" + fileName + ".xls");*/
			response.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName  + ".xls");
			response.setContentType("application/msexcel");
			workbook.write(output);
			output.close();
		} catch (Exception e) {
			logger.error("发生数据导出异常"+e.getMessage());
		}
		
	}

	/**
	 * 查找对象中对应field的值
	 * @param field
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object invokeVal(String field,Object obj){
		if(obj instanceof Map){
			Map<String,Object> mapData = (Map<String, Object>) obj;
			Object fieldVal =  mapData.get(field);
			if(fieldVal != null && fieldVal instanceof Date){
				return DateUtil.formatDate((Date)fieldVal, DateUtil.TIME_HAVINTERVAL);
			}else{
				return fieldVal;
			}
		}else{
			Method method = ReflectionUtils.findMethod(obj.getClass(), "get"+field.substring(0, 1).toUpperCase()+field.substring(1));
			Object fieldVal =  ReflectionUtils.invokeMethod(method, obj);
			Field proField = ReflectionUtils.findField(obj.getClass(), field);
			if(fieldVal != null && proField.getType().isAssignableFrom(Date.class)){
				JSONField annotation = proField.getAnnotation(JSONField.class);
				String dateFormat = annotation.format();
				return DateUtil.formatDate((Date)fieldVal, dateFormat);
			}else{
				return fieldVal;
			}
		}
	}

}
