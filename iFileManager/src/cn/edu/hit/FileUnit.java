package cn.edu.hit;

import java.io.File;
import java.util.Comparator;
import android.graphics.Bitmap;

public class FileUnit implements Comparator<Object>{

	private File file; // 文件
	private Bitmap icon;// 文件图标
	private String name;// 文件名
	private String path;// 文件路径
	private String size;// 文件大小
	private String type;// 文件类型

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileSize() {
		return size;
	}

	public void setFileSize(String fileSize) {
		this.size = fileSize;
	}

	public String getFileStyle() {
		return type;
	}

	public void setFileStyle(String style) {
		this.type = style;
	}

	@Override
	public int compare(Object object1, Object object2) {
		FileUnit file1 = (FileUnit)object1;
		FileUnit file2 = (FileUnit)object2;
		return file1.getName().compareToIgnoreCase(file2.getName());
	}
}
