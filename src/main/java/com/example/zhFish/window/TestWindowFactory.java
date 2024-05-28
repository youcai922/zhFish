package com.example.zhFish.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author yucan
 * @date 2024/5/24 15:01
 */
public class TestWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        //创建出windows对象
        TestWindow testWindow = new TestWindow(project, toolWindow);
        //获取内容工厂实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        //获取用于toolwindow显示的内容
        Content content = contentFactory.createContent(testWindow.getContentPanel(), "", false);
        //给toolwindow设置内容
        toolWindow.getContentManager().addContent(content);
    }
}
