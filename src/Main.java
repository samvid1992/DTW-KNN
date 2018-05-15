import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by samvi on 10/10/17.
 */


public class Main {


    int K = 1;
    ArrayList<String> Training_filenames = new ArrayList<String>();
    ArrayList<String> Training_filename = new ArrayList<String>();

    ArrayList<String> Test_filenames = new ArrayList<String>();
    ArrayList<String> Test_filename = new ArrayList<String>();


    String path = "Dataset\\";
    //String path_Test = "Dataset\\Training\\";

    public static void main(String args[]) {

        Main main = new Main();
        FileParser fileParser = new FileParser();
        // Data data = new Data();
        // ArrayList<Data> list = new ArrayList<Data>();
        //list.add()

        double minimun_dtw = 999999999;
        int correct = 0;
        int wrong = 0;
        double accuracy = 0.0;
        double precision = 0.0;
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        // String userfilename = "sitting_dataset1.csv";
        ArrayList<Float> test_getcolumnn1 = new ArrayList<>();
        ArrayList<Float> training_getcolumnn1 = new ArrayList<>();

        ArrayList<Float> test_getcolumnn2;
        ArrayList<Float> training_getcolumnn2;

        ArrayList<Float> test_getcolumnn3;
        ArrayList<Float> training_getcolumnn3;

        //ArrayList<String> classnames = new ArrayList<>();
        // ArrayList<Double> warping_distane = new ArrayList<>();

        // test_getcolumnn1 = main.findFile(userfilename, new File(main.path));


        // Step 2:- Calulate the Distance between query-instance and all the training samples.


        try {
            final File Training_folder = new File(main.path);
            main.listFilesForFolder_Training(Training_folder);
            // main.filenames.remove(userfilename);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            final File Test_folder = new File(main.path);
            main.listFilesForFolder_Test(Test_folder);
            // main.filenames.remove(userfilename);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //  System.out.println(main.filenames);
/*
        float[] Test_TS1 = new float[test_getcolumnn1.size()];
        int k = 0;
        for (Float f2 : test_getcolumnn1) {
            Test_TS1[k++] = (f2 != null ? f2 : Float.NaN); // Or whatever default you want.
        }
*/
        String indent = "------------------------------------------------------";

        //float accuracy = 0;
        //System.out.println("main.filenames.size() " + main.filenames.size());


        float[] Training_TS1, Training_TS2, Training_TS3, Test_TS1, Test_TS2, Test_TS3;
        String test_name = null, training_name = null;
        ArrayList<String> Wrong_list = new ArrayList<String>();
        ArrayList<String> Correct_list = new ArrayList<String>();


        long startTime = System.currentTimeMillis();


        ArrayList<String> Bending1_Training_class = new ArrayList<>();
        ArrayList<String> Bending1_Test_class = new ArrayList<>();
        ArrayList<Double> Bending1_min_dtw = new ArrayList<>();

        ArrayList<String> Bending2_Training_class = new ArrayList<>();
        ArrayList<String> Bending2_Test_class = new ArrayList<>();
        ArrayList<Double> Bending2_min_dtw = new ArrayList<>();

        ArrayList<String> Cycling_Training_class = new ArrayList<>();
        ArrayList<String> Cycling_Test_class = new ArrayList<>();
        ArrayList<Double> Cycling_min_dtw = new ArrayList<>();

        ArrayList<String> Sitting_Training_class = new ArrayList<>();
        ArrayList<String> Sitting_Test_class = new ArrayList<>();
        ArrayList<Double> Sitting_min_dtw = new ArrayList<>();

        ArrayList<String> Lying_Training_class = new ArrayList<>();
        ArrayList<String> Lying_Test_class = new ArrayList<>();
        ArrayList<Double> Lying_min_dtw = new ArrayList<>();

        ArrayList<String> Standing_Training_class = new ArrayList<>();
        ArrayList<String> Standing_Test_class = new ArrayList<>();
        ArrayList<Double> Standing_min_dtw = new ArrayList<>();

        ArrayList<String> Walking_Training_class = new ArrayList<>();
        ArrayList<String> Walking_Test_class = new ArrayList<>();
        ArrayList<Double> Walking_min_dtw = new ArrayList<>();

        String output = null;

        float[] Training_z_score_TS1, Training_z_score_TS2, Training_z_score_TS3;
        float[] Test_z_score_TS1, Test_z_score_TS2, Test_z_score_TS3;

        for (int i = 0; i < main.Test_filenames.size(); i++) {

            for (int j = 0; j < main.Training_filenames.size(); j++) {

                if (!main.Training_filename.get(j).equals(main.Test_filename.get(i))) {


                    try {
                        test_getcolumnn1 = fileParser.FileParsing(main.Test_filenames.get(i));
                        test_name = fileParser.gettextLine1();
                        test_getcolumnn2 = fileParser.getdatasetColumn4();
                        test_getcolumnn3 = fileParser.getdatasetColumn6();

                        //  readfile = main.Filereader(main.filenames.get(i));
                        training_getcolumnn1 = fileParser.FileParsing(main.Training_filenames.get(j));
                        training_name = fileParser.gettextLine1();
                        training_getcolumnn2 = fileParser.getdatasetColumn4();
                        training_getcolumnn3 = fileParser.getdatasetColumn6();
                        //Column2.add(fileParser.getcolumn3().get(j));
                        // Collections.copy(Column2,fileParser.getcolumn3());

                        //Training_Column6.addAll(fileParser.datasetColumn6());
                        // System.out.println("test_name " + test_name);
                        //System.out.println("training_name " + training_name);


                        //Training_TextLine1 = fileParser.gettextLine1();

                        //       System.out.println(training_name);

                        //Test_Column4.addAll(fileParser.datasetColumn4());
                        //Test_Column4.addAll(fileParser.datasetColumn6());
                        //      System.out.println(test_name);


                        //System.out.println("Training column " + training_getcolumnn1);
                        //System.out.println("Test Columns " + test_getcolumnn1);
                        // System.out.println("------------------------" + main.filenames.get(j));

                        Training_TS1 = new float[training_getcolumnn1.size()];
                        int l = 0;

                        for (Float f : training_getcolumnn1) {
                            Training_TS1[l++] = (f != null ? f : Float.NaN); // Or whatever default you want.
                        }

                        Training_TS2 = new float[training_getcolumnn2.size()];
                        int e = 0;

                        for (Float f : training_getcolumnn2) {
                            Training_TS2[e++] = (f != null ? f : Float.NaN); // Or whatever default you want.
                        }
                        Training_TS3 = new float[training_getcolumnn3.size()];
                        int r = 0;

                        for (Float f : training_getcolumnn3) {
                            Training_TS3[r++] = (f != null ? f : Float.NaN); // Or whatever default you want.
                        }

                        Test_TS1 = new float[test_getcolumnn1.size()];
                        int k = 0;
                        for (Float f2 : test_getcolumnn1) {
                            Test_TS1[k++] = (f2 != null ? f2 : Float.NaN); // Or whatever default you want.
                        }

                        //test_length = (int)(Math.ceil(Test_TS1.length * 1/3));
                        Test_TS1 = Arrays.copyOf(Test_TS1, (Integer)Test_TS1.length * 2/3);

                        Test_TS2 = new float[test_getcolumnn2.size()];
                        int t = 0;
                        for (Float f2 : test_getcolumnn2) {
                            Test_TS2[t++] = (f2 != null ? f2 : Float.NaN); // Or whatever default you want.
                        }

                        //test_length = (int)(Math.ceil(Test_TS1.length * 1/3));
                        Test_TS2 = Arrays.copyOf(Test_TS2, (Integer)Test_TS2.length * 2/3);

                        Test_TS3 = new float[test_getcolumnn3.size()];
                        int y = 0;
                        for (Float f2 : test_getcolumnn3) {
                            Test_TS3[y++] = (f2 != null ? f2 : Float.NaN); // Or whatever default you want.
                        }

                        //test_length = (int)(Math.ceil(Test_TS1.length * 1/3));
                        Test_TS3 = Arrays.copyOf(Test_TS3, (Integer)Test_TS3.length * 2/3);

                        //main.filewrite(Training_TS1.toString());
                        main.filewrite(indent);
                        main.filewrite(main.Test_filenames.get(i) + " ----> " + main.Training_filenames.get(j));


                        Training_z_score_TS1 = new float[Training_TS1.length];
                        Training_z_score_TS1 = main.calculateZScore(Training_TS1);

                        Training_z_score_TS2 = new float[Training_TS2.length];
                        Training_z_score_TS2 = main.calculateZScore(Training_TS2);

                        Training_z_score_TS3 = new float[Training_TS3.length];
                        Training_z_score_TS3 = main.calculateZScore(Training_TS3);


                        //  for(int u = 0; u < z_score_TS1.length ; u++) {
                        //     System.out.println("std_TS1 " +  z_score_TS1[u]);
                        // }

                        Test_z_score_TS1 = new float[Test_TS1.length];
                        Test_z_score_TS1 = main.calculateZScore(Test_TS1);

                        Test_z_score_TS2 = new float[Test_TS2.length];
                        Test_z_score_TS2 = main.calculateZScore(Test_TS2);

                        Test_z_score_TS3 = new float[Test_TS3.length];
                        Test_z_score_TS3 = main.calculateZScore(Test_TS3);



                        //DTW2 dtw = new DTW2(Training_z_score_TS1, Training_z_score_TS2, Training_z_score_TS3, Test_z_score_TS1, Test_z_score_TS2, Test_z_score_TS3);
                        DTW dtw = new DTW(Training_z_score_TS3, Test_z_score_TS3);
                        /*
                        for (int f =0; f < Training_TS1.length; f++) {
                            System.out.print(Training_TS1[f] + " ");
                        }
                        System.out.println("\n");
                        for (int f =0; f < Test_TS1.length; f++) {
                            System.out.print(Test_TS1[f]+ " ");
                        }
                        System.out.println("\n\n\n");

*/
                        // DTW2 dtw = new DTW2(Training_TS1, Test_TS1);


                        //z=score

                        double warping = dtw.warpingDistance;
                        warping = Float.valueOf(twoDForm.format(warping));
                        //System.out.println(warping);


                        // classnames.add(class_name);
                        // warping_distane.add(warping);

                        //   list.add(training_name, test_name, minimun_dtw);


                        // System.out.println("------11111-----------------------");
                        // System.out.println("11111 minimun_dtw " + minimun_dtw);
                        // System.out.println("11111 warping " + warping);
                        // System.out.println(main.Test_filenames.get(j));

                        //  list.add(new Data(main.Training_filename.get(j).toString(), main.Test_filename.get(i).toString(), minimun_dtw));
/*
                        if (minimun_dtw >= warping) {
                            minimun_dtw = warping;

                            // System.out.println("-----------22222------------------");
                            // System.out.println("2222 minimun_dtw " + minimun_dtw);
                            //System.out.println("22222 warping " + warping);
                            System.out.println(minimun_dtw);
                            System.out.println(main.Test_filename.get(j) + "---> " + (main.Training_filename.get(i)));
                            // if ((main.Test_filename.get(j)).equals(main.Training_filename.get(i))) {
                            if (training_name.equals(test_name)) {

                                System.out.println(main.Test_filename.get(j) + " ---Correct match--- " + main.Training_filename.get(i));
                                output = main.Test_filename.get(j) + "------>" + main.Training_filename.get(i);
                                Correct_list.add(main.Test_filenames.get(j) + " ----> " + main.Training_filenames.get(i));
                                System.out.println(training_name + " ----- " + test_name);
                                correct++;

                            } else {
                                Wrong_list.add(main.Test_filenames.get(j) + " ----> " + main.Training_filenames.get(i));
                                //System.out.println(main.Test_filenames.get(j) + " ---Wrong Match--- " + main.Training_filenames.get(i));
                                wrong++;
                            }


                        }
*/
                        // System.out.println(test_name.trim() + test_name);

                        if (test_name.trim().equals("bending1")) {
                            Bending1_min_dtw.add(warping);
                            //  System.out.println("Bending1_min_dtw ");
                            Bending1_Test_class.add(test_name);
                            Bending1_Training_class.add(training_name);
                        } else if (test_name.trim().equals("bending2")) {
                            Bending2_min_dtw.add(warping);
                            // System.out.println("Bending2_min_dtw ");

                            Bending2_Test_class.add(test_name);
                            Bending2_Training_class.add(training_name);
                        } else if (test_name.trim().equals("cycling")) {
                            Cycling_Training_class.add(training_name);
                            Cycling_min_dtw.add(warping);
                            Cycling_Test_class.add(test_name);
                        } else if (test_name.trim().equals("lying")) {
                            Lying_min_dtw.add(warping);
                            Lying_Test_class.add(test_name);
                            Lying_Training_class.add(training_name);
                        } else if (test_name.trim().equals("sitting")) {
                            Sitting_min_dtw.add(warping);
                            Sitting_Test_class.add(test_name);
                            Sitting_Training_class.add(training_name);
                        } else if (test_name.trim().equals("standing")) {
                            Standing_min_dtw.add(warping);
                            Standing_Test_class.add(test_name);
                            Standing_Training_class.add(training_name);
                        } else if (test_name.trim().equals("walking")) {
                            Walking_min_dtw.add(warping);
                            Walking_Test_class.add(test_name);
                            Walking_Training_class.add(training_name);

                        } else {
                            System.out.println("Error");
                        }

                        main.filewrite(dtw.toString());
                        main.filewrite(output);
                        main.filewrite(String.valueOf("Correct = " + correct));
                        main.filewrite(String.valueOf("Wrong = " + wrong));


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                // correct = 0;
                // wrong = 0;
                // minimun_dtw = 999999999;


            }

            //  int minIndex = warping_distane.indexOf(Collections.min(warping_distane));
            // System.out.println("Minimum " + Collections.min(warping_distane));

            //  System.out.println("minIndex " + minIndex);

            // System.out.println("Classname  " + classnames.get(minIndex) + " warping distance " + warping_distane.get(minIndex));

            // System.out.println("minIndex " + minIndex);


            // warping_distane.clear();
            //classnames.clear();
            training_getcolumnn1.clear();
            test_getcolumnn1.clear();

        }

        int Bending1_minIndex = Bending1_min_dtw.indexOf(Collections.min(Bending1_min_dtw));
        //System.out.println(Bending1_minIndex);
        int Bending2_minIndex = Bending2_min_dtw.indexOf(Collections.min(Bending2_min_dtw));
        //System.out.println(Bending2_minIndex);
        int Walking_minIndex = Walking_min_dtw.indexOf(Collections.min(Walking_min_dtw));
        //System.out.println(Walking_minIndex);
        int Lying_minIndex = Lying_min_dtw.indexOf(Collections.min(Lying_min_dtw));
        //System.out.println(Lying_minIndex);
        int Standing_minIndex = Standing_min_dtw.indexOf(Collections.min(Standing_min_dtw));
        //System.out.println(Standing_minIndex);
        int Sitting_minIndex = Sitting_min_dtw.indexOf(Collections.min(Sitting_min_dtw));
        //System.out.println(Sitting_minIndex);
        int Cycling_minIndex = Cycling_min_dtw.indexOf(Collections.min(Cycling_min_dtw));
        //System.out.println(Cycling_minIndex);

        //System.out.println("Bending1_Test_class.get(Bending1_minIndex)" + Bending1_Test_class.get(Bending1_minIndex));
        //System.out.println("Bending1_Training_class.get(Bending1_minIndex)" + Bending1_Training_class.get(Bending1_minIndex));
        if (Bending1_Test_class.get(Bending1_minIndex).trim().equals(Bending1_Training_class.get(Bending1_minIndex).trim())) {
            output = (Bending1_Test_class.get(Bending1_minIndex)) + " ---Correct match--- " + Bending1_Training_class.get(Bending1_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            // System.out.println(training_name + " ----- " + test_name);
            correct++;

        } else {
            output = (Bending1_Test_class.get(Bending1_minIndex)) + " ---Wrong match--- " + Bending1_Training_class.get(Bending1_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }
        if ((Bending2_Test_class.get(Bending2_minIndex).trim()).equals(Bending2_Training_class.get(Bending2_minIndex).trim())) {
            output = (Bending2_Test_class.get(Bending2_minIndex)) + " ---Correct match--- " + Bending2_Training_class.get(Bending2_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            correct++;

        } else {
            output = (Bending2_Test_class.get(Bending2_minIndex)) + " ---Wrong match--- " + Bending2_Training_class.get(Bending2_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }
        if ((Walking_Test_class.get(Walking_minIndex).trim()).equals(Walking_Training_class.get(Walking_minIndex).trim())) {
            output = (Walking_Test_class.get(Walking_minIndex)) + " ---Correct match--- " + Walking_Training_class.get(Walking_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            correct++;

        } else {
            output = (Walking_Test_class.get(Walking_minIndex)) + " ---Wrong match--- " + Walking_Training_class.get(Walking_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }
       // output = (Lying_Test_class.get(Lying_minIndex).trim() + " ---Test match--- " + Lying_Training_class.get(Lying_minIndex).trim());
        //System.out.println(output);
        if ((Lying_Test_class.get(Lying_minIndex).trim()).equals(Lying_Training_class.get(Lying_minIndex).trim())) {
            output = (Lying_Test_class.get(Lying_minIndex)) + " ---Correct match--- " + Lying_Training_class.get(Lying_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            correct++;

        } else {
            output = (Lying_Test_class.get(Lying_minIndex)) + " ---Wrong match--- " + Lying_Training_class.get(Lying_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }
        if ((Standing_Test_class.get(Standing_minIndex).trim()).equals(Standing_Training_class.get(Standing_minIndex).trim())) {
            output = (Standing_Test_class.get(Standing_minIndex)) + " ---Correct match--- " + Standing_Training_class.get(Standing_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            correct++;

        } else {
            output = (Standing_Test_class.get(Standing_minIndex)) + " ---Wrong match--- " + Standing_Training_class.get(Standing_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }
        if ((Sitting_Test_class.get(Sitting_minIndex).trim()).equals(Sitting_Training_class.get(Sitting_minIndex).trim())) {
            output = (Sitting_Test_class.get(Sitting_minIndex)) + " ---Correct match--- " + Sitting_Training_class.get(Sitting_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            correct++;

        } else {
            output = (Sitting_Test_class.get(Sitting_minIndex)) + " ---Wrong match--- " + Sitting_Training_class.get(Sitting_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }
        if ((Cycling_Test_class.get(Cycling_minIndex).trim()).equals(Cycling_Training_class.get(Cycling_minIndex).trim())) {
            output = (Cycling_Test_class.get(Cycling_minIndex)) + " ---Correct match--- " + Cycling_Training_class.get(Cycling_minIndex);
            System.out.println(output);
            Correct_list.add(output);
            correct++;

        } else {
            output = (Cycling_Test_class.get(Cycling_minIndex)) + " ---Wrong match--- " + Cycling_Training_class.get(Cycling_minIndex);
            System.out.println(output);
            Wrong_list.add(output);
            wrong++;
        }


        System.out.println(correct);
        System.out.println(wrong);
        double total = (double) correct + (double) wrong;
        precision = (double) correct / total;
        accuracy = precision * 100.0;

        System.out.println(precision);
        System.out.println(accuracy);


        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.print("Execution time is " + formatter.format((totalTime) / 1000d) + " seconds");


        //   String totalTime_string = Long.toString(totalTime);
        try {
            main.filewrite(String.valueOf("Correct = " + correct));
            main.filewrite(String.valueOf("Wrong = " + wrong));
            main.filewrite(String.valueOf("Precision = " + precision));
            main.filewrite(String.valueOf("Accuracy = " + accuracy));
            main.filewrite("Execution time is " + formatter.format((totalTime) / 1000d) + " seconds");
            main.filewrite("--------------------Correct List-----------------------");
            for (int i = 0; i < Correct_list.size(); i++) {
                main.filewrite(Correct_list.get(i) + "\n");
            }
            main.filewrite("---------------------Wrong List------------------------");
            for (int j = 0; j < Wrong_list.size(); j++) {
                main.filewrite(Wrong_list.get(j) + "\n");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public float[] calculateZScore(float numArray[]) {
        int length = numArray.length;
        float[] zscore = new float[length];
        double std;
        double sum = 0.0, standardDeviation = 0.0;

        // mean
        for (double num : numArray) {
            sum += num;
        }
        //standard deviation
        double mean = sum / length;

        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        std = Math.sqrt(standardDeviation / length);

        DecimalFormat twoDForm = new DecimalFormat("#.####");
        //z=score
        for (int i = 0; i < length; i++) {
            zscore[i] = (float) ((numArray[i] - sum) / std);
            // System.out.println(zscore[i]);
            zscore[i] = Float.valueOf(twoDForm.format(zscore[i]));
        }

        return zscore;


    }

    public void listFilesForFolder_Training(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder_Training(fileEntry);
            } else {
                // System.out.println(fileEntry.getName());
                Training_filenames.add(fileEntry.getAbsolutePath());
                Training_filename.add(fileEntry.getName());
                //  String content = FileUtils.readFileToString(file);

            }
        }
    }


    public void listFilesForFolder_Test(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder_Test(fileEntry);
            } else {
                //  System.out.println(fileEntry.getName());
                // System.out.println(fileEntry.getAbsolutePath());
                Test_filenames.add(fileEntry.getAbsolutePath());
                Test_filename.add(fileEntry.getName());
                //  String content = FileUtils.readFileToString(file);

            }
        }
    }

    /*
    public ArrayList<Float> findFile(String name, File file) {
        ArrayList<Float> column = null;
        FileParser fileParser2 = new FileParser();
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                // String path = null;
                if (fil.isDirectory()) {
                    column = findFile(name, fil);
                    if (column != null) {
                        return column;
                    }
                } else if (fil.getName().contains(name)) {
                    //  column =fil.getAbsolutePath();
                    column = fileParser2.FileParsing(fil.getAbsolutePath());
                    if (column != null) {

                        return column;
                    }
                }
            }
        }
        return null; // nothing found
    }
 */

    public void filewrite(String output) {
        // long value;
        //value = System.currentTimeMillis();

        String File_name = "Output.txt";
        try (FileWriter fw = new FileWriter(File_name, true);
             // String totalTime_string = Long.toString(value);
             // File fw = File.createTempFile(totalTime_string, ".txt", new File("C:\\Users\\samvi\\Desktop\\DTW-KNN"));
             // try(FileWriter fw = new FileWriter("output.txt");
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(output);
            //more code
        } catch (Exception e) {
            //exception handling left as an exercise for the reader
            e.printStackTrace();
        }

    }

    /*
    public class Data {
        String Training_name[];
        String Test_name[];
        double minimum_dtw;

        public Data(String Training_name[], String Test_name[], double minimum_dtw) {
            this.Training_name = Training_name;
            this.Test_name = Test_name;
            this.minimum_dtw = minimum_dtw;
        }
    }
*/

}