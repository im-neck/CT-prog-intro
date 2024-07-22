package markup;

import java.util.List;

//зачеркивание
public class Strikeout extends Markdownable {
    public  Strikeout (List<ToolsForText> list){
        super(list, "~","s");
    }
}
