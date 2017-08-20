package datastructure;

public class OrderArray {

    private int[] orderArray;
    private int nElems;  //非空元素个数
    private int initSize; //数组最大容纳元素个数

    public static void main(String[] args) {
    	OrderArray orderArray = new OrderArray(10);         
        orderArray.addElem(10);
        orderArray.addElem(8);
        orderArray.addElem(9);
        orderArray.addElem(5);
        orderArray.addElem(8);
        orderArray.addElem(7);

        orderArray.show();

        orderArray.delElem(11);
        orderArray.show();

        orderArray.delElem(8);
        orderArray.delElem(9);
        orderArray.show();

        orderArray.updateElem(3,6);
        orderArray.show();

        System.out.println(orderArray.length());
	}
    
    public OrderArray(int initSize){
        this.orderArray = new int[initSize];
        this.nElems = 0;
        this.initSize = initSize;
    }
    
    public boolean addElem(int value){

        if(nElems>=initSize){ 
            return false;
        }

        int index = 0; 
        for(int i=0;i<nElems;i++){
            if (value < orderArray[i]){
                index = i;  
                break;
            }
        }
        //最后一个非空元素开始后移1位
        for(int j=nElems-1;j>=index;j--){
            orderArray[j+1] = orderArray[j];
        }
        orderArray[index] = value;
        nElems++;
        return true;
    }
    
    public  boolean delElem(int value){

        boolean flag = false;
        int index = 0;
        for(int i=0;i<nElems;i++){ 
            if(value == orderArray[i]){
                index = i;
                flag = true;
                break;
            }
        }

        if(!flag)  
            return false;

        for(int i=index;i<nElems-1;i++){  
            orderArray[i] = orderArray[i+1];
        }
        orderArray[nElems-1] = 0;  
        nElems--;
        return true;
    }
    
    public boolean updateElem(int index,int value){

        if(index>=nElems) 
            return false;

            orderArray[index] = value;
            sortArray(); //重新排序：这里采用冒泡法
            return true;
    }
    
    public void sortArray(){
        for(int i=0;i<nElems;i++){
            for(int j=0;j<nElems-i-1;j++){
                if(orderArray[j]>orderArray[j+1]){
                    int temp = orderArray[j];
                    orderArray[j] = orderArray[j+1];
                    orderArray[j+1] = temp;
                }
            }
        }
    }
    
    public void show(){
        StringBuffer sb = new StringBuffer();
        sb.append("数组元素：[");
        for(int i=0;i<nElems;i++){
            sb.append(orderArray[i]+",");
        }
        sb.deleteCharAt(sb.length()-1).append("]");
        System.out.println(sb);
    }
    
    public int length(){
        return nElems;
    }
    
    public int getMin() {
    	return orderArray[0];
    }
    
    
}