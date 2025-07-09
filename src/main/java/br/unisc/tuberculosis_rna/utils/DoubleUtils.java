package br.unisc.tuberculosis_rna.utils;

import java.util.stream.DoubleStream;

public class DoubleUtils {

    /**
     * Concatenate multiple double arrays into a single array.
     *
     * @param arrays Double arrays to be concatenated.
     * @return A single double array containing all elements from the input arrays.
     */
    public static double[] concat(double[]... arrays) {
        double[] result = new double[0];

        for (double[] array : arrays)
            result = DoubleStream.concat(DoubleStream.of(result), DoubleStream.of(array)).toArray();

        return result;
    }

}
