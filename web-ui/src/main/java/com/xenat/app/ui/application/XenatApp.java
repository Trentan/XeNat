package com.xenat.app.ui.application;

import com.github.bordertech.wcomponents.HeadingLevel;
import com.github.bordertech.wcomponents.Margin;
import com.github.bordertech.wcomponents.MenuItem;
import com.github.bordertech.wcomponents.MenuSelectContainer;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.Size;
import com.github.bordertech.wcomponents.WApplication;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WHeading;
import com.github.bordertech.wcomponents.WMenu;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WText;
import com.github.bordertech.wcomponents.WTimeoutWarning;
import com.github.bordertech.wcomponents.WebUtilities;
import com.xenat.app.ui.common.XenatAppCardPath;
import com.xenat.app.ui.common.XenatMenuItem;
import com.xenat.app.ui.common.XenatPathUtil;
import com.xenat.app.ui.common.XenatWCardManager;
import com.xenat.app.ui.common.XenatWMessages;
import com.xenat.app.ui.list.ListController;

import java.util.Date;

import static com.xenat.app.ui.common.XenatAppCardPath.XENAT_LIST;

/**
 * The root WComponent application for the Xenat web UI.
 */
public class XenatApp extends WApplication implements MessageContainer {

	/** Messages. */
	private final WMessages messages = new XenatWMessages();

	/** Card manager. */
	private final XenatWCardManager mgr = new XenatWCardManager();

	/** Menu. */
	private final WMenu menu = new WMenu(WMenu.MenuType.BAR);

	/** Detail panel. */
	WPanel detail = new WPanel();

	/**
	 * Creates a new Xenat App.
	 */
	public XenatApp() {

		// Custom css
		addCssFile("/css/app.css");

		// Header
		final WPanel header = new WPanel(WPanel.Type.HEADER);
		add(header);
		header.setIdName("hdr");
		header.add(new WHeading(HeadingLevel.H1, "XeNat"));

		// Menu
		add(menu);
		setupMenu();

		// Detail
		detail.setMargin(new Margin(Size.LARGE));
		add(detail);

		// Card manager
		detail.add(mgr);

		// Messages
		messages.setIdName("msgs");
		detail.add(messages);

		// Cards
		WSection listController = new ListController(this);
		listController.setMargin(new Margin(Size.LARGE));

		// Card and URL
		mgr.addWithPath(listController, XENAT_LIST);

		// Footer
		final WPanel footer = new WPanel(WPanel.Type.FOOTER);
		add(footer);
		footer.add(new WText(new Date().toString()));

		add(new WTimeoutWarning());
	}

	/**
	 * Setup menu.
	 */
	private void setupMenu() {
		// Selection mode
		menu.setIdName("mnu");
		menu.setSelectionMode(MenuSelectContainer.SelectionMode.SINGLE);

		// List
		XenatMenuItem menuList = new XenatMenuItem("List", XENAT_LIST);
		menu.add(menuList);
	}

	@Override
	public void handleRequest(final Request request) {
		super.handleRequest(request);
		// Check path URL and View Access
		XenatPathUtil.checkPathAndAccess(mgr, this);
	}

	@Override
	protected void preparePaintComponent(final Request request) {
		super.preparePaintComponent(request);

		if (!isInitialised()) {
			menu.setVisible(true);
			detail.setVisible(true);
			setInitialised(true);
		}

		// Set selected menu
		selectMenu();
	}

	private void selectMenu() {
		XenatAppCardPath currentPath = mgr.getCurrentPath();
		XenatMenuItem selected = (XenatMenuItem) menu.getSelectedMenuItem();
		// Correct menu item already selected
		if (selected != null && selected.getPath() == currentPath) {
			return;
		}
		// Find the correct menu item and select it for display
		menu.clearSelectedMenuItems();
		for (MenuItem item : menu.getMenuItems(true)) {
			if (item instanceof XenatMenuItem) {
				XenatMenuItem menuItem = (XenatMenuItem) item;
				if (menuItem.getPath() == currentPath) {
					menu.setSelectedMenuItem(menuItem);
					break;
				}
			}
		}
	}

	@Override
	public WMessages getMessages() {
		return messages;
	}

	@Override
	public void handleStepError() {
		super.handleStepError();
		getMessages()
				.warn("A request was made that is not in the expected sequence and the application has been refreshed to its current state.");
	}

	/**
	 * Retrieves the XenatApp ancestor of the given component.
	 *
	 * @param descendant the component to find the XenatApp ancestor of.
	 * @return the XenatApp ancestor for the given component, or null if not found.
	 */
	public static XenatApp getInstance(final WComponent descendant) {
		return WebUtilities.getAncestorOfClass(XenatApp.class, descendant);
	}

}
