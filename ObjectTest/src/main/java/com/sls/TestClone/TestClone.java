package com.sls.TestClone;


public class TestClone implements Cloneable {

    private int a;
    private String b;
    private Address address;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public TestClone(int a, String b, Address address) {
        this.a = a;
        this.b = b;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "TestClone{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", address=" + address +
                '}';
    }
}

class Address {
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Address(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addr='" + addr + '\'' +
                '}';
    }
}

class TestFactory{

    public  static  TestClone getTestClone(){
        Address address = new Address("天湖");
        TestClone clone = new TestClone(1,"2",address);
        return  clone;
    }
}
class Test{
    public static void main(String[] args) throws CloneNotSupportedException {
        TestClone testClone = TestFactory.getTestClone();
        System.out.println("before clone"+testClone);
        TestClone clone = (TestClone) testClone.clone();
        clone.getAddress().setAddr("北京");
        System.out.println("after clone"+testClone);
        System.out.println(clone);
    }
}