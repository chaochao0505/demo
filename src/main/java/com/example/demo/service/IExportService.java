package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.bean.ExportBean;
/**
 * 
* <p>Title: IExportService</p>  
* <p>Description: </p>  
* @author koko  
* @date 2018年11月2日
 */
public interface IExportService {
	void exportData(List<?> source,ExportBean exportBean,HttpServletResponse response);
}
