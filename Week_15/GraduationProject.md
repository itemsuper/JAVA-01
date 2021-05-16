# 毕业作业  

1、(必做)分别用 100 个字以上的一段话，加上一幅图 (架构图或脑图)，总结自己
对下列技术的关键点思考和经验认识:  
1)JVM  
2)NIO  
3)并发编程  
4)Spring 和 ORM 等框架  
5)MySQL 数据库和 SQL  
6)分库分表  
7)RPC 和微服务  
8)分布式缓存  
9)分布式消息队列


# JVM 
##### JVM内存结构：JVM栈内存结构、堆内存结构  
##### JVM类加载器： 
1.启动类加载器（BootstrapClassLoader)  
2.扩展类加载器（ExtClassLoader)  
3.应用类加载器（AppClassLoader)  
##### 主要垃圾回收器及特点：
1.串行垃圾回收器(Serial)：它为单线程环境设计并且只使用一个线程进行垃圾回收，会暂停所有的用户线程，所以不适合服务器环境  
2.并行垃圾回收器(Parallel)：多个垃圾回收线程并行工作，此时用户线程是暂停的，适用于科学计算/大数据处理等弱交互场景  
3.并发垃圾回收器(CMS):用户线程和垃圾收集线程同时执行（不一定是并行，可能交替执行）不需要停顿用户线程，适用于对响应时间有要求的场景  
4.G1垃圾回收器：G1垃圾回收器将堆内存分割成不同的区域然后并发的对其进行垃圾回收。
##### 常见的垃圾回收算法：引用计数、复制、标记清除、标记整理  
##### JVM调优和参数配置： Xmx、Xms、Xmn、Meta、DirectMemory、Xss    

# NIO
1. Buffer：在NIO的世界中，所有数据都在Buffer中，从Buffer中读、写入Buffer。每中基本数据类型都有一个对应的Buffer类，其中ByteBuffer最为常用，也比较特殊——与Channel类联系最为紧密。
Buffer有三个状态变量：position、limit、capacity。Buffer可理解为比较高级的数据，这几个状态变量描述了数组中的数据装载情况，从变量名即可大约知道他们各自是什么意思。  
2. Channel：类似于旧IO的Stream，ServerSocket、Socket都有对应的Channel类，即ServerSocketChannel、SocketChannel，他们之间是双向关联的，即可以互相取得对方的句柄。与Stream不同的是，Channel是双向的。我们需要着重注意的是，SelectableChannel类，顾名思义，该类及其子类，表示“可以被选择”，说得更清楚一些，这样的Channel可以注册到某个Selector对象上，Selector对象可以在Channel关注的某些事件到来的时候，以某种方式给予通知。与著名的Observer模式在思路上惺惺相惜。事实上，我们所关心的ServerSocketChannel、SocketChannel都是SelectableChannel的子类。  
3. Selector：可理解为Channel们的调度器，多个Channel可以注册到一个Selector对象，如前所述，Selector在Channel关注的事件发生的时候给予通知。比如，ServerSocketChannel在注册到Selector的时候表示，我关心accept操作，即关心哪些客户端试图与我建链；再比如，SocketChannel就关心read操作，看看什么时候会从Socket上读到数据。Selector提供的select方法是我们最为关注的，该方法是同步的，返回此时Selector收到的所有事件，返回形式是包含多个SelectionKey的Set。  
4. SelectionKey：包裹Selector和Channel关联关系的类。从SelectionKey中可以得到如下信息：Channel、Selector、操作类型（accept、read、write）等。  

# 并发编程 
##### 线程的6个状态：New、Runnable、Blocked、Waiting、Timed Waiting、Terminated  
##### Thread和Object类中的重要方法：  
1.Thread：sleep相关、join、yield相关、currentThread、start,run相关、interrupt相关、stop,suspend,resume相关  
2.Object：wait/notify/notifyAll相关  
##### JUC:  
1.锁机制类 Locks : Lock, Condition, ReentrantLock, ReadWriteLock,LockSupport  
2.原子操作类 Atomic : AtomicInteger, AtomicLong, LongAdder  
3.线程池相关类 Executer : Future, Callable, Executor, ExecutorService  
4.信号量三组工具类 Tools : CountDownLatch, CyclicBarrier, Semaphore  
5.并发集合类 Collections : CopyOnWriteArrayList, ConcurrentMap  


