package com.foodplanner.common;

import org.apache.commons.codec.digest.DigestUtils;

public class HashAlgorithms
{

	public static class HashCode
	{
		public static <T> String getMd5(T obj)
		{
			String str = obj.toString().toLowerCase();
			String md5Hex = DigestUtils
				      .md5Hex(str).toLowerCase();
			
			return md5Hex;
		}
	}
}
