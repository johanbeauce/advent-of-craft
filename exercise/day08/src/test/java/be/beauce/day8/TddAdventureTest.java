package be.beauce.day8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Password verification")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TddAdventureTest {
    TddAdventure tddAdventure;

    @BeforeEach
    void setUp() {
        tddAdventure = new TddAdventure();
    }

    public static Stream<Arguments> verifyPasswordAtLeast8CharactersProvider() {
        return Stream.of(
                Arguments.of("Aa*45678", true),
                Arguments.of("12*4567", false),
                Arguments.of("Aa*456789", true));
    }

    @ParameterizedTest
    @MethodSource("verifyPasswordAtLeast8CharactersProvider")
    void it_contains_at_least_8_characters(String password, boolean valid) {
        assertThat(tddAdventure.verifyPassword(password)).isEqualTo(valid);
    }

    public static Stream<Arguments> verifyPasswordAtLeastOneCapitalLetterProvider() {
        return Stream.of(
                Arguments.of("1*3456a8", false),
                Arguments.of("1*3456aA", true));
    }

    @ParameterizedTest
    @MethodSource("verifyPasswordAtLeastOneCapitalLetterProvider")
    void it_contains_at_least_one_capital_letter(String password, boolean valid) {
        assertThat(tddAdventure.verifyPassword(password)).isEqualTo(valid);
    }

    public static Stream<Arguments> verifyPasswordAtLeastOneLowercaseLetterProvider() {
        return Stream.of(
                Arguments.of("A*345678", false),
                Arguments.of("A*34567a", true));
    }

    @ParameterizedTest
    @MethodSource("verifyPasswordAtLeastOneLowercaseLetterProvider")
    void it_contains_at_least_one_lowercase_letter(String password, boolean valid) {
        assertThat(tddAdventure.verifyPassword(password)).isEqualTo(valid);
    }

    public static Stream<Arguments> verifyPasswordAtLeastOneNumberProvider() {
        return Stream.of(
                Arguments.of("A*aaaaaaa", false),
                Arguments.of("A*aaaaaa1", true));
    }

    @ParameterizedTest
    @MethodSource("verifyPasswordAtLeastOneNumberProvider")
    void it_contains_at_least_a_number(String password, boolean valid) {
        assertThat(tddAdventure.verifyPassword(password)).isEqualTo(valid);
    }

    public static Stream<Arguments> verifyPasswordAtLeastOneSpecialCharacterProvider() {
        return Stream.of(
                Arguments.of("Aaaaaaaa1", false),
                Arguments.of("Aa1aaaaa*", true));
    }

    @ParameterizedTest
    @MethodSource("verifyPasswordAtLeastOneSpecialCharacterProvider")
    void it_contains_at_least_a_special_character(String password, boolean valid) {
        assertThat(tddAdventure.verifyPassword(password)).isEqualTo(valid);
    }

    public static Stream<Arguments> verifyPasswordDoNotContainsOtherSpecialCharacterProvider() {
        return Stream.of(
                Arguments.of("A*a)a*a1", false),
                Arguments.of("A*a%a*a1", true),
                Arguments.of("Aa1aaaa*", true));
    }

    @ParameterizedTest
    @MethodSource("verifyPasswordDoNotContainsOtherSpecialCharacterProvider")
    void it_should_not_contains_other_special_character(String password, boolean valid) {
        assertThat(tddAdventure.verifyPassword(password)).isEqualTo(valid);
    }

    @Test
    void name() {
        assertThat(tddAdventure.charactersShouldBeOneOf("%")).isTrue();
    }
}
