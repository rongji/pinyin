package org.rongji.dfish.misc.pinyin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

class Replacer {
	private KeyValuePair root=new KeyValuePair((char)0);
	public String replace(String str, String separator, int pinyinFormat) {
		StringBuilder result=new StringBuilder();
		char[] content=str.toCharArray();
		for(int i=content.length-1;i>=0;i--){
			FindResult r=root.match(content,-1,i);
			if(r.state==FindResult.STATE_MATCH){
				//仅在汉字后面加分隔符
				if(separator!=null&&separator.equals("")){
					result.append(separator);
				}
				char[] toappend= null;
				//增加分隔符号  pinyinFormat
				switch(pinyinFormat){
					case PinyinHelper.WITHOUT_TONE:
						toappend=r.replaceTo.replace("5","").replace("4","").replace("3","")
								.replace("2","").replace("1","").toCharArray(); 
						break;
					case PinyinHelper.WITH_TONE_MARK:
						toappend=PinyinHelper.getVowelReplacer().replace(r.replaceTo, "", PinyinHelper.WITH_TONE_NUMBER).toCharArray();
						break;
					default:toappend=r.replaceTo.toCharArray();
				}
				for(int k=toappend.length-1;k>=0;k--){
					result.append(toappend[k]);
				}
				i-=(r.end-r.begin-1);
			}else{
				result.append(content[i]);
			}
		}
		return result.reverse().toString();
	}

	




	void loadLib(String fileName) {
		try{
		InputStream is=Replacer.class.getResourceAsStream(fileName);
		BufferedReader bis=new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String line="";
		while ((line=bis.readLine())!=null){
			if(line==null||line.indexOf('=')<0){
				continue;
			}
			String[] pair=line.split("=");
			root.setKeyValue(pair[0], pair[1]);
		}
		
		if(bis!=null){
			bis.close();
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
