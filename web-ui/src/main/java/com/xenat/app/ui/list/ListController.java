package com.xenat.app.ui.list;

import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.SimpleBeanBoundTableModel;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WFieldLayout;
import com.github.bordertech.wcomponents.WList;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WTable;
import com.github.bordertech.wcomponents.WTableColumn;
import com.github.bordertech.wcomponents.WText;
import com.github.bordertech.wcomponents.WTextField;
import com.github.bordertech.wcomponents.validation.ValidatingAction;
import com.xenat.app.model.list.ListDetail;
import com.xenat.app.service.list.ListServices;
import com.xenat.app.ui.application.XenatApp;
import com.xenat.app.ui.common.Constants;
import com.xenat.app.ui.common.XenatWMessages;
import com.xenat.app.ui.util.ListServicesHelperFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Used to list modules on mainframe servers
 *
 * @author Trentan Healey
 * @since POC1
 */
public class ListController extends WSection implements MessageContainer {

	private static final Log LOG = LogFactory.getLog(ListController.class);

	private static final ListServices CLIENT_SERVICES = ListServicesHelperFactory.getInstance();

	private final WMessages messages = new XenatWMessages();

	private final XenatApp app;

	private final WTextField txtSearch = new WTextField();

	private final WTextField txtDbid = new WTextField();

	private final WPanel resultsPanel = new WPanel();

	private final WTable table = new WTable();

	/**
	 * The Starting class of our app.
	 */
	public ListController(final XenatApp app) {
		super("List Modules");
		this.app = app;

		WPanel content = getContent();

		// Messages
		content.add(messages);

		WFieldLayout layout = new WFieldLayout(WFieldLayout.LAYOUT_FLAT);
		layout.setLabelWidth(20);
		layout.setMargin(Constants.NORTH_MARGIN_LARGE);
		content.add(layout);

		layout.addField("Module name", txtSearch);
		txtSearch.setMandatory(true);
		layout.addField("Database ID", txtDbid);
		txtDbid.setMandatory(true);

		WButton searchButton = new WButton("Search");
		searchButton.setAction(new ValidatingAction(messages.getValidationErrors(), layout) {
			@Override
			public void executeOnValid(final ActionEvent event) {
				doSearch(txtSearch.getValue(), txtDbid.getValue());
			}
		});
		layout.addField(searchButton);

		WAjaxControl ajax = new WAjaxControl(searchButton, new AjaxTarget[]{txtSearch, txtDbid, messages, resultsPanel});
		content.add(ajax);

		// Table
		content.add(resultsPanel);
		resultsPanel.setMargin(Constants.NORTH_MARGIN_LARGE);
		resultsPanel.add(table);
		setupTable();

		// Ids
		setIdName("srch");
		setNamingContext(true);

		content.setDefaultSubmitButton(searchButton);
	}

	/**
	 * Setup the results table.
	 */
	private void setupTable() {
		// FIXME Make a proper table...
		table.setIdName("rs");
		WList listCountry = new WList(WList.Type.STACKED);

		table.setMargin(Constants.SOUTH_MARGIN_LARGE);
		table.addColumn(new WTableColumn("ID", new WText()));
		table.addColumn(new WTableColumn("Name", new WText()));
		table.addColumn(new WTableColumn("Address", new WText()));
		table.addColumn(new WTableColumn("ID2", new WText()));
		table.addColumn(new WTableColumn("Name2", new WText()));
		table.addColumn(new WTableColumn("Address2", new WText()));
		table.setStripingType(WTable.StripingType.ROWS);
		table.setNoDataMessage("No clients found.");

		SimpleBeanBoundTableModel model = new SimpleBeanBoundTableModel(new String[]{"library", "object", "srcType", "srcDateTime", "srcUser", "objDateTime", "objUser"});
		table.setTableModel(model);

		table.setBeanProperty(".");

		table.setVisible(false);
	}

	/**
	 * Refresh results. Remove results from the cache.
	 */
	public void doReset() {
		reset();
	}

	private void doSearch(final String module, final String dbid) {
		try {
			table.reset();
			// FIXME Need to handle error message response here...
			List<ListDetail> modules = CLIENT_SERVICES.retrieveListDetails(module, dbid).getData();
			if (modules.isEmpty()) {
				messages.info("No modules found");
				return;
			}
			table.setBean(modules);
			table.setVisible(true);
		} catch (Exception e) {
			String msg = "Error searching for modules. " + e.getMessage();
			LOG.error(msg, e);
			messages.error(msg);
		}

	}

	@Override
	public WMessages getMessages() {
		return messages;
	}
}
