# **1.（选做）使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。**

本机物理内存：12GB   Windows7 64位操作系统   jdk1.8.0_05
### 采样3次持续1秒时间 平均吞吐量
| 堆内存大小 | SerialGC | ParallelGC | CMCGC | G1GC |
|:--------:|:----------:|:----------:|:----------:|:----------:|
|  512M	 |  6727  |	 6770  |  8035  |  7393  |
|  1G  |  8516  |	8394  |  9379  |  8930  |
|  2G  |  8429  |	 9865  |  9253  |	7821  |
|  3G  |  9054  |  9390  |  9099  |  8204  |
|  4G  |  8273  |  7991  |  8894  |  9311  |
|  5G  |  8050  |  9644  |  8229  |  9558  |
|  6G  |  6363  |  8280  |  7584  |  9597  |
|  7G  |  7125  |  6680  |  7597  |  9418  |
|  8G  |  7794  |  7656  |  6976  |  9649  |
|  10G  |  7541  |  7725  |  6203  |  9769  |

本次采样测试，以下为各GC回收器吞吐量最大时候的堆内存设置情况，随机性较大
SerialGC（3G）、ParallelGC(2G) 、CMCGC(1G)、G1GC(10G)

###总结：堆内存大小的设置对程序的吞吐量有很大影响。但并不是越大越好。实际项目中，要根据业务场景要求，结合压测及软硬件资源选择合适GC回收器。


# **2.（选做）使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。**
### **使用默认GC（ParallelGC）**
#####启动参数： java -jar -Xmx2g -Xms2g -XX:+PrintGCDetails -XX:+PrintGCDateStamps gateway-server-0.0.1-SNAPSHOT.jar

#####压测命令：sb -u http://localhost:8088/api/hello -c 10 -N 30， 10个并发， 压测30s

#####压测结果：
######&emsp;Requests: 23997
######&emsp;RPS: 772.1
######&emsp;90th Percentile: 6ms
######&emsp;95th Percentile: 9ms
######&emsp;99th Percentile: 21ms
######&emsp;Average: 2.7ms
######&emsp;Min: 0ms
######&emsp;Max: 425ms


### **使用默认G1GC（ParallelGC）**
#####启动参数：  java -jar -Xmx2g -Xms2g -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps gateway-server-0.0.1-SNAPSHOT.jar

#####压测命令：sb -u http://localhost:8088/api/hello -c 10 -N 30， 10个并发， 压测30s

#####压测结果：
######&emsp;Requests: 25347
######&emsp;RPS: 813.7
######&emsp;90th Percentile: 6ms
######&emsp;95th Percentile: 9ms
######&emsp;99th Percentile: 20ms
######&emsp;Average: 2.8ms
######&emsp;Min: 0ms
######&emsp;Max: 2098ms

### 结论：设置堆内存2G的情况下，整体吞吐量 ParallelGC和G1GC差不多。

# **4.（必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。**

#####   SerialGC: 串行GC对年轻代使用 mark-copy（标记-复制） 算法，对老年代使用 mark-sweep-compact（标记- 清除-整理） 算法。 两者都是单线程的垃圾收集器，不能进行并行处理，所以都会触发全线暂停（STW），停止所有的应用线 程。因此这种GC算法不能充分利用多核CPU。不管有多少CPU内核，JVM 在垃圾收集时都只能使用单个核心。

#####   ParallelGC: 并行垃圾收集器对年轻代使用 标记-复制(mark-copy) 算法，对老年代使用 标记-清除-整理(marksweep-compact) 算法。年轻代和老年代的垃圾回收时都会触发STW事件，暂停所有的应用线程，再来执 行垃圾收集。在执行 标记 和 复制/整理 阶段时都使用多个线程，因此得名“ Parallel ”。通过多个GC 线程并行执行的方式，能使JVM在多CPU平台上的GC时间大幅减少。
           通过命令行参数 -XX:ParallelGCThreads=NNN 可以指定 GC 线程的数量，其默认值为CPU内核数量。

