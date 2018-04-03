package Util_Rpt;

import com.aspose.cells.*;
import entities.TV;
import entities.TV;
import entities.WaterFallLteData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dost Muhammad on 3/13/2018.
 */
public class ReadExcel {




    static int count=0;
    static int t=0;
    static int  countColumn() {



        if (t == 0) {
            t++;
            return count;
        }
        count++;
        return count;
    }


    public static Map getColumnHeadIndex(Worksheet worksheet,int rowNum){


        int maxrow;
        //maxrow = worksheet.getCells().get(23,0).;
        int trows = worksheet.getCells().getMaxDataRow();
        //int trows = worksheet.getCells().getMaxDataRow();
        int tcols = worksheet.getCells().getMaxDataColumn()+1;
        int rowNum2=rowNum+1;
        Map <String,Integer> map=new HashMap<String,Integer>();

        //RowCollection rows = cells.getRows();

        RowCollection rows = worksheet.getCells().getRows();

        for (int i = rowNum; i <rowNum2; i++) {
            for (int j = 0; j < tcols ; j++) {
                Cell cell = worksheet.getCells().get(i, j);
                if (cell == null) {
                } else {
                    String columnName = cell.getValue().toString();
                    Integer columnIndex = CellsHelper.columnNameToIndex("columnName");
                    map.put(columnName, columnIndex);

                   }
            }
        }
       // System.out.print("cell is "+map.size()+"tt"+map.get("Temp"));

        return map;
    }


    public static List<TV> getLst_Tv(Worksheet worksheet, int rowNum){

        List<TV> lstwfall=new ArrayList<>();
        lstwfall.clear();
        TV tvdata = null;

        int trows = worksheet.getCells().getMaxDataRow()+1;
        int tcols = worksheet.getCells().getMaxDataColumn()+1;
        //int row =23;


        RowCollection rows = worksheet.getCells().getRows();
        Cell lastCell = worksheet.getCells().endCellInColumn((short)0);



        for ( int row =rowNum ; row < trows ; row++) {
            tvdata = new TV();
            //if(rows.get(row) !=null) {
                for (int j = 0; j < tcols; j++) {
                    Cell cell = worksheet.getCells().get(row, j);
                   // if (cell.getValue() != null) {

                        //System.out.print(ReadExcel.countColumn());
                        //System.out.print("" + row + "col" + j);

                        tvdata.setPacenumber(worksheet.getCells().get(row, 0).getValue().toString());
                        tvdata.setSubmitters_email(worksheet.getCells().get(row,1).getValue().toString());
                        tvdata.setStructure_type(worksheet.getCells().get(row, 2).getValue().toString());
                        tvdata.setFa_location(worksheet.getCells().get(row, 3).getValue().toString());
                        tvdata.setRbsid(Double.parseDouble(worksheet.getCells().get(row, 4).getValue().toString()));
                        tvdata.setUsid(Double.parseDouble(worksheet.getCells().get(row, 5).getValue().toString()));
                        tvdata.setSite_state(worksheet.getCells().get(row, 6).getValue().toString());
                        tvdata.setUseid(worksheet.getCells().get(row, 7).getValue().toString());
                        tvdata.setVendor_provided_latitude_in_decimals(worksheet.getCells().get(row, 8).getValue().toString());
                        tvdata.setVendor_provided_longitude_in_decimals(worksheet.getCells().get(row, 9).getValue().toString());
                        int ft=(int)Math.round(Double.parseDouble(worksheet.getCells().get(row, 10).getValue().toString()));
                        tvdata.setVendor_provided_gps_cable_length_feets(ft);
                        tvdata.setVendor_provided_gps_cable_type(worksheet.getCells().get(row, 11).getValue().toString());
                        int a=(int)Math.rint(Double.parseDouble(worksheet.getCells().get(row, 12).getValue().toString()));
                        tvdata.setVendor_provided_rbs_cable_length_feets(a);
                        tvdata.setVendor_provided_rbs_cable_type(worksheet.getCells().get(row, 13).getValue().toString());


                    //}

                //}
            }
            lstwfall.add(tvdata);
        }
        return lstwfall;


    }

