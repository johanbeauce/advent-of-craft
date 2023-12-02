package games;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        // use early return to avoid nested if statements
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }
        // use well named methods to make the code more readable
        if (isDivisibleByThreeAndFive(input)) {
            return "FizzBuzz";
        }
        if (isDivisibleByThree(input)) {
            return "Fizz";
        }
        if (isDivisibleByFive(input)) {
            return "Buzz";
        }

        return input.toString();
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= 0 || input > 100;
    }

    private static boolean isDivisibleByThree(Integer input) {
        return input % 3 == 0;
    }

    private static boolean isDivisibleByThreeAndFive(Integer input) {
        return input % 3 == 0 && input % 5 == 0;
    }

    private static boolean isDivisibleByFive(Integer input) {
        return input % 5 == 0;
    }
}
