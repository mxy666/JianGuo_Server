package com.jianguo.servlet.pc;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_use_Money_Bean;

public class CZ_Excel
{
	/**
	 * @���ܣ��ֹ�����һ���򵥸�ʽ��Excel
	 */

	@SuppressWarnings("deprecation")
	public static void init(List<T_use_Money_Bean> list2,String filePath)
	{
		String result="ϵͳ��ʾ��Excel�ļ������ɹ���"; 
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("�û������");
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("ҵ������");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("��������");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("�̼�");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("�绰����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("Ӧ������");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("���ֽ��");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("��������");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("��ע");
		cell.setCellStyle(style);

		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		List list = null;
		try {
			list = list2;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);
			T_use_Money_Bean cz = (T_use_Money_Bean) list.get(i);
			// ���Ĳ���������Ԫ�񣬲�����ֵ
			//row.createCell((short) 0).setCellValue(i+1);
			row.createCell((short) 0).setCellValue(cz.getAdmin());		
			row.createCell((short) 1).setCellValue(cz.getWorkDate());
			row.createCell((short) 2).setCellValue(cz.getMerchant());
			row.createCell((short) 3).setCellValue(cz.getName());
			row.createCell((short) 4).setCellValue(cz.getTel());
			row.createCell((short) 5).setCellValue(cz.getHouldMoney());
			row.createCell((short) 6).setCellValue(cz.getMoneyOut());
			row.createCell((short) 7).setCellValue(cz.getMoneyOutDate());
			row.createCell((short) 8).setCellValue(cz.getRemarks());
		}
		// �����������ļ��浽ָ��λ��
		try
		{
			
			
			FileOutputStream fout = new FileOutputStream(filePath);
			wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	//---------�û���Ϣ---------------------
	public static void initUser(List<T_UserManage_Bean> list2,String filePath)
	{
		String result="ϵͳ��ʾ��Excel�ļ������ɹ���"; 
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		//HSSFSheet hssfSheet =	wb.createSheet("1") ;
		HSSFSheet sheet = wb.createSheet("�û���Ϣ��");
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("Login_Id");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("�Ա�");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("�绰");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("ѧУ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("���");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);	

		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		List list = null;
		try {
			list = list2;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		T_UserManage_Bean cz=new T_UserManage_Bean ();
		for (int i = 0; i < list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);
			 cz = (T_UserManage_Bean) list.get(i);
			// ���Ĳ���������Ԫ�񣬲�����ֵ
			//row.createCell((short) 0).setCellValue(i+1);
			row.createCell((short) 0).setCellValue(cz.getLogin_id());		
			row.createCell((short) 1).setCellValue(cz.getName());
			row.createCell((short) 2).setCellValue(cz.getSex());
			row.createCell((short) 3).setCellValue(cz.getTel());
			row.createCell((short) 4).setCellValue(cz.getSchool());
			row.createCell((short) 5).setCellValue(cz.getCity_id());
			row.createCell((short) 6).setCellValue(cz.getMoney());
		}
		// �����������ļ��浽ָ��λ��
		try
		{						
			FileOutputStream fout = new FileOutputStream(filePath);
			wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//------�����˼�ְ��Ϣ����-------------------
	public static void initJob(List<T_job_Bean> list2,String filePath)
	{
		String result="ϵͳ��ʾ��Excel�ļ������ɹ���"; 
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�
		HSSFWorkbook wb = new HSSFWorkbook();
		//HSSFSheet hssfSheet =	wb.createSheet("1") ;
		//HSSFSheet hssfSheet2 =	wb.createSheet("2") ;
		// �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet("���˼�ְ��¼��");
		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short
		HSSFRow row = sheet.createRow((int) 0);
		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("Job_Id");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		
		cell.setCellValue("�û�����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		
		cell.setCellValue("��ְ����");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("��ַ");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("���");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("��ʼʱ��");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("����ʱ��");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);


		// ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���
		List list = null;
		try {
			list = list2;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		T_job_Bean cz=new T_job_Bean();
		for (int i = 0; i < list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);
			 cz = (T_job_Bean) list.get(i);
			// ���Ĳ���������Ԫ�񣬲�����ֵ
			//row.createCell((short) 0).setCellValue(i+1);
			row.createCell((short) 0).setCellValue(cz.getId());				
			row.createCell((short) 1).setCellValue(cz.getName());
			row.createCell((short) 2).setCellValue(cz.getUserName());
			row.createCell((short) 3).setCellValue(cz.getAddress());
			row.createCell((short) 4).setCellValue(cz.getMoney());
			row.createCell((short) 5).setCellValue(cz.getStart_date());
			row.createCell((short) 6).setCellValue(cz.getStop_date());
		}
		// �����������ļ��浽ָ��λ��
		try
		{						
			//FileOutputStream fout = new FileOutputStream("D:/tomcat/webapps/JianGuo_Server/downLoadFile/Job_"+cz.getUserName()+".xls");
			FileOutputStream fout = new FileOutputStream(filePath);
			wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}