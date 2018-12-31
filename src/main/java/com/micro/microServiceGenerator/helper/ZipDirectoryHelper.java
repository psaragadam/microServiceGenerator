package com.micro.microServiceGenerator.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

public class ZipDirectoryHelper {

	public static void generateZip(String path, String location) throws IOException {
		File directoryToZip = new File(path);

		List<File> fileList = new ArrayList<File>();
		System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
		getAllFiles(directoryToZip, fileList);
		System.out.println("---Creating zip file");
		writeZipFile(directoryToZip, fileList, location);
		System.out.println("---Done");
	}

	public static void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					getAllFiles(file, fileList);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeZipFile(File directoryToZip, List<File> fileList, String location) {

		try {
			FileOutputStream fos = new FileOutputStream(location +directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) {
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
			IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}
	
	
	public static void deleteFile(String path) {
		File zipFile = new File(path);
		if(zipFile.exists()) {
			System.out.println("file exists");
			System.out.println(zipFile.delete());
		}else {
			System.out.println("zip file doesn't exists at "+ path);
		}
		
	}
	public static void deleteDirectory(String path) {
		File dir = new File(path);
		
		if(dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}else {
			try {
				System.out.println("Deleting "+ path);
				FileUtils.deleteDirectory(dir);
				System.out.println("Deleted "+ path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
						
			//dir.delete();
			/*File[] listFiles = dir.listFiles();
			for(File file : listFiles){
				System.out.println("Deleting "+file.getAbsolutePath());
				System.out.println("File deleting  status:"+ file.delete());
			}*/
		}
		
	}

}