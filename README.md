# 高并发编程

@Author:zxw

@School:吉首大学

# 1.概念

1. 同步和异步
   1. 同步：调用者必须等到方法调用返回后，才能继续后继行为
   2. 异步：方法调用立即返回
2. 并发和并行
3. 临界区
   1. 公共资源或者共享数据，每一次只能有一个线程使用它
4. 阻塞和非阻塞
5. 死锁、饥饿和活锁
   1. 死锁
   2. 饥饿：无法获得所需要的资源，导致一直无法执行
   3. 活锁：主动释放资源给他人用。两个线程之间资源来回跳动

## 1.1 并发级别

1. 阻塞：在其他线程释放资源前，当前线程无法继续执行
2. 无饥饿:线程之间的优先级，资源分配不公平
3. 无锁:无穷循环中，线程会不断尝试修改共享变量，如果没有冲突，修改成功。否则继续修改CAS算法
4. 无等待:要求所有线程有限步内必须完成，RCU（Read Copy Update）对数据读不加以控制，但在写数据的时候，先取得原始数据的副本，接着修改副本数据，修改完成后，在合适的实际写回数据
5. 无障碍:乐观锁

## 1.2 两个并行定律

1. Amdahl定律
2. Gustafson

# 2. Java并行程序

1. 原子性：一个操作不可中断，即使多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程干扰

2. 可见性：当一个线程修改了某一个共享变量的值时，其他线程是否能够立即指导修改

3. 有序性：程序执行时，指令重排

4. ```java
   public enum State{
   	NEW, // 线程新建，等到start方法调用时线程才开始执行
       RUNNABLE, // 所需资源准备完毕
       BLOCKED, // synchronized同步块，进入blocked阻塞状态
       WAITING, // 等待请求的锁，等待时间无限制,等到一些特殊时间，比如wait(),notify(),join()
       TIMED_WAITING, // 等待时间有限制
       TERMINATED // 线程结束
   }
   ```

## 2.1 线程基本操作

1. 新建：不要用run()方法开启新线程，它只会在当前线程中串行执行run()方法中的代码,默认的Tread.run()方法直接调用内部的Runnable接口

2. 终止:stop(),直接终止线程，并立即释放这个线程所持有的锁

3. 中断:

   ```java
   interrupt() // 中断线程
   isInterrupted() // 判断线程是否被中断
   Thread.interrupted() // 判断是否被中断，并清除当前状态
   ```

4. 等待和通知:

   ```java
   public final void wait() throws InterruptedException // 进入对象的等待队列
   public final native void notify() // 随机唤醒
   Object.wait() // 需要包含在synchronized语句中，在wait()方法执行前首先获得目标对象的一个监视器，在wait()执行后，会释放这个监视器
   Object.notify() // 首先获得监视器
   ```

5. 挂起和继续执行

   ```java
   suspend() // 不释放锁资源,从线程状态上看还是Runnable
   resume() // 唤起线程
   ```

6. 等待线程结束和谦让

   ```java
   public void final join() // 无限等待，一只阻塞当前线程
   public final synchronized void join(long mills) // 有限时间等待
   public static native void yield() // 当前线程让出CPU。让出后还会进行CPU资源争夺
   ```

7. volatile与java内存模型

8. 线程组

   ```java
   ThreadGroup tg = new ThreadGroup("print");
   Thread t1 = new Thread(tg,"","");
   ```

9. 守护线程

   ```java
   Thread t1 = new Thread();
   t.setDeamon(true); // 必须在start()之前设置，如果用户线程全部结束，守护线程要守护的对象已经不存在了，那么整个应用程序就应该结束。
   ```

10. 线程优先级

    ```java
    public final static int MIN_PRIORITY = 1;
    public final static int NORM_PRIORITY = 5;
    public final static int MAX_PRIORITY = 10;
    Thread t1 = new Thread();
    t1.setPriority(Thread.MIN_PRIORITY); // 高优先级在大部分情况下，都会首先完成任务
    ```

11. synchronized

    1. 对于普通同步方法，锁是当前实例对象。
    2. 对于静态同步方法，锁是当前类的Class对象。
    3. 对于同步方法块，锁是Synchonized括号里配置的对象。
       当

12. 错误的额加锁

# 3. JDK并发包

## 3.1 同步控制

### 3.1.1 重入锁(ReentrantLock)

1. 中断响应

   ```
   lock.lockInterruptibly()
   
   ```

2. 锁申请等待限时

   ```java
   // 如果不指定参数，则不会等待直接返回false
   try{
   if(lock.tryLock(5, TimeUnit.SECONDS)){
       Thread.sleep(6000)；
   }
   }finally{
       lock.unlock();
   }
   
   ```

