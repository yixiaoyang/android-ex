package cn.edu.hit;

public class SystemFilesList {
	public final static String[] filelists={"/sdcard/.android_secure"}; 
	public static boolean ifSysFile(String fn){
		for(int i = 0; i < filelists.length; i++){
			if(filelists[i].equals(fn)){
				return true;
			}
		}
		return false;
	}
}
