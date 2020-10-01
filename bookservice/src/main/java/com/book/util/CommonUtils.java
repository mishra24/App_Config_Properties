package com.book.util;

public class CommonUtils {

	private CommonUtils() {

	}

	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";

	public static <T> APIResponse<T> getSuccessResponse(T response) {
		APIResponse<T> apiResponse = new APIResponse<T>();
		apiResponse.setResponse(response);
		apiResponse.setStatus(SUCCESS);
		return apiResponse;
	}

	public static <T> APIResponse<T> getFailureResponse(T response, String errorCode, String errorMessage) {
		APIResponse<T> apiResponse = new APIResponse<T>();
		apiResponse.setResponse(response);
		apiResponse.setStatus(FAILED);
		apiResponse.setErrorCode(errorCode);
		apiResponse.setErrorMessage(errorMessage);
		return apiResponse;
	}
}
