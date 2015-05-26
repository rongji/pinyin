package org.rongji.dfish.misc.pinyin;

import static org.junit.Assert.*;

import org.junit.Test;

public class PinyinHelperTest {
	
	private String str = "厦门市少年有不少参加了马拉松比赛";
	
	@Test
	public void testWithToneMark() {
		String s=PinyinHelper.convertToPinyinString(str, "", PinyinHelper.WITH_TONE_MARK);
		assertEquals("xiàménshìshàoniányoǔbùshǎocānjiālemǎlāsōngbǐsài", s);
	}
	
	@Test
	public void testWithToneNumber() {
		String s=PinyinHelper.convertToPinyinString(str, "", PinyinHelper.WITH_TONE_NUMBER);
		assertEquals("xia4men2shi4shao4nian2you3bu4shao3can1jia1le5ma3la1song1bi3sai4", s);
	}
	
	@Test
	public void testWithoutTone() {
		String s=PinyinHelper.convertToPinyinString(str, "", PinyinHelper.WITHOUT_TONE);
		assertEquals("xiamenshishaonianyoubushaocanjialemalasongbisai", s);
	}

}
