package com.mcakir.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	
	public static FileInfo getFileInfo(Context context, Uri uri){
		ContentResolver contentResolver = context.getContentResolver();
		Cursor returnCursor = contentResolver.query(uri, null, null, null, null);

	    int displayNameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
	    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
	    int idIndex = returnCursor.getColumnIndex("document_id"); 
	    returnCursor.moveToFirst();
	    
	    String documentID = returnCursor.getString(idIndex);
	    String displayName = returnCursor.getString(displayNameIndex);

		FileInfo fileInfo = new FileInfo();
	    fileInfo.setDisplayName(displayName);
	    fileInfo.setBaseName(getName(displayName));
	    fileInfo.setExtension(getExtension(displayName));
	    fileInfo.setSize(returnCursor.getLong(sizeIndex));
	    fileInfo.setType(contentResolver.getType(uri));
	    fileInfo.setId(Long.parseLong(documentID.substring(documentID.indexOf(":") + 1)));

	    return fileInfo;
	}
	
	public static String getExtension(String fileName){
		String extension = null;
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
		    extension = fileName.substring(i);
		}
		return extension;
	}
	
	public static String getName(String fileName){
		String name = null;
		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
			name = fileName.substring(0, i);
		}
		return name;
	}
	
	public static File copyStreamToFile(InputStream in, FileInfo fileInfo) throws IOException{
		if(fileInfo == null){
			return null;
		}
		return copyStreamToFile(in, fileInfo.getBaseName(), fileInfo.getExtension());
	}
	
	public static File copyStreamToFile (InputStream in, String prefix, String suffix) throws IOException {
        final File tempFile = File.createTempFile(prefix, suffix, AppUtil.getInstance().getApplicationContext().getCacheDir());
        tempFile.deleteOnExit();
        try {
        	FileOutputStream out = new FileOutputStream(tempFile);
        	//IOUtils.copy(in, out);
        	copyLarge(in, out);
		} catch (FileNotFoundException e) {
			return null;
		}
        return tempFile;
    }
	
	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4096];
		long count = 0L;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
	
	public static Bitmap getThumbnailOfVideo(Context context, long id){
		ContentResolver crThumb = context.getContentResolver();
		BitmapFactory.Options options=new BitmapFactory.Options();
		options.inSampleSize = 1;
		return MediaStore.Video.Thumbnails.getThumbnail(crThumb, id, MediaStore.Video.Thumbnails.MICRO_KIND, options);
	}

}
