package games;

import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;
    public static final int FIZZ = 3;
    public static final int BUZZ = 5;
    public static final int FIZZ_BUZZ = 15;

    public static final Map<Integer, String> FIZZ_BUZZ_MAP = Map.of(
            FIZZ_BUZZ, "FizzBuzz",
            FIZZ, "Fizz",
            BUZZ, "Buzz"
    );

    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        var safeInteger = getSafely(input);
        return convertSafely(safeInteger);
    }

    public static int getSafely(Integer input) throws OutOfRangeException {
        return IntStream.range(MIN + 1, MAX + 1)
                .filter(i -> i == input)
                .findFirst()
                .orElseThrow(OutOfRangeException::new);
    }

    private static String convertSafely(Integer input) {
        return Stream.of(FIZZ_BUZZ, FIZZ, BUZZ)
                .filter(divisor -> is(divisor, input))
                .map(FIZZ_BUZZ_MAP::get)
                .findFirst()
                .orElseGet(input::toString);
    }

    private static boolean is(Integer divisor, Integer input) {
        return input % divisor == 0;
    }
}
