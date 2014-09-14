package cc.d_z.codeforblog.javacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author davy <br>
 *         2014-09-14 15:57 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class GuavaCache<K, V> implements ICache<K, V> {
	public Cache<K, V> cache = CacheBuilder.newBuilder().build();

	public GuavaCache() {

	}

	@Override
	public void put(K k, V v) {
		cache.put(k, v);
	}

	@Override
	public V get(K k) {
		return cache.getIfPresent(k);
	}
}
