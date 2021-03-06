/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.vcs.log.ui.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.DumbAware;
import com.intellij.vcs.log.VcsLogDataKeys;
import com.intellij.vcs.log.VcsLogUi;
import icons.VcsLogIcons;
import org.jetbrains.annotations.NotNull;

public class ShowLongEdgesAction extends ToggleAction implements DumbAware {
  public ShowLongEdgesAction() {
    super("Show long edges", "Show long branch edges even if commits are invisible in the current view.", VcsLogIcons.ShowHideLongEdges);
  }

  @Override
  public boolean isSelected(@NotNull AnActionEvent e) {
    VcsLogUi ui = e.getData(VcsLogDataKeys.VCS_LOG_UI);
    return ui != null && ui.areLongEdgesVisible();
  }

  @Override
  public void setSelected(@NotNull AnActionEvent e, boolean state) {
    VcsLogUi ui = e.getData(VcsLogDataKeys.VCS_LOG_UI);
    if (ui != null) ui.setLongEdgeVisibility(state);
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);
    VcsLogUi ui = e.getData(VcsLogDataKeys.VCS_LOG_UI);
    e.getPresentation().setEnabled(ui != null && ui.areGraphActionsEnabled());
  }
}
