/*
 * Copyright (C) 2003-2008 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.forum.webui.popup;

import org.exoplatform.forum.webui.UIForumPortlet;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIPopupComponent;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;

@ComponentConfig(
    lifecycle = UIFormLifecycle.class,
    template = "app:/templates/forum/webui/popup/UIViewListPostOrThreadByUser.gtmpl",
    events = {
      @EventConfig(listeners = UIViewPostedByUser.CloseActionListener.class)
    }
)
public class UIViewPostedByUser extends UIForm implements UIPopupComponent {
  public UIViewPostedByUser() throws Exception {
    addChild(UIPageListPostByUser.class, null, "UIPageListPostByUser");
  }

  public void activate() {
  }

  public void deActivate() {
  }

  public void setUserProfile(String userId) {
    this.getChild(UIPageListPostByUser.class).setUserName(userId);
  }

  static public class CloseActionListener extends EventListener<UIViewPostedByUser> {
    public void execute(Event<UIViewPostedByUser> event) throws Exception {
      UIForumPortlet forumPortlet = event.getSource().getAncestorOfType(UIForumPortlet.class);
      forumPortlet.cancelAction();
    }
  }
}
