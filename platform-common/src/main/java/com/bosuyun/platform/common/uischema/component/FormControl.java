package com.bosuyun.platform.common.uischema.component;

import com.bosuyun.platform.common.uischema.component.form.FormItemBase;
import com.bosuyun.platform.common.definition.UiComponentType;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

/**
 * 数据结构
 * <p>
 * Created by liuyuancheng on 2021/1/6  <br/>
 */
@NoArgsConstructor
public class FormControl extends Component {

    {
        put(TYPE, UiComponentType.FORM);
        put(CONTROLS, new LinkedList<FormItemBase>());
    }

    protected static final String CONTROLS = "controls";

    protected static final String API = "api";


    /**
     * 添加字段(每次添加字段都会触发重排序)
     *
     * @param controlItem
     * @return
     */
    @SuppressWarnings("unchecked")
    public FormControl addItem(final FormItemBase controlItem) {
        ((LinkedList<FormItemBase>) this.get(CONTROLS)).add(controlItem);
        return this;
    }
}
