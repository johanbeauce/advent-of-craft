package blog;

public final class ArticleBuilder {
    private String name;
    private String content;

    private ArticleBuilder() {
    }

    public static ArticleBuilder anArticle() {
        return new ArticleBuilder();
    }

    public ArticleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ArticleBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public Article build() {
        return new Article(name, content);
    }
}
