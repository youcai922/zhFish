package com.yc.zhFish.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;

/**
 * @author yucan
 * @date 2024/5/27 14:20
 */
@State(
        name = "com.yc.zhFish.config.AppSettingsState",
        storages = @Storage("plugin.xml")
)
public class AppSettingsState implements PersistentStateComponent<AppSettingsState> {

    public Integer fontSize = 20;

    public static AppSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(AppSettingsState.class);
    }

    @Nullable
    @Override
    public AppSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull AppSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}