#####   CMCGC: CMS也可称为 并发标记清除垃圾收集器 。其设计目标是避免在老年代GC时出现长时间的卡顿。默认情况下，CMS 使用的并发线程数等于CPU内核数的 1/4 。
 &emsp;CMS的垃圾回收过程：
  ###### &emsp;阶段 1：Initial Mark（初始标记）这个阶段伴随着STW暂停。初始标记的目标是标记所有的根对象，包括 GC ROOT 直接 引用的对象，以及被年轻代中所有存活对象所引用的对象。后面这部分也非常重要，因为老年代是独立进行回收的。
  ###### &emsp;阶段 2：Concurrent Mark（并发标记） 在并发标记阶段，CMS 从前一阶段 “Initial Mark” 找到的 ROOT 开始算起，遍历老年代并标记所有的存活对 象
  ###### &emsp;阶段 3：Concurrent Preclean（并发预清理） 此阶段同样是与应用线程并发执行的，不需要停止应用线程。
  ###### &emsp;阶段 4：Concurrent Abortable Preclean(可取消的并发预清理)此阶段也不停止应用线程，尝试在会触发STW 的 Final Remark 阶段开始之前，尽可能地多干一些活。 本阶段的具体时间取决于多种因素，因为它循环做同样的事情，直到满足某一个退出条件(如迭代次数，有 用工作量，消耗的系统时间等等)。
  ###### &emsp;阶段 5： Final Remark（最终标记）最终标记阶段是此次GC事件中的第二次(也是最后一次)STW停顿。 本阶段的目标是完成老年代中所有存活对象的标记。因为之前的预清理阶段是并发执行的，有可能GC线程 跟不上应用程序的修改速度。所以需要一次 STW 暂停来处理各种复杂的情况。 通常CMS会尝试在年轻代尽可能空的情况下执行 final remark 阶段，以免连续触发多次 STW 事件。
  ###### &emsp;阶段 6： Concurrent Sweep（并发清除）此阶段与应用程序并发执行，不需要STW停顿。目的是删除不再使用的对象，并回收他们占用的内存空间。 
  ###### &emsp;阶段 7： Concurrent Reset（并发重置）此阶段与应用程序线程并发执行，重置CMS算法相关的内部数据结构，下一次触发GC时就可以直接使用。

#####   G1GC: G1的全称是Garbage-First，意为垃圾优先，哪一块的垃圾最多就优先清理它。
  ###### &emsp;G1的垃圾回收过程：
  ###### &emsp;Evacuation Pause: young(纯年轻代模式转移暂停)当年轻代空间用满后，应用线程会被暂停，年轻代内存块中的存活对象被拷贝到存活区。 如果还没有存活 区，则任意选择一部分空闲的内存块作为存活区。
  ###### &emsp;Concurrent Marking(并发标记) 当堆内存的总体使用比例达到一定数值时，就会触发并发标记。 这个默认比例是 45% ，但也可以通过JVM 参数 InitiatingHeapOccupancyPercent 来设置。和CMS一样，G1的并发标记也是由多个阶段组 成，其中一些阶段是完全并发的，还有一些阶段则会暂停应用线程。
  ###### &emsp;Evacuation Pause (mixed)(转移暂停: 混合模式) 并发标记完成之后，G1将执行一次混合收集(mixed collection)，不只清理年轻代，还将一部分老年代区域也加入到 collection set 中。 混合模式的转移暂停(Evacuation pause)不一定紧跟并发标记阶段。 在并发标记与混合转移暂停之间，很可能会存在多次 young 模式的转移暂停。 混合模式 就是指这次GC事件混合着处理年轻代和老年代的region。这也是G1等增量垃圾收集器的特 色。而ZGC等最新的垃圾收集器则不使用分代算法。 当然，以后可能还是会实现分代的，毕竟分代之后性 能还会有提升
  ###### &emsp;Full GC (Allocation Failure) G1是一款自适应的增量垃圾收集器。一般来说，只有在内存严重不足的情况下才会发生Full GC。 比如堆空 间不足或者to-space空间不足。
    