    public static List<WaterFallLteData> getwaterfall(Worksheet worksheet, int rowNum){

            List<WaterFallLteData> lstwfall=new ArrayList<>();
            WaterFallLteData wfdata=new WaterFallLteData();

        int trows = worksheet.getCells().getMaxDataRow()+1;
        int tcols = worksheet.getCells().getMaxDataColumn()+1;

        RowCollection rows = worksheet.getCells().getRows();
        Cell lastCell = worksheet.getCells().endCellInColumn((short)0);



        for (int row = rowNum ; row < trows ; row++) {
            for (int j = 0; j < tcols; j++) {
                Cell cell = worksheet.getCells().get(row, j);
                if (cell.getValue() != null) {



                // Column column=worksheet.getCells().getColumns().getColumnByIndex(map.get("PACE_NUMBER"));
                //Cell PACE_NUMBER = worksheet.getCells().get(row, 0);
                //Cell cell2 = worksheet.getCells().get(row, 1);


                wfdata.setPacenumber(worksheet.getCells().get(row, 0).getValue().toString());
                wfdata.setSite_id(worksheet.getCells().get(row, 1).getValue().toString());
                wfdata.setPrimary_job_number(worksheet.getCells().get(row, 2).getValue().toString());
                wfdata.setPace_name(worksheet.getCells().get(row, 3).getValue().toString());
                wfdata.setJob_description(worksheet.getCells().get(row, 4).getValue().toString());
                wfdata.setSearch_ring_id(worksheet.getCells().get(row, 5).getValue().toString());
                wfdata.setSearch_ring_name(worksheet.getCells().get(row, 6).getValue().toString());
                wfdata.setCounty(worksheet.getCells().get(row, 7).getValue().toString());
                wfdata.setFips_code(Integer.parseInt(worksheet.getCells().get(row, 8).getValue().toString()));
                wfdata.setLatitude(Double.parseDouble((String) worksheet.getCells().get(row, 9).getValue()));
                wfdata.setLongitude(Double.parseDouble(worksheet.getCells().get(row, 10).getValue().toString()));
                wfdata.setProduct_group(String.valueOf(worksheet.getCells().get(row, 11).getValue().toString()));
                wfdata.setProduct_subgroup(String.valueOf(worksheet.getCells().get(row, 12).getValue().toString()));
                wfdata.setCfas_project_number(String.valueOf(worksheet.getCells().get(row, 13).getValue().toString()));
                wfdata.setSource_job_number(String.valueOf(worksheet.getCells().get(row, 14).getValue().toString()));
                wfdata.setIplan_job_status(String.valueOf(worksheet.getCells().get(row, 15).getValue().toString()));
                wfdata.setUsid(Integer.parseInt(worksheet.getCells().get(row, 16).getValue().toString()));
                wfdata.setConstruction_type(String.valueOf(worksheet.getCells().get(row, 17).getValue().toString()));
                wfdata.setStatus(String.valueOf(worksheet.getCells().get(row, 18).getValue().toString()));
                wfdata.setNational_program(String.valueOf(worksheet.getCells().get(row, 19).getValue().toString()));
                wfdata.setJob_type_subtype(String.valueOf(worksheet.getCells().get(row, 20).getValue().toString()));
                wfdata.setCapital_program(String.valueOf(worksheet.getCells().get(row, 21).getValue().toString()));
                wfdata.setTechnology(String.valueOf(worksheet.getCells().get(row, 22).getValue().toString()));
                wfdata.setFrequency(String.valueOf(worksheet.getCells().get(row, 23).getValue().toString()));
                wfdata.setRbs_id(String.valueOf(worksheet.getCells().get(row, 24).getValue().toString()));
                wfdata.setRegionfranchise_indicator(String.valueOf(worksheet.getCells().get(row, 25).getValue().toString()));
                //wfdata.setStrategic_driver(Integer.parseInt(worksheet.getCells().get(row, 26).getValue().toString()));
                //wfdata.sety(String.valueOf(worksheet.getCells().get(row, 27).getValue().toString()));
                wfdata.setCivil_vendor(String.valueOf(worksheet.getCells().get(row, 28).getValue().toString()));
                wfdata.setSite_acquisition_vendor(String.valueOf(worksheet.getCells().get(row, 29).getValue().toString()));
                wfdata.setEquipment_vendor(String.valueOf(worksheet.getCells().get(row, 30).getValue().toString()));
                wfdata.setJob_vendor(String.valueOf(worksheet.getCells().get(row, 31).getValue().toString()));
                wfdata.setIntegration_vendor(String.valueOf(worksheet.getCells().get(row, 32).getValue().toString()));
                wfdata.setOptimization_vendor(String.valueOf(worksheet.getCells().get(row, 33).getValue().toString()));
                wfdata.setCustom_program(String.valueOf(worksheet.getCells().get(row, 34).getValue().toString()));
                wfdata.setTemplate_name(String.valueOf(worksheet.getCells().get(row, 35).getValue().toString()));
                wfdata.setJob_scope(String.valueOf(worksheet.getCells().get(row, 36).getValue().toString()));
                wfdata.setOracle_ptn(String.valueOf(worksheet.getCells().get(row, 37).getValue().toString()));
                wfdata.setLaunch_polygon(String.valueOf(worksheet.getCells().get(row, 38).getValue().toString()));
                wfdata.setPhase(String.valueOf(worksheet.getCells().get(row, 39).getValue().toString()));
                wfdata.setProject_manager(String.valueOf(worksheet.getCells().get(row, 40).getValue().toString()));
                wfdata.setSaq_area_manager(String.valueOf(worksheet.getCells().get(row, 41).getValue().toString()));
                wfdata.setWork_group(String.valueOf(worksheet.getCells().get(row, 42).getValue().toString()));
                wfdata.setPm2_person(String.valueOf(worksheet.getCells().get(row, 43).getValue().toString()));
                wfdata.setPm3_person(String.valueOf(worksheet.getCells().get(row, 44).getValue().toString()));
                wfdata.setCx_area_manager(String.valueOf(worksheet.getCells().get(row, 45).getValue().toString()));
                wfdata.setFa_location_code(Integer.parseInt(worksheet.getCells().get(row, 46).getValue().toString()));
                wfdata.setFa_location_type(String.valueOf(worksheet.getCells().get(row, 47).getValue().toString()));
               // wfdata.setC(String.valueOf(worksheet.getCells().get(row, 48).getValue().toString()));
                //wfdata.setO(String.valueOf(worksheet.getCells().get(row, 49).getValue().toString()));
                //wfdata.setcu(String.valueOf(worksheet.getCells().get(row, 50).getValue().toString()));
                //wfdata.setCom(String.valueOf(worksheet.getCells().get(row, 51).getValue().toString()));
                wfdata.setAddress(String.valueOf(worksheet.getCells().get(row, 52).getValue().toString()));
                wfdata.setPolygon_jid(Integer.parseInt(worksheet.getCells().get(row, 53).getValue().toString()));
                if(worksheet.getCells().get(row, 54).getValue().toString().equals("")){

                }else {
                    wfdata.setTransport_type(Integer.parseInt(worksheet.getCells().get(row, 54).getValue().toString()));
                }
                wfdata.setOnair_task(String.valueOf(worksheet.getCells().get(row, 55).getValue().toString()));
                //wfdata.setO(String.valueOf(worksheet.getCells().get(row, 56).getValue().toString()));
                //wfdata.setO(String.valueOf(worksheet.getCells().get(row, 57).getValue().toString()));
                //wfdata.seton(String.valueOf(worksheet.getCells().get(row, 58).getValue().toString()));
                wfdata.setPoe_name(String.valueOf(worksheet.getCells().get(row, 59).getValue().toString()));
                wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 60).getValue().toString()));
                wfdata.setPoe_status(String.valueOf(worksheet.getCells().get(row, 61).getValue().toString()));
                wfdata.setPor_status(String.valueOf(worksheet.getCells().get(row, 62).getValue().toString()));
                //wfdata.setPor_on(String.valueOf(worksheet.getCells().get(row, 63).getValue().toString()));
                wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 64).getValue().toString()));
                wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 65).getValue().toString()));
                wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 66).getValue().toString()));
                wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 67).getValue().toString()));
                //wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 68).getValue().toString()));
                //wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 69).getValue().toString()));
               // wfdata.setPor_name(String.valueOf(worksheet.getCells().get(row, 70).getValue().toString()));






                lstwfall.add(wfdata);
            }
            }
        }
        return lstwfall;


    }


}
