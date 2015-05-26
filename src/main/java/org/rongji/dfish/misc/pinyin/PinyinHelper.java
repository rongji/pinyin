package org.rongji.dfish.misc.pinyin;


public class PinyinHelper {
	public static final int WITH_TONE_MARK = 2;
	public static final int WITHOUT_TONE = 1;
	public static final int WITH_TONE_NUMBER = 0;
	public static String convertToPinyinString(String str, String separator, int pinyinFormat) {
		return getDictReplacer().replace(str,separator,pinyinFormat);
	}

	private static Replacer REPLACER =null;
	private static Replacer VOWEL_REPLACER =null;
	private static Replacer getDictReplacer() {
		if(REPLACER==null){
			synchronized (PinyinHelper.class) {
				if(REPLACER==null){
					REPLACER=new Replacer();
					REPLACER.loadLib("/org/rongji/dfish/misc/pinyin/main_lib.txt");//初始化词典
				}
			}
		}
		return REPLACER;
	}
	static Replacer getVowelReplacer() {
		if(VOWEL_REPLACER==null){
			synchronized (PinyinHelper.class) {
				if(VOWEL_REPLACER==null){
					VOWEL_REPLACER=new Replacer();
					VOWEL_REPLACER.loadLib("/org/rongji/dfish/misc/pinyin/tone_lib.txt");//初始化韵母表
				}
			}
		}
		return VOWEL_REPLACER;
	}
	

	public static String convertToPinyinString(String str) {
		return convertToPinyinString(str,"",WITHOUT_TONE);
	}
}
