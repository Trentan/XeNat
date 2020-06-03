//package com.xenat.app.rest.servlet;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class XenatRestProfileFilter implements Filter {
//	private static final Log LOGGER = LogFactory.getLog(XenatRestProfileFilter.class);
//	/**
//	 * The element name for the {@link InvocationContext} TransactionId. This was copied from InvocationContext as it is private.
//	 */
//	private static final String TRANSACTION_ID = "TransactionId";
//
//	@Override
//	public void init(final FilterConfig filterConfig) throws ServletException {
//		// Do nothing
//	}
//
//	@Override
//	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
//		LOGGER.debug("Extracting app context from request header and start profile point.");
//
//		//FIXME add app context logic to extract user from header etc
////		HttpServletRequest request = (HttpServletRequest) servletRequest;
////
////		// Setup context and profiling
////		AppContext appCtx = AppContextManager.getCurrent(true);
////		try {
////			SessionContext sessionCtx = appCtx.getSessionContext();
////			ProfiledTransaction profiledTrans = ProfiledTransaction.getCurrent();
////
////			// User id
////			String userId = request.getHeader(HeaderConstants.HEADER_USERID.getHeaderValue());
////			if (userId != null) {
////				sessionCtx.setUsername(userId, true);
////				profiledTrans.setUserId(userId);
////			}
////			// Session id
////			String sessionId = request.getHeader(HeaderConstants.HEADER_SESSIONID.getHeaderValue());
////			if (sessionId != null) {
////				sessionCtx.setSessionId(sessionId);
////				profiledTrans.setSessionId(sessionId);
////			}
////			// Transaction ID
////			String tranId = request.getHeader(HeaderConstants.HEADER_TRANSACTIONID.getHeaderValue());
////			if (tranId != null) {
////				sessionCtx.setTransactionId(tranId);
////				// Set invocation context (this should be a public API but we need to access the backing map instead)
////				InvocationContext invCtx = appCtx.getInvocationContext();
////				invCtx.getBacking().put(TRANSACTION_ID, tranId);
////			}
////
////			// Start profile
////			ProfilePoint profile = new ProfilePoint(ProfilePoint.PC_SERVICE, "restServiceEntry");
////			profile.setAttribute("uri", request.getPathInfo());
////			profile.start();
////			try {
////				chain.doFilter(servletRequest, servletResponse);
////			} finally {
////				profile.stop();
////			}
////		} finally {
////			// Clear context and ProfiledTransaction
////			AppContextManager.removeCurrent();
////			ProfiledTransaction.forceNewTransaction();
////		}
//	}
//
//	@Override
//	public void destroy() {
//		// Do nothing
//	}
//}
//
