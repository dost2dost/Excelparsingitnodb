package Util_Rpt;

import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.sun.org.apache.xpath.internal.operations.Bool;
import play.libs.Json;
import play.mvc.Result;

import java.io.File;
import java.sql.*;
import java.io.File;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Abbas Qamar on 3/20/2018.
 */
public class ReadExcelFiles {


    public  Connection Connections()
    {
        Connection con=null;
        String status="";
        try {

            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(

                    DbContract.HOST + DbContract.DB_NAME,

                    DbContract.USERNAME,

                    DbContract.PASSWORD);


            System.out.println("DB connected");
            status="Db Connected";


           /* Statement st = null;
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
             c.close();*/


        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
            status="not Connected";
        }

        return con;
    }

    public String ReadHeaders(String FilePath,int SheetNo,int nRow) throws SQLException {
        ReadExcelFiles obj=new ReadExcelFiles();


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


        ArrayList lstTV = new ArrayList<>();
        Connection Conn=obj.Connections();
        if(Conn !=null) {

            String SAMPLE_XLSX_FILE_PATH =FilePath;


            try {
                workbook = new Workbook(SAMPLE_XLSX_FILE_PATH);
            } catch (Exception e) {
                e.printStackTrace();
            }


            ws_tv = workbook.getWorksheets().get(SheetNo);
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

            SheetName= " \""+SheetName+"\" ";
            System.out.println(" SheetName :" + SheetName);
            maxDataRow = ws_tv.getCells().getMaxDataRow();
            maxDataColumn = ws_tv.getCells().getMaxDataColumn();

            //  Cells cells = ws_tv.getCells();
            //Get the AA column index. (Since "Status" is always @ AA column.

            values.append(" values (");

            // Getting First Row header as Column
            for (int i = 0; i <= maxDataColumn; i++) {
                int row = nRow;
                int col = i;

                // Access the cell by row and col indices
                Cell cell = ws_tv.getCells().getCell(row, col);
                columns.append("\""+cell.getStringValue()+"\""+",");
                lstTV.add(cell.getStringValue());
                values.append("?"+",");
                createTableQuery.append("\""+cell.getStringValue()+"\""+" varchar(2000),");

            }
            createTableQuery.append("RowNo"+" varchar(2000),");

            int index= createTableQuery.lastIndexOf(",");
            createTableQuery=createTableQuery.deleteCharAt(index);

            int index1= columns.lastIndexOf(",");
            columns=columns.deleteCharAt(index1);

            int index2= values.lastIndexOf(",");
            values=values.deleteCharAt(index2);

            values.append(");");


            Boolean IsTableCreated=obj.CreateTable(createTableQuery,SheetName,Conn,lstTV);




            StringBuilder InsertSql = new StringBuilder();

            InsertSql.append( "insert into "+SheetName+" ("+columns+")");
            InsertBatchQuery  = InsertSql.toString()+values.toString();
            PreparedStatement ps = Conn.prepareStatement(InsertBatchQuery);



            System.out.println(" InsertSql :" + InsertSql);

            nRow=  nRow+1;
            for (int i = nRow+1; i <= maxDataRow; i++) {
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

        }

       // return ok(Json.toJson(lstTV));

        return lstTV.toString();

    }



    public Boolean CreateTable (StringBuilder createTableQuery,String SheetName,Connection Conn,ArrayList columns) throws SQLException {

            ReadExcelFiles obj=new ReadExcelFiles();

                Statement st = null;
                Statement statement =null;
                Statement statementTblCol=null;
                Statement statementAlterTbl=null;


                ResultSet rs = null;
                ArrayList lstCol = new ArrayList<>();
                ArrayList lstNewCol = new ArrayList<>();
                String InsertCol ="";
                StringBuilder strAlterTbl=new StringBuilder();

                statement= Conn.createStatement();

                String sqlTableSchema = "\n" +

                        "   SELECT table_name\n" +
                        "   FROM   information_schema.tables \n" +
                        "   WHERE  table_schema = 'public'\n" +
                        "   AND    table_name = '"+SheetName+"'\n" +
                        "   ";


                ResultSet resultSet = statement.executeQuery(sqlTableSchema);

               Boolean isTableSchema=resultSet.next();
               System.out.println("isTableSchema  :" + isTableSchema);



            if(isTableSchema)
            {

                statementTblCol= Conn.createStatement();
                String sqlColSchema="  select column_name  from information_schema.columns where\n" +
                       "  table_name='"+SheetName.toLowerCase()+"';";

                System.out.println("sqlColSchema  :" +sqlColSchema);
                ResultSet rsColSchema= statementTblCol.executeQuery(sqlColSchema);


                while (rsColSchema.next()) {
                    String Cols = rsColSchema.getString("column_name");

                    lstCol.add(Cols);
                    System.out.println("   Cols  :" + Cols);
                }


                for (int i=0;i<columns.size();i++)
                {

                      boolean isExist=  lstCol.contains(columns.get(i).toString().trim());

                      if(!isExist)
                      {
                          lstNewCol.add(columns.get(i));

                          strAlterTbl.append("Add Column "+columns.get(i)+",");
                          System.out.println("   Add  Column :" + columns.get(i));
                      }


                }


                if(lstNewCol.size() > 0)
                {
                    int index= strAlterTbl.lastIndexOf(",");
                    strAlterTbl=strAlterTbl.deleteCharAt(index);

                    statementAlterTbl = Conn.createStatement();
                    String sqlAlterTbl=" ALTER TABLE "+SheetName.toLowerCase()+" "+strAlterTbl+";";

                    System.out.println("sqlAlterTbl  :" +sqlAlterTbl);
                    statementAlterTbl.execute(sqlAlterTbl);


                }
                System.out.println(" New Columns  :" + lstNewCol);
            }
            else

            {
                st = Conn.createStatement();

                String qs = "CREATE TABLE  IF NOT EXISTS " + SheetName + " (\n" +
                        createTableQuery +

                        ");\n";


                System.out.println("Query " + qs);
                // String qs1 = "SELECT * FROM test";

                st.execute(qs);

            }

        return  true;
    }


    public  double distance( double lat1, double lon1, double lat2, double lon2) {
        String unit="K";
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist)/1000;
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}