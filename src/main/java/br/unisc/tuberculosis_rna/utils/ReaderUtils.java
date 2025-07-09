package br.unisc.tuberculosis_rna.utils;

public class ReaderUtils {

    public static Integer readerParseInt(String value) {
        return Integer.parseInt(value.trim());
    }

    public static Double readerParseDouble(String value) {
        return Double.parseDouble(value.trim());
    }

}
