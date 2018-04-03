package controllers;

import Util_Rpt.*;
import Util_Rpt.Validation;
import com.aspose.cells.*;
import com.avaje.ebean.Ebean;
import entities.TV;
import org.h2.command.dml.Insert;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Abbas Qamar on 3/19/2018.
 */
public class ImportExcel extends Controller {

    public Result connect() {

        String status="";
        try {

            Class.forName("org.postgresql.Driver");

            Connection c = DriverManager.getConnection(

                    DbContract.HOST + DbContract.DB_NAME,

                    DbContract.USERNAME,

                    DbContract.PASSWORD);


            System.out.println("DB connected");
            status="Db Connected";


            Statement st = null;
            ResultSet rs = null;
            st= c.createStatement();
            String qs = "CREATE TABLE  IF NOT EXISTS films (\n" +
                    "    code        char(5) CONSTRAINT firstkey PRIMARY KEY,\n" +
                    "    title       varchar(40) NOT NULL,\n" +
                    "    did         integer NOT NULL,\n" +
                    "    date_prod   date,\n" +
                    "    kind        varchar(10),\n" +
                    "    len         interval hour to minute\n" +
                    ");\n";



            // String qs1 = "SELECT * FROM test";
            rs = st.executeQuery(qs);
            c.close();


        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
            status="not Connected";
        }

        return ok(views.html.RecordSAved.render());
    }



    public Result ReadHeaders() throws SQLException {

        ReadExcelFiles obj=new ReadExcelFiles();
        Util_Rpt.Validation gg= new Validation();

        String SAMPLE_XLSX_FILE_PATH = "D:\\Conure Automation Process\\SiteMaster_UseID_W-LOSANGELES.csv";


        String ok=obj.ReadHeaders(SAMPLE_XLSX_FILE_PATH,0,0);
    //    Boolean ok= gg.Step3();

      String result=ok;
    /*   ReadExcelFiles obj=new ReadExcelFiles();


        Worksheet ws_tv;
        int count = 0;
        String SheetName="";
        Workbook workbook = null;
        int maxDataRow=0;
        int maxDataColumn=0;
        StringBuilder createTableQuery = new  StringBuilder();
        StringBuilder columns  = new StringBuilder();
        StringBuilder values  = new StringBuilder();
        String InsertBatchQuery="";


        ArrayList lstTV = ne w ArrayList<>();
        Connection Conn=obj.Connections();
        if(Conn !=null) {

            String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\Abbas Qamar\\Desktop\\SiteMaster_UseID_W-LOSANGELES.CSV";


            try {
                workbook = new Workbook(SAMPLE_XLSX_FILE_PATH);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ws_tv = workbook.getWorksheets().get(0);
            int format= workbook.getFileFormat();


            if(format != 1)  // 1 mean its CSV Format
            {
                SheetName="_"+ws_tv.getName().replace("-","_");

            }
            else
            {

                SheetName=   workbook.getFileName();
                File f = new File(SheetName);

                SheetName=f.getName().replace("-","_").replace(".CSV","");;
            }


            System.out.println(" SheetName :" + SheetName);
            maxDataRow = ws_tv.getCells().getMaxDataRow();
            maxDataColumn = ws_tv.getCells().getMaxDataColumn();


            //Get the AA column index. (Since "Status" is always @ AA column.

            values.append(" values (");

            // Getting First Row header as Column
            for (int i = 0; i <= maxDataColumn; i++) {
            int row = 0;
            int col = i;

            // Access the cell by row and col indices
            Cell cell = ws_tv.getCells().getCell(row, col);
             columns.append("\""+cell.getStringValue()+"\""+",");
             lstTV.add(cell.getStringValue());
             values.append("?"+",");
             createTableQuery.append("\""+cell.getStringValue()+"\""+" varchar(2000),");

        }

            int index= createTableQuery.lastIndexOf(",");
            createTableQuery=createTableQuery.deleteCharAt(index);

            int index1= columns.lastIndexOf(",");
            columns=columns.deleteCharAt(index1);

            int index2= values.lastIndexOf(",");
            values=values.deleteCharAt(index2);

            values.append(");");


            Boolean IsTableCreated=obj.CreateTable(createTableQuery,SheetName,Conn,lstTV);

*/


          /*  StringBuilder InsertSql = new StringBuilder();

            InsertSql.append( "insert into "+SheetName+" ("+columns+")");
            InsertBatchQuery  = InsertSql.toString()+values.toString();
            PreparedStatement ps = Conn.prepareStatement(InsertBatchQuery);



            for (int i = 1; i <= maxDataRow; i++) {
                int row = i;

                for (int j = 0; j <= maxDataColumn; j++) {

                    int col = j;
                    Cell cell = ws_tv.getCells().getCell(row, col);
                    System.out.println(cell.getName() + ": " + cell.getStringValue());
                    lstTV.add(cell.getStringValue());

                    int k = j;
                    k = k + 1;
                    String val =cell.getStringValue();
                    if(cell.getStringValue().length() <=0){
                        val= " ";


                    }

                    ps.setString(k,val);



                }
                ps.addBatch();

            }

            ps.executeBatch();

            ps.close();
            Conn.close();


            System.out.println(" InsertSql %" + InsertBatchQuery);

        }*/
      //  return ok(views.html.RecordSAved.render());
        return ok(result);

    }


}
