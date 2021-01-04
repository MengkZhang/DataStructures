package com.zhang.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);

        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);

        //输出一个菜单
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):展示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列去数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("取出的数据是=" + queue);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int queue = arrayQueue.headQueue();
                        System.out.println("取出的头数据是=" + queue);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

//使用数组模拟一个队列
class CircleArrayQueue {
    //队列的最大容量
    private int maxSize;
    //队列头
    private int front = 0;
    //指向最后一个元素的后一个位置
    private int rear = 0;
    //数组存储数据 模拟队列
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    /**
     * 判断队列是否已满
     *
     * @return 已满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return 为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列
     *
     * @param n 数组
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满 不能加数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移 取模运算
        rear = (rear + 1) % maxSize;

    }

    /**
     * 获取队列的数据 出队列
     *
     * @return 获取的数据
     */
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空 不能取数据");
            throw new RuntimeException("队列为空 不能取数据");
        }
        //front指向队列的第一个元素
        //先把front对应的值保存到一个临时变量
        //将front后移 考虑取模
        //将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列的数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历 遍历多少个元素
        for (int i = front; i < front + getSize(); i++) {
            System.out.println("arr[" + i % maxSize + "] = " + arr[i % maxSize]);
        }
    }

    private int getSize() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列头数据
     */
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空 不能取数据");
        }
        return arr[front];
    }
}