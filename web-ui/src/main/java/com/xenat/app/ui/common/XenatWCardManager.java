package com.xenat.app.ui.common;

import com.github.bordertech.wcomponents.WCardManager;
import com.github.bordertech.wcomponents.WComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Give each card a unique PATH. This card manager is used at the top of the Application.
 *
 * @author Trentan Healey
 * @since POC1
 */
public class XenatWCardManager extends WCardManager {

	/**
	 * @param component the card to add
	 * @param path the matching url for the card
	 */
	public void addWithPath(final WComponent component, final XenatAppCardPath path) {
		add(component);
		XenatCardManagerModel model = getOrCreateComponentModel();
		if (model.pathToComponent == null) {
			model.pathToComponent = new HashMap<>();
		}
		model.pathToComponent.put(path, component);
	}

	/**
	 * @return the path of the current card
	 */
	public XenatAppCardPath getCurrentPath() {
		return getPathForCard(getVisible());
	}

	/**
	 * @param path the Xenat path
	 * @return the matching card
	 */
	public WComponent getCardForPath(final XenatAppCardPath path) {
		XenatCardManagerModel model = getComponentModel();
		if (model.pathToComponent != null) {
			return model.pathToComponent.get(path);
		}
		return null;
	}

	/**
	 * @param component the card
	 * @return the url
	 */
	public XenatAppCardPath getPathForCard(final WComponent component) {
		XenatCardManagerModel model = getComponentModel();
		if (model.pathToComponent != null) {
			for (Entry<XenatAppCardPath, WComponent> entry : model.pathToComponent.entrySet()) {
				if (component == entry.getValue()) {
					return entry.getKey();
				}
			}
		}
		return null;
	}

	@Override
	protected XenatCardManagerModel newComponentModel() {
		return new XenatCardManagerModel();
	}

	@Override
	protected XenatCardManagerModel getOrCreateComponentModel() {
		return (XenatCardManagerModel) super.getOrCreateComponentModel();
	}

	@Override
	protected XenatCardManagerModel getComponentModel() {
		return (XenatCardManagerModel) super.getComponentModel();
	}

	/**
	 * This model holds the state information.
	 */
	public static final class XenatCardManagerModel extends CardManagerModel {

		/**
		 * Map path to component.
		 */
		private Map<XenatAppCardPath, WComponent> pathToComponent;
	}

}
