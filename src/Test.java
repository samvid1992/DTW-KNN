import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class Test {
    int K = 1;
    ArrayList<String> Training_filenames = new ArrayList<String>();
    ArrayList<String> Test_filenames = new ArrayList<String>();

    ArrayList<String> Training_filename = new ArrayList<String>();
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
        // String userfilename = "sitting_dataset1.csv";
        ArrayList<Float> test_getcolumnn1;
        ArrayList<Float> training_getcolumnn1;

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
        float[] TS2 = new float[test_getcolumnn1.size()];
        int k = 0;
        for (Float f2 : test_getcolumnn1) {
            TS2[k++] = (f2 != null ? f2 : Float.NaN); // Or whatever default you want.
        }
*/
        String indent = "------------------------------------------------------";

        //float accuracy = 0;
        //System.out.println("main.filenames.size() " + main.filenames.size());


        float[] TS1, TS2;
        String test_name, training_name;
        ArrayList<String> Wrong_list = new ArrayList<String>();
        ArrayList<String> Correct_list = new ArrayList<String>();


        long startTime = System.currentTimeMillis();

        ArrayList<Float> Training_Column4 = new ArrayList<>();
        ArrayList<Float> Training_Column6 = new ArrayList<>();

        ArrayList<Float> Test_Column4 = new ArrayList<>();
        ArrayList<Float> Test_Column6 = new ArrayList<>();

        float[] z_score_TS1, z_score_TS2;

        for (int i = 0; i < main.Test_filenames.size(); i++) {
            for (int j = 0; j < main.Training_filenames.size(); j++) {

                if (!main.Training_filename.get(j).equals(main.Test_filename.get(i))) {


                    try {


                        //  readfile = main.Filereader(main.filenames.get(i));
                        training_getcolumnn1 = fileParser.FileParsing(main.Training_filenames.get(j));
                        training_name = fileParser.gettextLine1();
                        //Column2.add(fileParser.getcolumn3().get(j));
                        // Collections.copy(Column2,fileParser.getcolumn3());
                        //Training_Column4.addAll(fileParser.datasetColumn4());
                        //Training_Column6.addAll(fileParser.datasetColumn6());
                        //System.out.println(Column4);


                        //Training_TextLine1 = fileParser.gettextLine1();

                        //       System.out.println(training_name);
                        test_getcolumnn1 = fileParser.FileParsing(main.Test_filenames.get(i));
                        test_name = fileParser.gettextLine1();
                        TS1 = new float[training_getcolumnn1.size()];
                        int l = 0;
                        System.out.println(training_getcolumnn1);

                        for (Float f : training_getcolumnn1) {
                            TS1[l++] = (f != null ? f : Float.NaN); // Or whatever default you want.
                            System.out.println(TS1[l]);
                        }

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}




/*
            try {
                Path startPath = Paths.get("Dataset\\");
                Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        // do your thing here

                        String firstLine = Files.newBufferedReader(file, Charset.defaultCharset()).readLine();
                        System.out.println(firstLine);
                       // Files.list(file);



                        return FileVisitResult.CONTINUE;

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            */


