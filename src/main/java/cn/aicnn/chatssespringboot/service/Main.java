package cn.aicnn.chatssespringboot.service;

import java.util.Observable;


public class Main  extends Observable {

    public static void main(String[] args) throws Exception {

        Main observer = new Main();
        //添加观察者
        observer.addObserver((o,arg)->{
            System.out.println("发生变化");
        });
        observer.addObserver((o,arg)->{
            System.out.println("手动被观察者通知，准备改变");
        });
        observer.setChanged(); //数据变化
        observer.notifyObservers(); //通知

    }

}
