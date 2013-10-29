package org.grove.common.nio;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferTest {

	public static void main(String[] args) throws IOException{
		
		String content = "搜索技术－淘宝by昆仑1dkeepunreadpreviewJVM的GC简介和实例EvernoteInstapaperAddtoPocketDeliciousTwitterLinkedInFacebookBufferMailsavef"
				+ "orlater+TAG本文是一次内部分享中总结了jvmgc的分类和一些实例,内容是introduction级别的,供初学人士参考.成文仓促,难免有些错误,如果有大牛发现,请留言,我一定及时更正,"
				+ "谢谢!JVM内存布局主要包含下面几个部分：JavaVirtualMachineStack:也就是我们常见的局部变量栈，线程私有，保存线程执行的局部变量表、操作栈、动态连接等。JavaHeap："
				+ "我们最常打交道的内存区域，几乎所有对象的实例都在这个区域分配。所谓的GC基本上也就是跟这个区域打交道。MethodArea：包含被虚拟机加载的类、常量、静态变量等数据。"
				+ "Hotspot虚拟机使用分代收集算法，将JavaHeap根据对象的存活周期分为多个区域：新生代、老生代和永生代。新生代和老生代位于Javaheap中，是垃圾收集器主要处理的内存区域。"
				+ "永生代则基本上等价于MethodArea，也就是说其中包含的数据在jvm进程存活期间会一直存在，一般不会发生变化。java堆内存的布局如下图所示：jvm堆布局使用jstat可以查看某个"
				+ "java进程的内存状况：chendeMacBook-Air:~eleforest$jstat-gc16136S0CS1CS0US1UECEUOCOUPCPUYGCYGCTFGCFGCTGCT1024.01024.00.00.08192.02867.910240.00"
				+ ".021248.02637.200.00000.0000.000其中各个指标介绍如下：（单位为KB）S0C,S1C,S0U,S1U:01幸存区(survivor)容量(C:Capacity)使用量（U:Used)。EC,EU:Eden(伊甸)"
				+ "区容量用量。Eden和survivor两个区域位于新生代，由于新生代GC一般是使用复制算法进行清理，因此按照复制算法的原理将新生代分成了3个区域：Eden、Survivor0、Survivor1。"
				+ "Hotspot虚拟机的3个空间缺省配比为:8:1:1,jvm只会使用eden和1个survivor作为新生代空间.当新生代空间不足时发生minorgc,此时根据复制算法,jvm会首先1将eden和fromsurvivor"
				+ "中存活的对象拷贝到tosurvior中，然后2）释放eden和from中的所有需要回收对象，最后3）调换fromtosurvior，jvm将eden和新的fromsurvior作为新生代。当然上述minorgc顺利执行"
				+ "还取决于很多因素，这里只描述了最理想化的状态。OC,OU:Old(老生代）容量用量。老生代常用的垃圾收集器有CMS、SerialOld、ParallelOld等PC,PU:Perm(永生代）容量用量。"
				+ "YGCYGCT:YoungGC次数和总耗费时间。YoungGC也就是MinorGC，新生代中内存不够时触发，通常采用复制算法进行，回收速度较快，对系统的影响较小。FGCFGCT：FullGC次数和总耗费时间。"
				+ "FullGC是在javaheap空间不足（包括New和Old区域）时触发，会分别清理新生代、老生代，通常耗时较长，对系统有较大影响，应该尽量避免。GCT：GC总耗时。常用的垃圾收集器包括下面几个"
				+ "Serial：最基本，历史最悠久的收集器，单线程收集垃圾内存，在新生代采用复制算法，在老生代使用标记-整理算法ParNew：Serial的多线程版本，主要用于新生代收集。与CMS收集器配合"
				+ "成为现在最常用的server收集器ParallelScavenge：也是一个并行收集器，使用与ParNew完全不同的收集策略，具体的差别还在研究中CMS：ConcurrentMarkSweep收集器，大名鼎鼎，"
				+ "其目标是获取最短回收停顿时间，是server模式下最常用的收集器G1：最新的收集器，木有用过啊下面将会用一段简单的程序演示jvm在配置使用不同的收集器情况下，GC行为的不同点，"
				+ "通过GC的行为能够了解到不同收集器的收集策略和行为。代码非常简单：jvmbasicargs:-Xmx20M-Xms20M-Xmn10M-XX:+PrintGCDetails-XX:SurvivorRatio=8publicclassMain"
				+ "{publicstaticvoidmain(String[]args)throwsException{byte[]alloc1,alloc2,alloc3,alloc4;alloc1=newbyte[2*1024*1024];Thread.sleep(2000);alloc2=newbyte"
				+ "[2*1024*1024];Thread.sleep(2000);alloc3=newbyte[2*1024*1024];Thread.sleep(2000);alloc4=newbyte[2*1024*1024];Thread.sleep(2000);}}其中上例中的"
				+ "jvm参数解释如下：Xmx	最大堆容量，包含了新生代和老生代的堆容量Xms	最小堆容量，此时配置与Xmx一样，避免了申请空间时的堆扩展Xmn	新生代容量，包含eden，survivor1，"
				+ "survivor2三个区域PrintGCDetails	让jvm在每次发生gc的时候打印日志，利于分析gc的原因和状况SurvivorRatio	新生代中eden的比例，如果设置为8，意味着新生代中eden占据"
				+ "80%的空间，两个survivor分别占据10%测试环境为macos10.8，jdk版本如下：chendeMacBook-Air:~eleforest$java-versionjavaversionJava(TM)SERuntimeEnvironment"
				+ "(build1.7.0_09-b05)JavaHotSpot(TM)64-BitServerVM(build23.5-b02,mixedmode)示例1：让jvm自动选择收集器直接运行上述代码，用jstat观察gc情况如下："
				+ "chendeMacBook-Air:~eleforest$jstat-gc217291000S0CS1CS0US1UECEUOCOUPCPUYGCYGCTFGCFGCTGCT1024.01024.00.00.08192.0819.910240.00.021248.02637.200."
				+ "00000.0000.0001024.01024.00.00.08192.02867.910240.00.021248.02637.200.00000.0000.0001024.01024.00.00.08192.02867.910240.00.021248.02637.200"
				+ ".00000.0000.0001024.01024.00.00.08192.04915.910240.00.021248.02637.200.00000.0000.0001024.01024.00.00.08192.04915.910240.00.021248.02637.200.00000"
				+ ".0000.0001024.01024.00.00.08192.06963.910240.00.021248.02637.200.00000.0000.0001024.01024.00.00.08192.06963.910240.00.021248.02637.200.00000.0000."
				+ "0001024.01024.00.0292.98192.02375.910240.06144.021248.02640.310.00700.0000.0071024.01024.00.0292.98192.02375.910240.06144.021248.02640.310.00700."
				+ "0000.007由上述的结果可见，程序启动时，Eden使用了819.9K的空间（我现在还不知道819k是什么东西的开销），S1、S2、老生代均没有占用，永生代则使用了2.6MB空间，其中包含了包含"
				+ "被虚拟机加载的类、常量、静态变量等数据。随后连续三次申请了2MB的空间，这些数据都被放到了Eden区域，这就是jvm内存分配的第一个原则：对象优先在Eden分配，这个原则只在Eden空间足够，"
				+ "且申请的内存小于jvm参数PretenureSizeThreshold设置值时生效（根据采用的收集器不同，还会有很多不同情况）注意看第四次申请2MB空间，此时由于Eden空间无法容纳新的数组，因此发生了"
				+ "一次MinorGC，具体的GClog如下所示：[GC[DefNew:6963K-&gt;292K(9216K),0.0065350secs]6963K-&gt;6436K(19456K),0.0065940secs][Times:user=0.01sys=0.00,"
				+ "real=0.00secs]Heapdefnewgenerationtotal9216K,used2832K[0x0000000112230000,0x0000000112c30000,0x0000000112c30000)edenspace8192K,31%used"
				+ "[0x0000000112230000,0x00000001124aaf60,0x0000000112a30000)fromspace1024K,28%used[0x0000000112b30000,0x0000000112b793b0,0x0000000112c30000)"
				+ "tospace1024K,0%used[0x0000000112a30000,0x0000000112a30000,0x0000000112b30000)tenuredgenerationtotal10240K,used6144K[0x0000000112c30000,"
				+ "0x0000000113630000,0x0000000113630000)thespace10240K,60%used[0x0000000112c30000,0x0000000113230030,0x0000000113230200,0x0000000113630000)"
				+ "compactingpermgentotal21248K,used2647K[0x0000000113630000,0x0000000114af0000,0x0000000118830000)thespace21248K,12%used[0x0000000113630000,"
				+ "0x00000001138c5ec0,0x00000001138c6000,0x0000000114af0000)Nosharedspacesconfigured.其中第一行中的代表使用的收集器是Serial收集器，"
						+ "这次MinorGC使用copy算法，做了下面几件事情：检索heap中的对象，将还能通过GCroots能够遍历到的对象copy到to区中如果需要copy的对象没法进入from区中，"
						+ "则将其晋升到老年代，本例中即发生了这种情况，3个2MB的数组全部晋升到老生代（OU：6144）清理eden和from中无用的垃圾互换from和to空间";

		ByteBuffer buf = ByteBuffer.allocate(256);
		
		byte[] contentData = content.getBytes();
		
		for(int i=0;i<contentData.length;i++){
			//if(i>=buf.limit()){
			if(!buf.hasRemaining()){
				buf.flip();
				byte[] data1 = new byte[buf.limit()];
				buf.get(data1);
				buf.clear();
				System.out.println(new String(data1,"utf-8"));
			}else{
				buf.put(contentData[i]);
			}
		}
	}
	
}
