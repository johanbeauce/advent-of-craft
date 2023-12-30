package blog;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {
    public static final String AUTHOR = "Pablo Escobar";
    private static final String COMMENT_TEXT = "Amazing article !!!";
    private Article article;

    @BeforeEach
    void setup() {
        article = ArticleBuilder.anArticle()
                .withName("Lorem Ipsum")
                .withContent("consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore")
                .build();
    }

    @Nested
    class Given_an_article_without_comment {
        @Test
        void should_have_no_comment() {
            assertThat(article.getComments()).isEmpty();
        }
    }

    @Nested
    class Given_an_article_with_a_comment {
        @BeforeEach
        void setup() throws CommentAlreadyExistException {
            article.addComment(COMMENT_TEXT, AUTHOR);
        }

        @Test
        void should_add_comment_in_an_article() {
            assertThat(article.getComments())
                    .hasSize(1)
                    .extracting(Comment::text, Comment::author)
                    .containsExactly(Tuple.tuple(COMMENT_TEXT, AUTHOR));
        }

        @Test
        void should_add_comment_in_an_article_containing_already_a_comment() throws CommentAlreadyExistException {
            var newComment = "Finibus Bonorum et Malorum";
            var newAuthor = "Al Capone";

            article.addComment(newComment, newAuthor);

            assertThat(article.getComments())
                    .hasSize(2)
                    .element(1)
                    .extracting(Comment::text, Comment::author)
                    .containsExactly(newComment, newAuthor);
        }

        @Nested
        class Fail {
            @Test
            void when_adding_an_existing_comment() {
                assertThatThrownBy(() -> article.addComment(COMMENT_TEXT, AUTHOR))
                        .isInstanceOf(CommentAlreadyExistException.class);
            }
        }
    }
}
