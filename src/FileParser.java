import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by samvi on 10/10/17.
 */
public class FileParser {

    String line = "";
    String cvsSplitBy = ",";
    String Task = "";

    String currentLine = null;
    String textLine1 = null;
    String textLine2 = null;
    String textLine3 = null;
    String textLine4 = null;
    String textLine5 = null;

    float column2;
    String[] Task1 = null;
    String[] Columns = null;
    String[] ColumnNames = null;
    String[] dataset = null;


    // public static ArrayList<Float> datasetColumn3 = new ArrayList<Float>();
    // public static ArrayList<Float> datasetColumn4 = new ArrayList<Float>();
    // public static ArrayList<Float> datasetColumn5 = new ArrayList<Float>();
    //public static ArrayList<Float> datasetColumn6 = new ArrayList<Float>();
    //public static ArrayList<Float> datasetColumn7 = new ArrayList<Float>();

    public static void main(String args[]) {

        //  FileParser fileParser = new FileParser();
        //  String Path = "dataset1.csv";
        //  ArrayList<Float> output= fileParser.FileParsing(Path);
        //     System.out.println(output);

    }
    ArrayList<Float> datasetColumn2;
    ArrayList<Float> datasetColumn3;
    ArrayList<Float> datasetColumn4;
    ArrayList<Float> datasetColumn5;
    ArrayList<Float> datasetColumn6;
    ArrayList<Float> datasetColumn7;


