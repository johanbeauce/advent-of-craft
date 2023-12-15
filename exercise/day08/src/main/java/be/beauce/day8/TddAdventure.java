package be.beauce.day8;

import java.util.Arrays;

public class TddAdventure {
    private static final Character[] SPECIAL_CHARACTERS = {'.', '*', '#', '@', '$', '%', '&'};

    public boolean verifyPassword(String password) {
        return containsAtLeastHeightCharacters(password)
                && containsAtLeastOneLowercaseLetter(password)
                && containsAtLeastOneCapitalLetter(password)
                && containsAtLeastOneNumber(password)
                && containsAtLeastOneSpecialCharacter(password)
                && charactersShouldBeOneOf(password);
    }

    private boolean containsAtLeastHeightCharacters(String password) {
        return password.length() >= 8;
    }

    private boolean containsAtLeastOneCapitalLetter(String password) {
        return password.chars()
                .anyMatch(Character::isUpperCase);
    }

    private boolean containsAtLeastOneLowercaseLetter(String password) {
        return password.chars()
                .anyMatch(Character::isLowerCase);
    }

    private boolean containsAtLeastOneNumber(String password) {
        return password.chars()
                .anyMatch(Character::isDigit);
    }

    private boolean containsAtLeastOneSpecialCharacter(String password) {
        return password.chars()
                .anyMatch(c -> Arrays.asList(SPECIAL_CHARACTERS).contains((char) c));
    }

    public boolean charactersShouldBeOneOf(String password) {
        return password.chars()
                .allMatch(c -> Character.isLetterOrDigit(c) || Arrays.asList(SPECIAL_CHARACTERS).contains((char) c));
    }
}
