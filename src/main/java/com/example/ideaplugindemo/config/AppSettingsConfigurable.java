package com.example.ideaplugindemo.config;

import com.example.ideaplugindemo.config.window.AppSettingsComponent;
import com.example.ideaplugindemo.window.TestWindow;
import com.intellij.openapi.options.Configurable;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

/**
 * @author yucan
 * @date 2024/5/27 14:24
 */
public class AppSettingsConfigurable implements Configurable {

    private AppSettingsComponent mySettingsComponent;

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    @Override
    @Nls(capitalization = Nls.Capitalization.Title)
    public String getDisplayName() {
        return "zhFish";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return mySettingsComponent.getPreferredFocusedComponent();
    }

    /**
     * 进入到这个设置界面，初始化UI
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        mySettingsComponent = new AppSettingsComponent();
        return mySettingsComponent.getPanel();
    }

    /**
     * 判断是否修改了，如果修改了，那么apply则回值灰
     * @return
     */
    @Override
    public boolean isModified() {
        AppSettingsState settings = AppSettingsState.getInstance();
        boolean modified = !mySettingsComponent.getFontSize().equals(settings.fontSize);
        return modified;
    }

    /**
     * idea设置窗口，点击apply调用该方法
     */
    @Override
    public void apply() {
        AppSettingsState settings = AppSettingsState.getInstance();
        settings.fontSize = mySettingsComponent.getFontSize();
    }

    @Override
    public void reset() {
        AppSettingsState settings = AppSettingsState.getInstance();
        mySettingsComponent.setFontSize(settings.fontSize);
    }

    @Override
    public void disposeUIResources() {
        mySettingsComponent = null;
    }
}