import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Написать скрипт для расчета корреляции Пирсона между двумя случайными величинами (двумя массивами).
 * Можете использовать любую парадигму, но рекомендую использовать функциональную,
 * т.к. в этом примере она значительно упростит вам жизнь.
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Решение на базе списков
         */
        List<Double> list1 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        List<Double> list2 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        System.out.printf("Correlation = %.2f\n", correlationCalc(list1, list2));
        /**
         * Решение на базе массивов
         */
        double[] array1 = new double[] {1, 2, 3, 4, 5};
        double[] array2 = new double[] {1, 2, 3, 4, 5};
        System.out.printf("CorrelationArray = %.3f", correlationArray(array1, array2));
    }

    /**
     * Списки
     */
    private static double correlationCalc(List<Double> list1, List<Double> list2) {
        if (list1.size() != list2.size()) throw new RuntimeException("Не корректный ввод массивов");
        return getNumerator(list1, list2) / getDenominator(list1, list2);
    }
    private static double getAverageValue(List<Double> list1) {
        double result = 0;
        for (int i = 0; i < list1.size(); i++) {
            result = result + list1.get(i);        }
        return result / list1.size();
    }
    private static double getNumerator(List<Double> list1, List<Double> list2) {
        double result = 0;
        for (int i = 0; i < list1.size(); i++) {
            result += (list1.get(i) - getAverageValue(list1)) * (list2.get(i) - getAverageValue(list2));
        }
        return result;
    }
    private static double getDenominator(List<Double> list1, List<Double> list2) {
        double result = 0;
        for (int i = 0; i < list1.size(); i++) {
            result += Math.pow((list1.get(i) - getAverageValue(list1)), 2) * Math.pow((list2.get(i) - getAverageValue(list2)), 2);
        }
        return Math.sqrt(result);
    }
    /**
     * Массивы
     */
    private static Double correlationArray(double[] array1, double[] array2){
        return getNumerator(array1, array2) / getDenominator(array1, array2);
    }
    private static Double getNumerator(double[] array1, double[] array2) {
        double average1 = Arrays.stream(array1).average().stream().sum();
        double average2 = Arrays.stream(array2).average().stream().sum();
        double result = 0;
        for (int i = 0; i < array1.length; i++) {
            result += (array1[i] - average1) * (array2[i] - average2);
        }
        return result;
    }
    private static Double getDenominator(double[] array1, double[] array2) {
        double average1 = Arrays.stream(array1).average().stream().sum();
        double average2 = Arrays.stream(array2).average().stream().sum();
        double result = 0;
        for (int i = 0; i < array1.length; i++) {
            result += Math.pow(array1[i] - average1, 2) * Math.pow(array2[i] - average2, 2);
        }
        return Math.sqrt(result);
    }

}