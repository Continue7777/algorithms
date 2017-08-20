package algorithms;

import java.util.Iterator;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import datastructure.TrieTree.TrieNode;


public class Test implements Iterable{

	
	public static void main(String[] args) {
		I a = new I(4);
		I b = a;
		b.setNum(5);
		System.out.println(a.getNum());

	}
	
	
	static class I{
		private int num;

		public I(int num) {
			num = num;
		}
		
		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
		
	}


	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
