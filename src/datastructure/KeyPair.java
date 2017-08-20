package datastructure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class KeyPair<K, V> implements Serializable {
	private K key;
	private V value;

	public static void main(String[] args) {
		KeyPair<String, Integer> keyPair = new KeyPair<String, Integer>("test", 1);
		KeyPair<String, Integer> keyPair2 = keyPair.clone();
		System.out.println(keyPair2.getKey());
	}
	
	public KeyPair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	// Discription:[深度复制方法,需要对象及对象所有的对象属性都实现序列化]
	public KeyPair clone() {
		KeyPair outer = null;
	    try { // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(this);
	        // 将流序列化成对象
	        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	        ObjectInputStream ois = new ObjectInputStream(bais);
	        outer = (KeyPair) ois.readObject();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return outer;
	  }
}


