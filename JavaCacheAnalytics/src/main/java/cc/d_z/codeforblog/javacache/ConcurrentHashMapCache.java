package cc.d_z.codeforblog.javacache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author davy <br>
 *         2014-09-14 15:57 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class ConcurrentHashMapCache<K, V> implements ICache<K, V> {
	ConcurrentHashMap<K, V> table = new ConcurrentHashMap<K, V>();

	@Override
	public void put(K k, V v) {
		table.put(k, v);
	}

	@Override
	public V get(K k) {
		return table.get(k);
	}
}
