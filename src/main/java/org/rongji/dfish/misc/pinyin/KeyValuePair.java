package org.rongji.dfish.misc.pinyin;

import java.util.HashMap;
import java.util.Map;

class KeyValuePair {
	KeyValuePair(char c){
		key=c;
	}
	public FindResult match(char[] content, int begin, int end) {
	
		char c=content[end];
		FindResult result=new FindResult(FindResult.STATE_UNMATCH,begin,end);
		if(children==null){
			return result;
		}
		KeyValuePair p=children.get(c);
		if(p==null){
			return result;
		}else{
			if(p.wordFinish){
				result.state=FindResult.STATE_MATCH;
				result.end=end;
				result.begin=end-1;
				result.replaceTo=p.replaceTo;
				//找到一个匹配项，如果没有更长的将使用这个匹配项。
				//不能直接return;
			}
			if(p.children!=null){
				if(end-1>begin){
					FindResult subMatch=p.match(content, begin, end-1);
					if(subMatch.state==FindResult.STATE_MATCH){
						result.state=FindResult.STATE_MATCH;
						result.end=end;
						result.begin=subMatch.begin;
						result.replaceTo=subMatch.replaceTo;
						return result;
					}
				}
			}
		}
		
		return result;
	}
	public void setKeyValue(String keyWord, String replaceTo) {
		setKeyValue(keyWord.toCharArray(),-1,keyWord.length()-1,replaceTo);
	}
	void setKeyValue(char[] charArray,int begin,int end, String replaceTo) {
		if(end-begin<=0)return;
		if(children==null){
			children=new HashMap<Character , KeyValuePair>();
		}
		char c=charArray[end];
		KeyValuePair sub=children.get(c);
		if(sub==null){
			sub=new KeyValuePair(c);
			children.put(c, sub);
		}
		if( end-begin ==1){
			sub.wordFinish=true;
			sub.replaceTo=replaceTo;
		}else{
			sub.setKeyValue(charArray, begin,end-1,replaceTo);
		}
	}

	char key;
	boolean wordFinish;
	String replaceTo;
	HashMap<Character , KeyValuePair> children;
	public void show() {
		StringBuilder sb=new StringBuilder();
		show(sb);
		System.out.println(sb);
	}
	void show(StringBuilder sb) {
		sb.append('{');
		boolean begin=true;
		if(wordFinish){
			if(begin){begin=false;}else{sb.append(',');}
			sb.append("\"replaceTo\":\"");
			sb.append(replaceTo);
			sb.append('\"');
		}
		if(children!=null){
			for(Map.Entry<Character , KeyValuePair> entry:children.entrySet()){
				if(begin){begin=false;}else{sb.append(',');}
				sb.append('"');
				sb.append(entry.getKey());
				sb.append('"');
				sb.append(':');
				entry.getValue().show(sb);
			}
		}
		sb.append('}');
	}
}
