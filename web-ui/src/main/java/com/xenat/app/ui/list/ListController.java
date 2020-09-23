package com.xenat.app.ui.list;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.MessageContainer;
import com.github.bordertech.wcomponents.SimpleBeanBoundTableModel;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WCancelButton;
import com.github.bordertech.wcomponents.WDateField;
import com.github.bordertech.wcomponents.WFieldLayout;
import com.github.bordertech.wcomponents.WMessages;
import com.github.bordertech.wcomponents.WMultiTextField;
import com.github.bordertech.wcomponents.WPanel;
import com.github.bordertech.wcomponents.WSection;
import com.github.bordertech.wcomponents.WTable;
import com.github.bordertech.wcomponents.WTableColumn;
import com.github.bordertech.wcomponents.WText;
import com.github.bordertech.wcomponents.WTextField;
import com.github.bordertech.wcomponents.layout.ColumnLayout;
import com.github.bordertech.wcomponents.validation.ValidatingAction;
import com.xenat.app.model.list.ListCriteria;
import com.xenat.app.model.list.ListDetail;
import com.xenat.app.service.list.ListServices;
import com.xenat.app.ui.application.XenatApp;
import com.xenat.app.ui.common.Constants;
import com.xenat.app.ui.common.XenatWMessages;
import com.xenat.app.ui.util.ListServicesHelperFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;
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

	private final WTextField txtModuleFrom =  new WTextField();
	private final WTextField txtModuleTo = new WTextField();
	private final WDateField dtSourceFromTs = new WDateField();
	private final WDateField dtSourceToTs = new WDateField();
	private final WMultiTextField txtLibraries = new WMultiTextField();
	private final WMultiTextField txtUserIds = new WMultiTextField();

	private final WPanel criteriaPanel = new WPanel();
	private final WFieldLayout layout = new WFieldLayout(WFieldLayout.LAYOUT_FLAT);
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

		content.add(criteriaPanel);
		layout.setLabelWidth(20);
		layout.setMargin(Constants.NORTH_MARGIN_LARGE);
		criteriaPanel.add(layout);

		layout.addField("Module From", txtModuleFrom);
		txtModuleFrom.setMandatory(true);
		layout.addField("Module To", txtModuleTo);
		layout.addField("Source Timestamp changed from", dtSourceFromTs);
		layout.addField("Source Timestamp changed to", dtSourceToTs);
		layout.addField("Libraries", txtLibraries);
		txtLibraries.setToolTip("Leave blank for all libraries");
		layout.addField("User Ids", txtUserIds);
		txtUserIds.setToolTip("Leave blank for all user ids");
		setupCriteriaButtonPanel();

		// Table
		content.add(resultsPanel);
		resultsPanel.setMargin(Constants.NORTH_MARGIN_LARGE);
		resultsPanel.add(table);
		setupTable();

		// Ids
		setIdName("srch");
		setNamingContext(true);
	}

	/**
	 * Add some buttons to the criteria panel.
	 */
	private void setupCriteriaButtonPanel() {
		// Reset button
		WCancelButton buttonReset = new WCancelButton("Reset");
		// Search button
		WButton buttonSearch = new WButton("Search");
		buttonSearch.setAction(new ValidatingAction(messages.getValidationErrors(), layout) {
			@Override
			public void executeOnValid(final ActionEvent event) {
				doSearch(createListCriteria());
			}
		});

		WAjaxControl ajax = new WAjaxControl(buttonSearch, new AjaxTarget[]{txtModuleFrom, messages, resultsPanel});
		getContent().add(ajax);

		// Button layout
		WPanel buttonPanel = new WPanel(WPanel.Type.FEATURE);
		buttonPanel.setLayout(new ColumnLayout(new int[]{Constants.HALF, Constants.HALF},
				new ColumnLayout.Alignment[]{ColumnLayout.Alignment.LEFT, ColumnLayout.Alignment.RIGHT}));
		buttonPanel.setMargin(Constants.NORTH_MARGIN);
		criteriaPanel.add(buttonPanel);

		// Add buttons
		buttonPanel.add(buttonReset);
		buttonPanel.add(buttonSearch);

		// Reset button Action
		buttonReset.setAccessKey('R');
		buttonReset.setAction((Action) event -> reset());

		// Set the default button for the panel
		criteriaPanel.setDefaultSubmitButton(buttonSearch);
	}

	/**
	 * Create criteria from selection panel.
	 */
	private ListCriteria createListCriteria() {
		ListCriteria listCriteria = new ListCriteria();
		listCriteria.setModuleFrom(txtModuleFrom.getText());
		listCriteria.setModuleTo(txtModuleTo.getText());
		listCriteria.setSourceFromTs(dtSourceFromTs.getDate());
		listCriteria.setSourceToTs(dtSourceToTs.getDate());
		String[] libs = txtLibraries.getTextInputs();
		if (libs != null) {
			listCriteria.setLibrary(Arrays.asList(libs));
		}
		String[] users = txtUserIds.getTextInputs();
		if (users != null) {
			listCriteria.setUserId(Arrays.asList(users));
		}
		return listCriteria;
	}

	/**
	 * Setup the results table.
	 */
	private void setupTable() {
		// FIXME Make a proper table...
		// TODO also, ensure proper date conversion etc WTimestampText
		table.setIdName("rs");

		table.setMargin(Constants.SOUTH_MARGIN_LARGE);
		table.addColumn(new WTableColumn("library", new WText()));
		table.addColumn(new WTableColumn("objDateTime", new WDateField()));
		table.addColumn(new WTableColumn("object", new WText()));
		table.addColumn(new WTableColumn("objGdaName", new WText()));
		table.addColumn(new WTableColumn("objSize", new WText()));
		table.addColumn(new WTableColumn("objType", new WText()));
		table.addColumn(new WTableColumn("objUser", new WText()));
		table.addColumn(new WTableColumn("objVersion", new WText()));
		table.addColumn(new WTableColumn("srcDateTime", new WDateField()));
		table.addColumn(new WTableColumn("srcProgMode", new WText()));
		table.addColumn(new WTableColumn("srcSize", new WText()));
		table.addColumn(new WTableColumn("srcType", new WText()));
		table.addColumn(new WTableColumn("srcUser", new WText()));
		table.addColumn(new WTableColumn("srcVersion", new WText()));


		table.setStripingType(WTable.StripingType.ROWS);
		table.setNoDataMessage("No clients found.");

		SimpleBeanBoundTableModel model = new SimpleBeanBoundTableModel(new String[]{"library",
				"objDateTime",
				"object",
				"objGdaName",
				"objSize",
				"objType",
				"objUser",
				"objVersion",
				"srcDateTime",
				"srcProgMode",
				"srcSize",
				"srcType",
				"srcUser",
				"srcVersion"
		});
		table.setTableModel(model);

		table.setBeanProperty(".");

		table.setVisible(false);
	}

	private void doSearch(final ListCriteria listCriteria) {
		try {
			table.reset();
			// FIXME Need to handle error message response here...
			List<ListDetail> modules = CLIENT_SERVICES.retrieveListDetails(listCriteria).getData();
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
