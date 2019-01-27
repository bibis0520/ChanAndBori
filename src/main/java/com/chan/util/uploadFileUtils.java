package com.chan.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 1. 자동적인 폴더의 생성
 * 2. 파일 저장은 UUID를 이용해서 처리 (중복방지)
 * 3. 썸네일 이미지를 생성하는 기능 (서버에서 최소한의 데이터를 다루기 위해서)
 * @author chan
 */
public class uploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(uploadFileUtils.class);

	/**
	 * 파일을 업로드하기 위해 필요한 3가지의 데이터를 받는 함수
	 * @param uploadPath
	 * @param originalName
	 * @param fileData
	 * @return
	 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		return null;
	}

	public static String calcPath(String uploadPath) throws Exception {

		// date보다 가벼움.
		Calendar cal = Calendar.getInstance();

		// File.separator는 파일 경로가 정해질때 경로를 구분하는 구분자. 윈도우와 리눅스가 경로를 구분하는 방법이 다른데 이부분을 해결해준다.
		String yearPath = File.separator + cal.get(Calendar.YEAR);

														// DecimalFormat : 형식화 클래스로 십진수로 ("00")일 경우 두자리 수까지 표현한다.
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		logger.info("datePath : " + datePath);

		return datePath;
	}

	public static void makeDir(String uploadPath, String... paths) {

		if ( new File(uploadPath + paths[paths.length - 1]).exists() ) {
			return;
		}

		for ( String path : paths ) {
			File dirPath = new File(uploadPath + path);

			if ( !dirPath.exists() ) {
				dirPath.mkdir();
			}
		}
	}
}
