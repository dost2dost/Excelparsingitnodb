package entities;

/**
 * Created by Abbas Qamar on 3/21/2018.
 */
public class ExcelToDb {

    private int maxDataColumn;
    private int maxDataRow;
    private String insertQuery;
    private String createTableQuery;

    public int getMaxDataColumn() {
        //include validation, logic, logging or whatever you like here
        return this.maxDataColumn;
    }
    public void setMaxDataColumn(int value)
    {
        //include more logic
        this.maxDataColumn = value;
    }


    public int getMaxDataRow() {
        //include validation, logic, logging or whatever you like here
        return this.maxDataRow;
    }
    public void setMaxDataRow(int value)
    {
        //include more logic
        this.maxDataRow = value;
    }


    public String getinsertQuery() {
        //include validation, logic, logging or whatever you like here
        return this.insertQuery;
    }

    public String getcreateTableQuery()
    {
        return this.createTableQuery;
    }
}
