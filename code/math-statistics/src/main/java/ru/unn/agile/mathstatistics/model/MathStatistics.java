package ru.unn.agile.mathstatistics.model;

public final class MathStatistics {
    public static final int MEAN_ORDER = 1;
    public static final int DISPERSION_ORDER = 2;
    public static final Double EPSILON = 1e-3;

    private MathStatistics() {
    }

    static Double calculateExpectedValue(final Number[] data, final Double[] probabilityVector) {
        checkInputData(data, probabilityVector);

        Double expectedValue = 0.0;
        for (int i = 0; i < data.length; ++i) {
            expectedValue += probabilityVector[i] * data[i].doubleValue();
        }
        return expectedValue;
    }

    static Double calculateDispersion(final Number[] data, final Double[] probabilityVector) {
        final Double expectedValue = calculateExpectedValue(data, probabilityVector);

        Double dispersion = 0.0;
        for (int i = 0; i < data.length; ++i) {
            dispersion += probabilityVector[i]
                    * Math.pow((data[i].doubleValue() - expectedValue), DISPERSION_ORDER);
        }

        return dispersion;
    }

    static Double calculateMoment(final Number[] data, final Double[] probabilityVector,
                                  final int order, final Number offset) {
        checkInputData(data, probabilityVector);

        if (order <= 0) {
            throw new IllegalArgumentException("Order must be non-zero and positive value!");
        }

        Double moment = 0.0;
        for (int i = 0; i < data.length; ++i) {
            moment += probabilityVector[i]
                    * Math.pow((data[i].doubleValue() - offset.doubleValue()), order);
        }

        return moment;
    }

    private static void checkInputData(final Number[] data, final Double[] probabilityVector) {
        if (data == null || probabilityVector == null) {
            throw new IllegalArgumentException("Array should be initialized!");
        }

        if (data.length < 1 || probabilityVector.length < 1) {
            throw new IllegalArgumentException("Array length must be non-zero value!");
        }

        if (data.length != probabilityVector.length) {
            throw new IllegalArgumentException("Data and probability array length is not equal!");
        }

        Double sum = 0.0;
        for (int i = 0; i < data.length; ++i) {
            if (data[i] == null || probabilityVector[i] == null) {
                throw new IllegalArgumentException("Null element shouldn't be in array!");
            }
            if (!(0 <= probabilityVector[i] && probabilityVector[i] <= 1)) {
                throw new IllegalArgumentException("Probability is out of range [0;1]!");
            }
            sum += probabilityVector[i];
        }

        if (Math.abs(1.0 - sum) > EPSILON) {
            throw new IllegalArgumentException("The total probability must be equal to 1!");
        }
    }

}
