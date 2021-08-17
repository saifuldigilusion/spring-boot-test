package hk.com.nexify.cmn.response;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import hk.com.nexify.cmn.mapper.MapperUtils;

@JsonInclude(Include.NON_NULL)
public final class ResponseModel<T> {
	private String code;
	private String message;
	private T result;

	// no error, no result return
	public ResponseModel() {
		this.code = StringUtils.EMPTY;
		this.message = StringUtils.EMPTY;
		this.result = MapperUtils.getEmptyJsonObject();
	}

	// no error, empty code and message, return res
	public ResponseModel(T result) {
		this.code = StringUtils.EMPTY;
		this.message = StringUtils.EMPTY;
		this.result = result;
	}

	// return code, message
	// error
	public ResponseModel(String code, String message) {
		this.code = code;
		this.message = message;
		this.result = MapperUtils.getEmptyJsonObject();
	}

	// return code, message and result
	public ResponseModel(String code, String message, T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}

	// getter and setter----------
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
