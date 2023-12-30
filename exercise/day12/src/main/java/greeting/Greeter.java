package greeting;

import java.util.Map;
import java.util.Optional;

public class Greeter {
    String formality;

    Map<String, String> greetings = Map.of(
        "formal", "Good evening, sir.",
        "casual", "Sup bro?",
        "intimate", "Hello Darling!",
        "default", "Hello."
    );

    public String greet() {
        return Optional.ofNullable(this.formality)
            .map(greetings::get)
            .orElseGet(() -> greetings.get("default"));
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }
}
