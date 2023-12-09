package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {

    @ParameterizedTest
    @ValueSource(ints = {1, 67, 82})
    void returns_the_given_number_for(int input) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input))
                .isEqualTo(String.valueOf(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 66, 99})
    void returns_Fizz_for(int input) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input))
                .isEqualTo("Fizz");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 50, 85})
    void returns_Buzz_for(int input) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input))
                .isEqualTo("Buzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    void returns_FizzBuzz_for(int input) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input))
                .isEqualTo("FizzBuzz");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 101, -1})
    void throws_an_exception_for(int input) {
        assertThatThrownBy(() -> FizzBuzz.convert(input))
                .isInstanceOf(OutOfRangeException.class);
    }
}
