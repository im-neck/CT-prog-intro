package markup;

import java.util.List;

//выделение
public class Emphasis extends Markdownable {
    public Emphasis (List<ToolsForText> list){
        super(list, "*","i");
    }
}
