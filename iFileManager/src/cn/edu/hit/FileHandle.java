package cn.edu.hit;

import java.io.File;

public class FileHandle {
	/* 判断文件MimeType的方法 */
	public static String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		/* 取得扩展名 */
		String extension = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		if (extension.equals("m4a") || extension.equals("mp3") 
				|| extension.equals("mid") || extension.equals("xmf") 
				|| extension.equals("ogg") || extension.equals("wav")) {
			type = "audio";
		} else if (extension.equals("3gp") || extension.equals("mp4")
				|| extension.equals("rmvb")|| extension.equals("avi")) {
			type = "video";
		} else if (extension.equals("jpg") || extension.equals("gif") 
				|| extension.equals("png") || extension.equals("jpeg") 
				|| extension.equals("bmp")) {
			type = "image";
		} else if (extension.equals("doc")) {
			type = "word";
		} else if (extension.equals("xls")) {
			type = "excel";
		} else if (extension.equals("ppt")) {
			type = "powerpoint";
		} else if (extension.equals("pdf")) {
			type = "pdf";
		} else if (extension.equals("txt")) {
			type = "txt";
		}else if (extension.equals("apk")) {
			type = "apk";
		}else if (extension.equals("htm")) {
			type = "html";
		}

		return type;
	}

	/* 文件大小描述 */
	public static String getFileSize(File f) {
		int sub_index = 0;
		String show = "";
		if (f.isFile()) {
			long length = f.length();
			if (length >= 1073741824) {
				sub_index = (String.valueOf((float) length / 1073741824))
						.indexOf(".");
				show = ((float) length / 1073741824 + "000").substring(0,
						sub_index + 3)
						+ "GB";
			} else if (length >= 1048576) {
				sub_index = (String.valueOf((float) length / 1048576))
						.indexOf(".");
				show = ((float) length / 1048576 + "000").substring(0,
						sub_index + 3)
						+ "MB";
			} else if (length >= 1024) {
				sub_index = (String.valueOf((float) length / 1024))
						.indexOf(".");
				show = ((float) length / 1024 + "000").substring(0,
						sub_index + 3)
						+ "KB";
			} else if (length < 1024) {
				show = String.valueOf(length) + "B";
			}
		}
		return show;
	}
}
