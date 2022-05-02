package com.dunkware.learn.client.fileupload;

import com.dunkware.common.util.helpers.DHttpHelper;

public class FileUploadClient {

	public static void main(String[] args) {
		String bonehead =   "Swede";
		byte[] bytes = bonehead.getBytes();
		try {
			String response = DHttpHelper.multipartRequest("http://localhost:6969/upload", "test=me", bytes, "file");
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
