package com.example.demo.bean;
/**
 * 
* <p>Title: ExportBean</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月2日
 */
public class ExportBean {
	/**
	 * 导出文件名
	 */
	private String fileName;
	/**
	 * 文件表头
	 */
	private String[] head;
	/**
	 * 当导出已有数据源时 数组存的是数据源数据属性 
	 * 当通过SQL导出时数组存的是数据库字段
	 */
	private String[] field;
	/**
	 * 导出文件类型
	 */
	private String exportType;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the head
	 */
	public String[] getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(String[] head) {
		this.head = head;
	}

	/**
	 * @return the field
	 */
	public String[] getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String[] field) {
		this.field = field;
	}

	/**
	 * @return the exportType
	 */
	public String getExportType() {
		return exportType;
	}

	/**
	 * @param exportType the exportType to set
	 */
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	
}
