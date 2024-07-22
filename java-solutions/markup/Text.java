package markup;

public class Text implements ToolsForText {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder bob) {
        bob.append(text);

    }
    public void toBBCode(StringBuilder bob) {
        bob.append(text);
    }
}