package markup;

import java.util.List;

public class Paragraph implements ToolsForText {
    private final List<ToolsForText> input;

    public Paragraph(List<ToolsForText> input) {
        this.input = input;
    }

    public void toMarkdown(StringBuilder bob) {
        for (ToolsForText el : input) {
            el.toMarkdown(bob);
        }
    }

    public void toBBCode(StringBuilder bob) {
        for (ToolsForText el : input) {
            el.toBBCode(bob);
        }
    }
}
