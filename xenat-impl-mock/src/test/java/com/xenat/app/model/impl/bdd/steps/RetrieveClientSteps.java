package com.xenat.app.model.impl.bdd.steps;

/**
 * Retrieve client steps.
 */
public class RetrieveClientSteps {
	// TODO Add cucumber tests

//	private final ClientServices backing = new ListServicesMockImpl();
//
//	private ClientDetail client;
//	private Exception error;
//
//	@Given("A client retrieve service is available")
//	public void wantToRetrieveClient() {
//		this.error = null;
//		this.client = null;
//	}
//
//	@When("User retrieves client {string}")
//	public void retrieveClient(final String id) {
//		client = null;
//		error = null;
//		try {
//			client = backing.retrieveClient(id);
//		} catch (Exception e) {
//			error = e;
//		}
//	}
//
//	@When("User retrieves a client that does not exist")
//	public void retrieveClientNotExists() {
//		retrieveClient("notfound");
//	}
//
//	@When("User retrieves client that causes a service exception")
//	public void retrieveClientCausesError() {
//		retrieveClient("error");
//	}
//
//	@Then("User gets {string} client")
//	public void shouldHaveApplication(final String id) {
//		Assert.assertNotNull("Client should have been retrieved", client);
//		Assert.assertEquals("Incorrect client id retrieved", id, client.getClientId());
//	}
//
//	@Then("User gets client not found exception for retrieve")
//	public void shouldHaveClientNotFoundException() {
//		Assert.assertTrue("Service should have caused client not found exception", error instanceof ClientNotFoundException);
//	}
//
//	@Then("User gets service exception for retrieve")
//	public void shouldHaveServiceException() {
//		Assert.assertTrue("Service should have caused service exception", error instanceof ServiceException);
//	}

}
