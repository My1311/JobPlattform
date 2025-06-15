package de.hbrs.se2.util;

import com.vaadin.flow.component.UI;
import de.hbrs.se2.model.advertisement.Advertisement;
import de.hbrs.se2.model.user.User;
import jakarta.annotation.Nullable;

public class SessionAttributes {
    public static @Nullable User getCurrentUser() {
        try {
            return (User) UI.getCurrent().getSession().getAttribute(Constant.Value.CURRENT_USER);
        }catch (NullPointerException e) {
            return null;
        }
    }
    public static void setCurrentUser(User user) {
        UI.getCurrent().getSession().setAttribute(Constant.Value.CURRENT_USER, user);
    }

    public static void reloadSite() {
        UI.getCurrent().getPage().reload();
    }

    public static @Nullable Advertisement getCurrentAdvertisement() {
        try {
            return (Advertisement) UI.getCurrent().getSession().getAttribute(Constant.Value.CURRENT_AD);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
