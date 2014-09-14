package cc.d_z.codeforblog.javacache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author davy <br>
 *         2014年9月14日 下午5:46:41 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class Main {
	public static final int dataSize = 100;
	public static final int nThreads = 8;
	public static final long times = 1000000;
	public static final String value = build(dataSize);

	public static void main(String[] args) throws InterruptedException {

		analytics(new GuavaCache<Long, String>());
		analytics(new HashTableCache<Long, String>());
		analytics(new ConcurrentHashMapCache<Long, String>());
		analytics(new EhCacheMemory<Long, String>());
		// analytics(new EhCacheDisk<Long, String>());
		// analytics(new JCSCache<Long, String>());
	}

	public static void analytics(final ICache<Long, String> cache) throws InterruptedException {
		analytics("put", cache, new Runnable() {
			@Override
			public void run() {
				try {
					put(cache, times, value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		analytics("get", cache, new Runnable() {
			@Override
			public void run() {
				try {
					get(cache, times);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void analytics(String type, ICache<Long, String> cache, Runnable runnable) throws InterruptedException {
		long start = System.nanoTime();
		ExecutorService service = Executors.newFixedThreadPool(nThreads);
		for (int i = 0; i < nThreads; i++) {
			service.execute(runnable);
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.DAYS);
		long end = System.nanoTime();
		if ("get".equals(type))
			System.out.println(cache.getClass().getSimpleName() + "\t" + type + "\t" + dataSize + "\t" + nThreads + "\t" + times + "\t" + (end - start) + "\t");
	}

	private static String build(int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	public static void put(final ICache<Long, String> cache, long nums, String value) throws InterruptedException {
		for (long i = 0; i < nums; i++)
			cache.put(i, value);
	}

	public static void get(final ICache<Long, String> cache, long nums) throws InterruptedException {
		for (long i = 0; i < nums; i++) {
			String value = cache.get(i);
			if (value == null) {
				System.err.println(value);
			}
		}
	}
}
