/**
 * Created by samvi on 11/07/17.
 */
/*
 * DTW.java
 */


import java.text.DecimalFormat;

/**
 * This class implements the Dynamic Time Warping algorithm
 * given two sequences
 * <pre>
 *   X = x1, x2,..., xi,..., xn
 *   Y = y1, y2,..., yj,..., ym
 *  </pre>
 */
public class DTW2 {

    protected float[] Training_seq1,Training_seq2,Training_seq3 ;
    protected float[] Test_seq1,Test_seq2,Test_seq3;
    protected int[][] warpingPath;

    protected int n;
    protected int m;
    protected int K;

    protected double warpingDistance;

    /**
     * Constructor
     *
     * @param query
     * @param templete
     */


    public DTW2(float[] training1, float[] training2,float[] training3, float[] test1, float[] test2,float[] test3) {
        Training_seq1 = training1;
        Training_seq2 = training2;
        Training_seq3 = training3;
        Test_seq1 = test1;
        Test_seq2 = test2;
        Test_seq3 = test3;

        n = Training_seq1.length;
        m = Test_seq1.length;
        K = 1;

        warpingPath = new int[n + m][2];	// max(n, m) <= K < n + m
        warpingDistance = 0.0;

        this.compute();
    }

    public void compute() {
        double accumulatedDistance = 0.0;

        double[][] d = new double[n][m];	// local distances
        double[][] D = new double[n][m];	// global distances

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] = distanceBetween(Training_seq1[i],Training_seq2[i],Training_seq3[i], Test_seq1[j], Test_seq2[j],Test_seq3[j]);
            }
        }

        D[0][0] = d[0][0];

        for (int i = 1; i < n; i++) {
            D[i][0] = d[i][0] + D[i - 1][0];
        }

        for (int j = 1; j < m; j++) {
            D[0][j] = d[0][j] + D[0][j - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                accumulatedDistance = Math.min(Math.min(D[i-1][j], D[i-1][j-1]), D[i][j-1]);
                accumulatedDistance += d[i][j];
                D[i][j] = accumulatedDistance;
            }
        }
        accumulatedDistance = D[n - 1][m - 1];

        int i = n - 1;
        int j = m - 1;
        int minIndex = 1;

        warpingPath[K - 1][0] = i;
        warpingPath[K - 1][1] = j;

        while ((i + j) != 0) {
            if (i == 0) {
                j -= 1;
            } else if (j == 0) {
                i -= 1;
            } else {	// i != 0 && j != 0
                double[] array = { D[i - 1][j], D[i][j - 1], D[i - 1][j - 1] };
                minIndex = this.getIndexOfMinimum(array);

                if (minIndex == 0) {
                    i -= 1;
                } else if (minIndex == 1) {
                    j -= 1;
                } else if (minIndex == 2) {
                    i -= 1;
                    j -= 1;
                }
            } // end else
            K++;
            warpingPath[K - 1][0] = i;
            warpingPath[K - 1][1] = j;
        } // end while
        warpingDistance = accumulatedDistance / K;

        this.reversePath(warpingPath);
    }

    /**
     * Changes the order of the warping path (increasing order)
     *
     * @param path	the warping path in reverse order
     */
    protected void reversePath(int[][] path) {
        int[][] newPath = new int[K][2];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 2; j++) {
                newPath[i][j] = path[K - i - 1][j];
            }
        }
        warpingPath = newPath;
    }
    /**
     * Returns the warping distance
     *
     * @return
     */
    public double getDistance() {
        return warpingDistance;
    }

    /**
     * Computes a distance between two points
     *
     * @param p1	the point 1
     * @param p2	the point 2
     * @return		the distance between two points
     */
    protected double distanceBetween(double train1, double train2, double train3, double test1, double test2, double test3) {
        return Math.pow(train1 - test1, 2) + Math.pow(train2 - test2,2) + Math.pow(train3 - test3,2);
    }

    /**
     * Finds the index of the minimum element from the given array
     *
     * @param array		the array containing numeric values
     * @return				the min value among elements
     */
    protected int getIndexOfMinimum(double[] array) {
        int index = 0;
        double val = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < val) {
                val = array[i];
                index = i;
            }
        }
        return index;
    }

    /**
     *	Returns a string that displays the warping distance and path
     */
    public String toString() {
        DecimalFormat twoDForm = new DecimalFormat("#.####");
        warpingDistance = Double.valueOf(twoDForm.format(warpingDistance));
        String retVal = "Warping Distance: " + warpingDistance + "\n";
        retVal += "Warping Path: {";
        for (int i = 0; i < K; i++) {
            retVal += "(" + warpingPath[i][0] + ", " +warpingPath[i][1] + ")";
            retVal += (i == K - 1) ? "}" : ", ";

        }
        return retVal;
    }

    /*
    public static void main(String[] args) {
       // float[] n2 = {1.5f, 3.9f, 4.1f, 3.3f};
      //  float[] n1 = {2.1f, 2.45f, 3.673f, 4.32f, 2.05f, 1.93f, 5.67f, 6.01f};
      //  System.out.println(dtw);


        FileParser fileParser = new FileParser();
        String Path1 = "dataset1.csv";
        ArrayList<Float> output1= fileParser.FileParsing(Path1);
      //  System.out.println(output1);

        float[] TS1 = new float[output1.size()];
        int i = 0;

        for (Float f : output1) {
            TS1[i++] = (f != null ? f : Float.NaN); // Or whatever default you want.
        }



      //  float[] n1 = ArrayUtils.toPrimitive(output1.toArray(new Float[0]), 0.0F);


        String Path2 = "dataset2.csv";
        ArrayList<Float> output2= fileParser.FileParsing(Path2);
        //  System.out.println(output1);

        float[] TS2 = new float[output2.size()];
        int j = 0;
        for (Float f2 : output2) {
            TS2[j++] = (f2 != null ? f2 : Float.NaN); // Or whatever default you want.
        }


        DTW2 dtw = new DTW2(TS1, TS2);
        System.out.println(dtw);


    }

    */
}

