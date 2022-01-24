package com.example.springplug.conversion;

import com.example.springplug.req.User;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * @author liluming
 * @className: StringToUserPropertyEditor
 * @description:
 * @date 2021/12/16 6:55 下午
 */
public class StringToUserPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User user = new User();
        user.setName(text);
        this.setValue(user);
    }
}