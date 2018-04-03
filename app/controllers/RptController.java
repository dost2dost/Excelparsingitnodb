package controllers;

import Util_Rpt.ReadExcel;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.avaje.ebean.Ebean;
import entities.TV;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import views.html.RecordSAved.*;
import views.html.*;
/**
 * Created by Dost Muhammad on 3/13/2018.
 */
public class RptController extends Controller {

    public Result saveTurfVendor() {
        List<TV> lstTV=new ArrayList<>();
        lstTV.clear();
        Workbook wb_tv = null;

        try {
            wb_tv = new Workbook("D:\\Conure Automation Process\\ENMT 3 _Turf_Vendor_Tim Kurtz_1_16_18.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Worksheet ws_tv;

        ws_tv = wb_tv.getWorksheets().get(0);
        lstTV= ReadExcel.getLst_Tv(ws_tv,22);
        Ebean.saveAll(lstTV);
        return ok(views.html.RecordSAved.render());
    }

    public Result index1() {


        return ok(views.html.index.render());
}

}
