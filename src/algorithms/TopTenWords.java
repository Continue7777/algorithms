package algorithms;

import java.awt.RenderingHints.Key;
import java.beans.Transient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import datastructure.Heap;
import datastructure.KeyPair;
import datastructure.TrieTree;
import datastructure.TrieTree.TrieNode;

public class TopTenWords {
	
	private static String fileList = "searchResult/baidu0818.log";
	private TrieTree trieTree; //字典树为了确定关联的所有子集
	private Map<String, Integer> hashMap; //统计词频用的东西
	private Heap<KeyPair<String,Integer>> mHeap;
	public static void main(String[] args) {
		TopTenWords topTenWords = new TopTenWords();
		//System.out.println(topTenWords.trieTree.getRoot());
		topTenWords.getTopKRelatedWords("北京",3);
//		System.out.println(subRoot);
//		topTenWords.statisticToHashMap();
//		topTenWords.showStatisticResult();
//		topTenWords.buildTrieTree();
	}
	
	public TopTenWords() {
		//文章词频统计到hashMap
		statisticToHashMap();
		//文章统计到字典树
		buildTrieTree();
	}
	
	//根据文件列表讲文件写到字典树中
	private void buildTrieTree() {
		trieTree = new TrieTree();
		Iterator itr = hashMap.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<String, Integer> entry = (Entry<String, Integer>) itr.next();
			String str = entry.getKey();
			int count = entry.getValue();
			trieTree.insert(str, count);
		}
		//trieTree.showTrieTree();
	}
	
	//根据文件列表将文件写到ＨashＭap中，进行词频统计
	private void statisticToHashMap(){
		hashMap = new HashMap<String,Integer>();
		 try
	        {
	            String encoding = "utf-8";
	            File file = new File(fileList);
	            if (file.isFile() && file.exists())
	            { // 判断文件是否存在
	                InputStreamReader read = new InputStreamReader(
	                        new FileInputStream(file), encoding);// 考虑到编码格式
	                BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;

	                while ((lineTxt = bufferedReader.readLine()) != null)
	                {
	                	if(hashMap.containsKey(lineTxt)) {
	                		hashMap.put(lineTxt, hashMap.get(lineTxt)+1);
	                	}else {
	                		hashMap.put(lineTxt,1);
	                	}
	                }
	                bufferedReader.close();
	                read.close();
	            }
	            else
	            {
	                System.out.println("找不到指定的文件");
	            }
	        }
	        catch (Exception e)
	        {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
		//hashMap.put(key, value)
	}

	//查看hashmap情况*
	private void showStatisticResult() {
		Iterator iter = hashMap.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry<String, Integer> entry = (Entry<String, Integer>) iter.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println(key + value);
		}
		
	}
	
	//智能热词联想：根据输入的word来做智能搜索Top10的热词,词语来自搜索词条不涉及分词*
	public void getTopKRelatedWords(String word,int k) {
		//根据字典树和输入得到对应需要排序的子树
		TrieNode subTree = trieTree.getSubTreeByWord(word);
		
		System.out.println(subTree);
		//利用topk算法根据子树内容和最小堆结构获取最热
		//******************************这个地方还需要修改，要能根据输入点迭代*********************************
		Iterator<TrieNode> iterator = trieTree.iterator(subTree);
		
		//建立heap来做键值对比较
		Comparator<KeyPair<String, Integer>> comp = new Comparator<KeyPair<String, Integer>>() {  
			@Override
			public int compare(KeyPair<String, Integer> o1, KeyPair<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}  
        };  
        
        //初始化，创建最大堆  
        KeyPair<String, Integer>[] temp = new KeyPair[k];
        for(int i=0;i<k;i++) {
        	temp[i] = new KeyPair("null",0);
        }
        
        mHeap = new Heap<KeyPair<String, Integer>>(temp, comp);  
		KeyPair<String, Integer> tempKeyPair = new KeyPair<String, Integer>("", 0);
        while(iterator.hasNext()){
			TrieNode node = iterator.next();	
			//获取整个字符串和频次
			tempKeyPair.setKey(node.getValue());
			tempKeyPair.setValue(node.getCount());			
//			System.out.println("当前值：" + tempKeyPair.getKey() + tempKeyPair.getValue() + "　最小值：" + mHeap.root().getKey() + ""+ mHeap.root().getValue());	
//			System.out.println("判断条件　" + tempKeyPair.getValue() + ">" + mHeap.root().getValue());
			if(tempKeyPair.getValue() > mHeap.root().getValue()) {
//			System.out.println("插入" + tempKeyPair.getKey() + tempKeyPair.getValue());
				//这个地方要传入拷贝的值，而不能直接传入索引
				mHeap.setRoot(tempKeyPair.clone());
//				System.out.println("插入后" + mHeap.root().getKey() + mHeap.root().getValue());
			}
		}
        
        mHeap.sort();
        for(int i=0;i<k;i++) {
        	System.out.println(mHeap.getByIndex(i).getKey() + mHeap.getByIndex(i).getValue());
        }
  //      System.out.println(temp[0].getKey());
        
	}
	
	//获取热词*
	public void getTopKWords() {
		
	}
	
}