# Spring 和 ORM 等框架  
##### Spring AOP（面向切面编程）/IOC（控制反转）  
##### Spring Bean核心原理：Spring Bean的生命周期  
##### Spring XML配制原理：  
1.根据Bean的字段结构，自动生成XSD；  
2.根据Bean的字段结构，配置XML文件  
##### Spring Boot核心原理：  
1.自动化配置  
2.spring-boot-starter  
##### ORM：Hibernate、Mybatis、JPA    



# MySQL 数据库和 SQL  
1.数据库设计范式    
2.结构化查询语言的六个部分：DQL、DML、TCL、DCL、DDL、CCL  
3.MySQL优化：选择合适的引擎、库表命名、宽表拆分、恰当的选择数据类型、外键和触发器  
4.MySQL的存储结构：mysql提供了四个级别的数据存储。表空间->段->区-块（页）。  
5.MySQL的索引结构：mysql提供两种索引结构哈希索引和B+树  
6.MySQL锁：按照不同的分类可划分多种锁：【行锁、间隙锁、临间锁】【排他锁和共享锁】【表锁、页锁、行锁】等
7.MySQL事务：事务传播特性及隔离级别

# 分库分表  
1.垂直拆分和水平拆分：需了解不同拆分的特点和解决的问题  
2.MySQL主从复制：主库写binlog，从库relay log  
3.MySQL读写分离  
4.MySQL高可用：①主从手动切换 ②LVS+Keepalived ③MHA ④MGR*  ⑤MySQL Cluster ⑥Orchestrator  

# RPC和微服务  
RPC的简化版原理如下：  
核心是代理机制  
  1.本地代理存根: Stub  
  2.本地序列化反序列化  
  3.网络通信  
  4.远程序列化反序列化  
  5.远程服务存根: Skeleton  
  6.调用实际业务服务  
  7.原路返回服务结果  
  8.返回给本地调用方   
  
 具体的分布式业务场景里，除了能够调用远程方法，我们还需要考虑什么？  
 1、多个相同服务如何管理？  
 2、服务的注册发现机制？   
 3、如何负载均衡，路由等集群功能？  
 4、熔断，限流等治理能力。  
 5、重试等策略  
 6、高可用、监控、性能等等。   
 
# 分布式缓存  
1.Redis的数据结构：String、List、set、zset(有序集合）、Hash（k-v结构）  
2.Redis的特性：主从模式、哨兵模式、集群模式    
3.Redis持久化的两种方式：RDB、AOF及各自的优缺点和适用场景
4.分布式缓存常见问题：缓存雪崩、缓存穿透、缓存击穿  
5.Redission:基于Netty NIO，API线程安全。大量丰富的分布式功能特性，比如JUC的线程安全集合和工具的分布式版本，分布式的基本数据类型和锁等。  
6.Hazelcast：Hazelcast IMGD(in-memory data grid) 是一个标准的内存网格系统；它具有以下的一 些基本特性：   
①. 分布式的：数据按照某种策略尽可能均匀的分布在集群的所有节点上。  
②. 高可用：集群的每个节点都是 active 模式，可以提供业务查询和数据修改事务；部 分节点不可用，集群依然可以提供业务服务。  
③. 可扩展的：能按照业务需求增加或者减少服务节点。  
④. 面向对象的：数据模型是面向对象和非关系型的。在 java 语言应用程序中引入 hazelcast client api是相当简单的。  
⑤. 低延迟：基于内存的，可以使用堆外内存。
  
# 分布式消息队列  
1.KafKa核心概念：  
①Broker：Kafka 集群包含一个或多个服务器，这种服务器被称为 broker。  
②Topic：每条发布到 Kafka 集群的消息都有一个类别，这个类别被称为 Topic。 （物理上不同 Topic 的消息分开存储，逻辑上一个 Topic 的消息虽然保存于一个或 多个 broker 上，但用户只需指定消息的 Topic 即可生产或消费数据而不必关心数 据存于何处）。  
③Partition：Partition 是物理上的概念，每个 Topic 包含一个或多个 Partition。  
④Producer：负责发布消息到 Kafka broker。  
⑤Consumer：消息消费者，向 Kafka broker 读取消息的客户端。  
⑥Consumer Group：每个 Consumer 属于一个特定的 Consumer Group（可为每个 Consumer 指定 group name，若不指定 group name 则属于默认的 group）。  
2.RabbitMQ/RocketMQ  
3.Pulsar:基于topic，支持namespace和多租户  
4.EIP:两个开源EIP实现框架，Camel和Spring Integration 
