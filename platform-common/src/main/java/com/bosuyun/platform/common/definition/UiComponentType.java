package com.bosuyun.platform.common.definition;

import lombok.Getter;

/**
 * Created by liuyuancheng on 2020/12/16  <br/>
 */
public enum UiComponentType {
    FORM("form"),
    TEXT("text"),
    SELECT("select"),
    ARRAY("array"),
    BUTTON("button"),
    ACTION("action"),
    BUTTON_TOOLBAR("button_toolbar"),
    BUTTON_GROUP("button_group"),
    RESET("reset"),
    SUBMIT("submit"),
    CHAINED_SELECTED("chained-selected"),
    CHECKBOX("checkbox"),
    CHECKBOXES("checkboxes"),
    CITY("city"),
    COLOR("color"),
    COMBO("combo"),
    DATE("date"),
    DATETIME("datetime"),
    MONTH("month"),
    DATE_RANGE("date-range"),
    DATETIME_RANGE("datetime-range"),
    DIFF_EDITOR("diff-editor"),
    EDITOR("editor"),
    FIELD_SET("field-set"),
    NUMBER("number"),
    RADIOS("radios"),
    GRID("grid"),
    GROUP("group"),
    HBOX("hbox"),
    HIDDEN("hidden"),
    IMAGE("image"),
    INPUT_GROUP("input-group"),
    LIST("list"),
    UUID("uuid"),
    MATRIX("matrix"),
    NESTED_SELECT("nested-select"),
    PANEL("panel"),
    PICKER("picker"),
    RATING("rating"),
    RANGE("range"),
    REPEAT("repeat"),
    RICH_TEXT("rich-text"),
    SERVICE("service"),
    SWITCH("switch"),
    STATIC("static"),
    TABS("tabs"),
    TABLE("table"),
    TAG("tag"),
    TEXTAREA("textarea"),
    TIME("time"),
    TRANSFER("transfer"),
    TABS_TRANSFER("tabs-transfer"),
    TREE("tree"),
    TREE_SELECT("tree-select"),
    YEAR("year")
    ;

    @Getter
    private final String componentType;

    UiComponentType(final String name){
        this.componentType = name;
    }
}
