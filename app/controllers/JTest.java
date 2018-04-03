package controllers;

import Util_Rpt.ReadExcel;
import com.aspose.cells.*;
import com.avaje.ebean.Ebean;
import com.ibm.icu.impl.LocaleDisplayNamesImpl;
import entities.TV;
import entities.WaterFallLteData;
import play.mvc.Controller;
import sun.security.pkcs11.wrapper.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dost Muhammad on 3/13/2018.
 */
public class JTest extends Controller {


    public static void main(String args[]){
            String path="";
        Map<String,Integer> m = new HashMap<String,Integer>();
        List<WaterFallLteData> lstWfall=new ArrayList<>();
        List<TV> lstTV=new ArrayList<>();
        try {
            File file=new File(path);
            Workbook wb;
            Workbook wb_tv;
            wb = new Workbook("D:\\Conure Automation Process\\WaterfALL-2018-01-26.xlsb");
            wb_tv = new Workbook("D:\\Conure Automation Process\\ENMT 3 _Turf_Vendor_Tim Kurtz_1_16_18.xls");
            //wb.Open("f:\test\MyExcelFile.xls");
            Worksheet ws;
            Worksheet ws_tv;
            ws = wb.getWorksheets().get("LTE_Data");
            ws_tv = wb_tv.getWorksheets().get(0);

            //lstWfall=ReadExcel.getwaterfall(ws,2);
            lstTV=ReadExcel.getLst_Tv(ws_tv,23);
            Ebean.saveAll(lstTV);

            System.out.print("lst size is "+lstTV.size());

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void  dtable(Worksheet worksheet,int rowNum){
        int trows = worksheet.getCells().getMaxDataRow();
        int tcols = worksheet.getCells().getMaxDataColumn();
        //LocaleDisplayNamesImpl.DataTable dt = worksheet.getCells().ex.ExportDataTable(0, 0,trows, tcols, true);

    }
    public static void  dispp(Worksheet worksheet,int rowNum){

        //Access the Maximum Display Range
        Range range = worksheet.getCells().getMaxDisplayRange();
        int tcols = range.getColumnCount();
        int trows = range.getRowCount();

        System.out.println("Total Rows:" + trows);
        System.out.println("Total Cols:" + tcols);

        RowCollection rows = worksheet.getCells().getRows();


        for (int i = 2 ; i < 5 ; i++)
        {
            for (int j = 0 ; j < 5 ; j++)
            {
                Cell cell=worksheet.getCells().get(i,j);
                if(cell.getValue() !=null) {
                    String val = worksheet.getCells().get(i, 0).getValue().toString();
                    String val2 = worksheet.getCells().get(i, 1).getValue().toString();
                    if (val != null) {
                        System.out.println(""+val+ " d"+val2);

                    }
                }

            }
            System.out.println("");
        }



    }

    private static void ColumnNameToIndex(String s) {

    }

    public static Map disp(Worksheet worksheet,int rowNum){
        Map<String,Integer> m = new HashMap<String,Integer>();
        List<WaterFallLteData> lst=new ArrayList<>();

        int trows = worksheet.getCells().getMaxDataRow()+1;
        int tcols = worksheet.getCells().getMaxDataColumn()+1;
        int rowNumm=rowNum+1;
        Map <String,Integer> map=new HashMap<String,Integer>();
        map= ReadExcel.getColumnHeadIndex(worksheet,1);

        //lst=ReadExcel.getwaterfall(worksheet,2);
        System.out.println("lst "+lst.size());

        //RowCollection rows = cells.getRows();

        RowCollection rows = worksheet.getCells().getRows();
        Cell lastCell = worksheet.getCells().endCellInColumn((short)0);


            int count =0;

            for(int row =2; row<=lastCell.getRow()+1; row++)

            {



                Row row1 = worksheet.getCells().getRow(row);
                if (row1 != null) {
                    count++;
                   // Column column=worksheet.getCells().getColumns().getColumnByIndex(map.get("PACE_NUMBER"));
                    /*for(int k=0;k<2;k++){
                        Cell cellk = worksheet.getCells().get(row, k);
                        System.out.println(cellk.getStringValue());
                    }*/

                    Cell cell = worksheet.getCells().get(row, 0);
                    Cell cell2 = worksheet.getCells().get(row, 1);


                    //System.out.println(cell.getStringValue() + "  " + cell2.getStringValue());
                    //System.out.println(cell.getStringValue() + "  " + cell2.getStringValue());
                    //System.out.println(" column name"+cell.getColumn());

                }
            }

        System.out.print("tcount"+count);

        return m;
    }
}