    //ArrayList<Float>
    public ArrayList<Float> FileParsing(String Path) {


        ArrayList<String> datasetColumn1 = new ArrayList<String>();
        datasetColumn2 = new ArrayList<Float>();
        datasetColumn3 = new ArrayList<Float>();
        datasetColumn4 = new ArrayList<Float>();
        datasetColumn5 = new ArrayList<Float>();
        datasetColumn6 = new ArrayList<Float>();
        datasetColumn7 = new ArrayList<Float>();


        ArrayList<ArrayList<Float>> datasetColumn = new ArrayList<ArrayList<Float>>();


        LineNumberReader reader;

        try {
            reader = new LineNumberReader(new FileReader(Path));

            while ((currentLine = reader.readLine()) != null) {

                // # Task: bending1
                if (reader.getLineNumber() == 1) {
                    textLine1 = currentLine;
                    Task1 = currentLine.split(":");
                    settextLine1(Task1[1]);
                    datasetColumn1.add(Task1[1]);
                    //   System.out.println(Task1[1]);
                }


                // # Frequency (Hz): 20


                else if (reader.getLineNumber() == 2) {
                    textLine2 = currentLine;
                    settextLine2(textLine2);
                    //  System.out.println(textLine2);

                }

                // # Clock (millisecond): 250

                else if (reader.getLineNumber() == 3) {
                    textLine3 = currentLine;
                    settextLine3(textLine3);
                    //  System.out.println(textLine3);

                }

                // # Duration (seconds): 120

                else if (reader.getLineNumber() == 4) {
                    textLine4 = currentLine;
                    settextLine4(textLine4);
                    //   System.out.println(textLine4);

                }

                // # Columns: time	avg_rss12	var_rss12	avg_rss13	var_rss13	avg_rss23	var_rss23


                else if (reader.getLineNumber() == 5) {

                    textLine5 = currentLine;
                    Columns = currentLine.split(":");
                    ColumnNames = Columns[1].split(",");
                    datasetColumn1.add(ColumnNames[0]);
                    datasetColumn1.add(ColumnNames[1]);
                    datasetColumn1.add(ColumnNames[2]);
//                    setgetcolumn2(Float.parseFloat(ColumnNames[2]));
                    datasetColumn1.add(ColumnNames[3]);
                    datasetColumn1.add(ColumnNames[4]);
                    datasetColumn1.add(ColumnNames[5]);
                    datasetColumn1.add(ColumnNames[6]);

                    //   System.out.println(ColumnNames[0]+ " " + ColumnNames[1] + " " + ColumnNames[2] +" " + ColumnNames[3] + " " + ColumnNames[4]+ " " + ColumnNames[5] + " " + ColumnNames[6] );
                    //System.out.println(textLine5);

                } else {


                    dataset = currentLine.split(cvsSplitBy);
                    datasetColumn2.add(Float.parseFloat(dataset[2]));
                   // datasetColumn3.add(Float.parseFloat(dataset[2]));
                    datasetColumn4.add(Float.parseFloat(dataset[4]));
                   // datasetColumn5.add(Float.parseFloat(dataset[4]));
                    datasetColumn6.add(Float.parseFloat(dataset[6]));
                   // datasetColumn7.add(Float.parseFloat(dataset[6]));

                 //   ArrayList<ArrayList<Float>> arrayList = getArrayList(datasetColumn2, datasetColumn4, datasetColumn6);


                    //  System.out.println("Column 1 =" + dataset[1] + " , Column 3 =" + dataset[3]);
                }

            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


//    String[] FinalOutput = {Task1[1], ColumnNames[0], ColumnNames[1], ColumnNames[2], ColumnNames[3], ColumnNames[4], ColumnNames[5], ColumnNames[6]};
        //  System.out.println(datasetColumn1);
        return datasetColumn2;
    }



    public float getcolumn2() {
        return this.column2;
    }

    public void setgetcolumn2(float columns2) {
        this.column2 = columns2;
    }


    public String gettextLine1() {
        return this.textLine1;
    }

    public void settextLine1(String line1) {
        this.textLine1 = line1;
    }


    public String gettextLine2() {
        return this.textLine2;
    }

    public void settextLine2(String line2) {
        this.textLine2 = line2;
    }


    public String gettextLine3() {
        return this.textLine3;
    }

    public void settextLine3(String line3) {
        this.textLine3 = line3;
    }


    public String gettextLine4() {
        return this.textLine4;
    }

    public void settextLine4(String line4) {
        this.textLine4 = line4;
    }


    public String gettextLine5() {
        return this.textLine5;
    }

    public void settextLine5(String line5) {
        this.textLine5 = line5;
    }






    public ArrayList<Float> getdatasetColumn4() {
        return datasetColumn4;
    }

    public void setdatasetColumn4(ArrayList<Float> datasetColumn4) {
        this.datasetColumn4 = datasetColumn4;
    }



    public ArrayList<Float> getdatasetColumn6() {
        return datasetColumn6;
    }

    public void setdatasetColumn6(ArrayList<Float> datasetColumn6) {
        this.datasetColumn6 = datasetColumn6;
    }


    public ArrayList<Float> getdatasetColumn7() {
        return datasetColumn7;
    }


    public void setdatasetColumn7(ArrayList<Float> datasetColumn7) {
        this.datasetColumn7 = datasetColumn7;
    }

    /*
    public ArrayList<Float> getdatasetColumn3() {
        return datasetColumn3;
    }

    public void setdatasetColumn3(ArrayList<Float> datasetColumn3) {
        this.datasetColumn3 = datasetColumn3;
    }
    public ArrayList<Float> getDatasetColumn5() {
        return datasetColumn5;
    }

    public void setdatasetColumn5(ArrayList<Float> datasetColumn5) {
        this.datasetColumn5 = datasetColumn5;
    }
    */

}









/*
    try (BufferedReader br = new BufferedReader(new FileReader(Path))) {
        line = br.readLine();
        Task = line;
        System.out.println(Task);


        while ((line = br.readLine()) != null) {
            if(line)

            Task = line;


          //  System.out.println(Task);

         //   ColumnNames =
            // use comma as separator
          //  String[] dataset = line.split(cvsSplitBy);

         //   System.out.println("Column 1 =" + dataset[1] + " , Column 3 =" + dataset[3] + "]");

        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    */

