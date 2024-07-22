package markup;

import java.util.List;

//сильное выделение
public class Strong extends Markdownable {
    public Strong (List<ToolsForText> list){
        super(list, "__","b");
    }
}