3. 公平锁

   ```java
   public ReentrantLock(boolean fair) // 公平锁,需要维护一个有序队列，实现成本比较高，性能比较低
   
   ```

4. API

   1. lock()：获得锁，如果已经被占用，则等待
   2. lockInterruptibly()：获得锁，但优先响应中断
   3. tryLock()：尝试获得锁，如果成功，则返回true，失败返回false。
   4. tryLock(long time,TimeUnit unit)：给定时间内尝试获得锁
   5. unlock()：释放锁

5. 三要素

   1. 原子状态：原子状态使用CAS操作
   2. 等待队列：所有没有请求到锁的线程，会进入等待队列进行等待
   3. 阻塞原于park和unpark()：用来挂起和恢复线程。没有得到锁的线程将会被挂起

### 3.1.2 重入锁的好搭档：Condition

1. 与重入锁相关联，通过lock接口的Condition new Condition()方法可以生成一个与当前重入锁绑定的Condition实例,用重入锁的对象来获得此对象
2. ![1567393746883](D:\code\IDEA CODE\Thread-Learn\README.assets\1567393746883.png)

### 3.1.3  信号量(Semaphore)

1. ```java
   public Semaphore(int permits) // 指定同时能申请多少个许可
   public Semaphore(int permits, boolean fair) // 第二个参数可以指定是否公平
   
   ```

2. ```java
   public void acquire() // 获取准入许可，如果无法获得，则线程等待
   public void acquireUninterruptibly() // 不响应中断
   public boolean tryAcquire() // 尝试获取许可
   public boolean tryAcquire(long timeout, TimeUnit unit)
   public void release() // 释放许可
   
   ```

3. 信号量是对锁的扩展，可以指定多个线程，同时访问某一个资源

### 3.1.4 ReadWriteLock 读写锁

1. ![1567394933710](D:\code\IDEA CODE\Thread-Learn\README.assets\1567394933710.png)

2. ```java
   ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock()
   Lock readlock = readWriteLock.readLock();
   Lock writelock = readWriteLock.writeLock();
   
   ```

### 3.1.5 倒计数器CountDownLatch

1. 控制线程等待，让某一个线程等待直到倒计数结束，再开始执行

   ```java
   public CountDownLatch(int count) // 需要count个线程完成任务后等待在CountDownLatch上的线程才能继续执行
   CountDownLatch end = new CountDownLatch(10);
   end.countDown(); // 通知CountDownLatch一个线程已经完成任务,倒计数减1
   
   ```

### 3.1.6 循环栅栏CyclicBarrier

1. 阻止线程继续执行，要求线程在栅栏外等待，这个计数器可以反复使用,当碰到await()方法每个线程都会等待，直到所有线程执行完毕。

2. ```java
   public CyclicBarrier(int parties, Runnable barrierAction)
   
   ```

### 3.1.7 线程阻塞工具类LockSupport

1. 线程阻塞工具，可以让线程内任意位置让线程阻塞，弥补了resume()方法导致线程无法执行的情况，与wait()方法相比不需要先获得某个对象的锁

### 3.1.8 Guava和RateLimiter限流



## 3.2 线程池

如果当前运行的线程少于corePoolSize，则创建新线程来执行任务（注意，执行这一步骤
需要获取全局锁）。
2）如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue。
3）如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务（注意，执
行这一步骤需要获取全局锁）。
4）如果创建新线程将使当前运行的线程超出maximumPoolSize，任务将被拒绝，并调用
RejectedExecutionHandler.rejectedExecution()方法。

ThreadPoolExecutor采取上述步骤的总体设计思路，是为了在执行execute()方法时，尽可能
地避免获取全局锁（那将会是一个严重的可伸缩瓶颈）。在ThreadPoolExecutor完成预热之后
（当前运行的线程数大于等于corePoolSize），几乎所有的execute()方法调用都是执行步骤2，而
步骤2不需要获取全局锁。

## 3.3 JDK的并发容器

## 3.4 JMH性能测试

## 3.5 锁的优化

## 3.6 ThreadLocal

## 3.7 无锁

# 4. 并行模式与算法

## 4.1 单例模式

## 4.2 不变模式

## 4.3 生产者-消费者模式

## 4.4 高性能的生产者-消费者模式-无锁实现

## 4.5 Future模式

## 4.6 并行流水线

## 4.7 并行搜索

## 4.8 并行排序

## 4.9 NIO

ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列。
·LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
·PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列。
·DelayQueue：一个使用优先级队列实现的无界阻塞队列。
·SynchronousQueue：一个不存储元素的阻塞队列。
·LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
·LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。