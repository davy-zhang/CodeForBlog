package cc.d_z.codeforblog.javacache;

/**
 * @author davy <br>
 *         2014-09-14 15:57 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */

public interface ICache<K, V> {
    public void put(K k, V v);

    public V get(K k);
}
