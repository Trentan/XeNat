package com.xenat.app.model.impl.util;
/**
 * Message class to handle rpc response.
 *
 * @author Trentan Healey
 * @since POC1
 */

public class MessageRPC {
		public String _msg_typ;
		public String _msg_cod;
		public String _msg_fld;
		public String _msg_txt;

		public void set_msg_typ(String _msg_typ) {
			this._msg_typ = _msg_typ;
		}

		public String get_msg_typ() {
			return this._msg_typ;
		}

		public void set_msg_cod(String _msg_cod) {
			this._msg_cod = _msg_cod;
		}

		public String get_msg_cod() {
			return this._msg_cod;
		}

		public void set_msg_fld(String _msg_fld) {
			this._msg_fld = _msg_fld;
		}

		public String get_msg_fld() {
			return this._msg_fld;
		}

		public void set_msg_txt(String _msg_txt) {
			this._msg_txt = _msg_txt;
		}

		public String get_msg_txt() {
			return this._msg_txt;
		}
}
