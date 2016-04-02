package org.oopdev.io;

import org.oopdev.io.exception.AlreadyBoundException;
import org.oopdev.io.exception.UnBoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 */
public abstract class IOContext<K, V extends IOMeta> {
    /**
     * K key of the component
     * V is component meta data
     */
    private Map<K,V> kvMap = new ConcurrentHashMap<>();
    /**
     * String is alias to component
     * K key of the component
     */
    private Map<String,K> aliasMap = new ConcurrentHashMap<>();

    /**
     *
     * @param k
     * @return
     */
    public V getIOMeta(K k){
        return kvMap.get(k);
    }

    /**
     *
     * @param alias
     * @return
     */
    public V getIOMetaByAlias(String alias){
        K key = aliasMap.get(alias);
        return (key != null) ? kvMap.get(key) : null;
    }

    /**
     *
     * @param alias
     * @param key
     * @param metaData
     * @throws AlreadyBoundException
     */
    public void addIOMeta(String alias,K key,V metaData) throws AlreadyBoundException {
        K existKey = aliasMap.get(alias);
        if(existKey != null){
            throw new AlreadyBoundException(alias + " alias already bind with " + existKey + "! ");
        }

        V existData = kvMap.get(key);

        if(existData != null){
            throw new AlreadyBoundException(key + " key already bind ! ");
        }

        aliasMap.put(alias,key);
        kvMap.put(key,metaData);
    }

    /**
     *
     * @param alias
     */
    public void removeIOMetaByAlias(String alias){
        K key = aliasMap.get(alias);
        if(key == null){
            throw new UnBoundException(alias + " alias not exist on components ! ");
        }
        removeIOMeta(key);
        aliasMap.remove(alias);
    }

    /**
     *
     * @param key
     */
    public void removeIOMeta(K key){
        V beanClass = kvMap.get(key);

        if(beanClass == null){
            throw new UnBoundException(key + " name not exist on components ! ");
        }
        kvMap.remove(key);
    }
    /**
     *
     * @param clazz
     */
    public  abstract void bind(Class<?> clazz);

}
