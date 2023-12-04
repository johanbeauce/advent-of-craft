package blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Testing Article")
class ArticleTests {

    @Nested
    class Given_an_article {

        private Article article;

        @BeforeEach
        void setUp() {
            // avoid repeating the same code so create instance of article in a @BeforeEach method
            article = new Article(
                    "Lorem Ipsum",
                    "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
            );
        }

        @Nested
        class Adding_comment_with_text_and_author {

            private String text;
            private String author;

            @BeforeEach
            void setUp() throws CommentAlreadyExistException {
                // create a second nested class to add comment to article
                text = "Amazing article !!!";
                author = "Pablo Escobar";
                article.addComment(text, author);
            }

            @Test
            void should_contains_a_comment_with_the_given_text() {
                // simplify the test by checking only text of the comment
                assertThat(article.getComments())
                        .hasSize(1)
                        .anyMatch(comment -> comment.text().equals(text));
            }

            @Test
            void should_contains_a_comment_with_the_given_author() {
                // simplify the test by checking only author of the comment
                assertThat(article.getComments())
                        .hasSize(1)
                        .anyMatch(comment -> comment.author().equals(author));
            }

            @Test
            void should_throw_an_exception_when_adding_existing_comment() {
                assertThatThrownBy(() -> article.addComment(text, author))
                        .isInstanceOf(CommentAlreadyExistException.class);
            }
        }
        // remove test with the check of the date of the day; it's useless
    }
}
