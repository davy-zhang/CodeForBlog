package cc.d_z.codeforblog.javacache;

import org.apache.jcs.JCS;
import org.apache.jcs.access.CacheAccess;
import org.apache.jcs.access.exception.CacheException;

/**
 * @author davy <br>
 *         2014-09-14 15:57 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class JCSCache<K, V> implements ICache<K, V> {
	public CacheAccess cache;

	public JCSCache() {
		try {
			cache = JCS.getInstance("OUR_REGION");
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void put(K k, V v) {
		try {
			cache.put(k, v);
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}

	@Override
	public V get(K k) {
		return (V) cache.get(k);
	}
}
