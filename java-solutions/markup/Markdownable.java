package markup;

import java.util.List;

public abstract class Markdownable implements ToolsForText {
    private final List<ToolsForText> input; // это мое поле - эл-ты доступные в классе
    private final String tagM;
    private final String tagB;

    public Markdownable(List<ToolsForText> list, String tagM1, String tagB1) {
        this.tagM = tagM1;
        this.tagB = tagB1;
        this.input = list;
    }

    public void toMarkdown(StringBuilder bob) {
        bob.append(tagM);
        for (ToolsForText el : input) {
            el.toMarkdown(bob);
        }
        bob.append(tagM);
    }

    public void toBBCode (StringBuilder bob) {
        String start = "[" + tagB +"]";
        String end = "[/" + tagB +"]";
        bob.append(start);
        for (ToolsForText el : input) {
            el.toBBCode(bob);
        }
        bob.append(end);
    }
}
