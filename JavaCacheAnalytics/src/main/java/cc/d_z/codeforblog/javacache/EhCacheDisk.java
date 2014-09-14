package cc.d_z.codeforblog.javacache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * @author davy <br>
 *         2014年9月14日 下午6:53:15 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class EhCacheDisk<K, V> implements ICache<K, V> {
	public net.sf.ehcache.Ehcache cache;

	public EhCacheDisk() {
		DiskStoreConfiguration diskStoreConfiguration = new DiskStoreConfiguration();
		diskStoreConfiguration.setPath("/tmp");
		Configuration configuration = new Configuration();
		configuration.addDiskStore(diskStoreConfiguration);
		CacheConfiguration cacheConf = new CacheConfiguration("cache_disk", 0).memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU).overflowToDisk(false).eternal(false).diskExpiryThreadIntervalSeconds(0);
		CacheManager cacheManager = new CacheManager(configuration);
		cacheManager.addCache(new Cache(cacheConf));
		cache = cacheManager.getEhcache("cache_disk");
	}

	@Override
	public void put(K k, V v) {
		cache.put(new Element(k, v));
	}

	@Override
	public V get(K k) {
		Element v = cache.get(k);
		if (v != null) {
			return (V) v.getObjectValue();
		} else {
			return null;
		}
	}
}
