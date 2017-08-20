package datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import datastructure.TrieTree.TrieNode;


public class TrieTree implements Iterable{     
	private  TrieNode root;
    
	public static void main(String[] args) {
		TrieTree trieTree = new TrieTree();
		trieTree.insert("北京动物园", 2);
		trieTree.insert("北京天安门", 3);
		trieTree.insert("北京", 1);
		String word = "北京";
		TrieNode subTree = trieTree.getSubTreeByWord(word);
		Iterator<TrieNode> iterator = trieTree.iterator(subTree);
		while(iterator.hasNext()) {
			TrieNode node = iterator.next();
			System.out.println(node.value + " " + node.count);
		}
		//trieTree.showTrieTree();
	}
	
	public TrieNode getRoot() {
		return root;
	}
	
	public TrieTree() {
		root = new TrieNode("root",0);
	}
	
	public class TrieNode{
		private String value;
		private ArrayList<TrieNode> son;
		private int count; //当前路径上统计数
		public TrieNode() {
			// TODO Auto-generated constructor stub
			this.value = "null";
			this.count = 0;
			this.son = new ArrayList<TrieNode>();
		}
		
		public TrieNode(String value,int count) {
			// TODO Auto-generated constructor stub
			this.value = value;
			this.count = count;
			this.son = new ArrayList<TrieNode>();
		}
		
		public String getValue() {
			return value;
		}
		
		public int getCount() {
			return count;
		}
	}
	
	
	
	
	//根据输入获取子树
	public TrieNode getSubTreeByWord(String str) {
		return _getSubTreeByWord(root,str);
	}
	private TrieNode _getSubTreeByWord(TrieNode root,String str) {
		int sonNum = root.son == null? 0 :root.son.size();
		
		if(root.value.equals(str)) {
			return root;
		}
		
		for(int i=0;i<sonNum;i++) {
			TrieNode node = _getSubTreeByWord(root.son.get(i),str);
			if(node != null) {
				return node;
			}
		}
		return null;
	}
	
	//插入时，把count放在最后一个节点上
	public void insert(String str,int count) {
		_insertNode(root, str, count ,1);
	}
	private void _insertNode(TrieNode root,String str,int count ,int index) {
		int sonNum = root.son.size();
		int findFlag = 0;
		
		
		for(int i=0;i<sonNum;i++) {
			if(root.son.get(i).value.equals(str.substring(0, index))) {
				findFlag = 1;
				if(str.length() == index) {
					root.son.get(i).count = count;
					return;
				}else {
					_insertNode(root.son.get(i), str, count ,index+1);
				}
				break;
			}
		}
		//遍历之后没有找到就创建一个
		if(findFlag == 0) {
		//	System.out.println(str.substring(0, index));
			String newValue = str.substring(0, index);
			int newCount = index != str.length() ? 0 : count;
			TrieNode sonNode = new TrieNode(newValue,newCount);
			root.son.add(sonNode);
			if(str.length() != index) {
				_insertNode(sonNode, str, count ,index+1);
			}
		}
	}
	
	//循环遍历输出字典树内容
	public void showTrieTree() {
		_showTrieTree(root);
	}
	private void _showTrieTree(TrieNode root) {
		System.out.println(root.value + root.count);
		int sonNum = root.son.size();
		for(int i=0;i<sonNum;i++) {
			_showTrieTree(root.son.get(i));
		}
	}

	
	@Override
	public Iterator<TrieNode> iterator() {
		// TODO Auto-generated method stub
		return new TrieTreeIterator();
	}
	
	
	public Iterator<TrieNode> iterator(TrieNode itrRoot) {
		// TODO Auto-generated method stub
		return new TrieTreeIterator(itrRoot);
	}
	
	private class TrieTreeIterator implements Iterator<TrieNode>{
		private TrieNode next;
		private  Queue<TrieNode> queue;
		
		public TrieTreeIterator() {
			// TODO Auto-generated constructor stub
			next = root;
			queue = new LinkedList<TrieNode>();
			if(next == null) {
				return;
			}
		}
		
		public TrieTreeIterator(TrieNode itrRoot) {
			// TODO Auto-generated constructor stub
			next = itrRoot;
			queue = new LinkedList<TrieNode>();
			if(next == null) {
				return;
			}
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			int sonNum = next.son.size();
			for(int i=0;i<sonNum;i++) {
				queue.add(next.son.get(i));
			}
			if(queue.isEmpty()) {
				return false;
			}else {
				return true;
			}

		}

		@Override
		public TrieNode next() {
			// TODO Auto-generated method stub
			
			next = queue.remove();
			return next;
		}
		
	}
	
}  