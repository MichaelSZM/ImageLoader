package com.szm.administrator.loaderlibs.loader;

import com.szm.administrator.loaderlibs.loader.api.LoaderAPI;

import java.util.HashMap;
import java.util.Map;

/**
 * 加载器管理者，负责选出合适的加载器
 * Created by michael on 2016/3/15.
 */
public class LoaderManager {

    private Map<String,LoaderAPI> mapLoader=new HashMap<>();

    private static LoaderManager loaderManager=new LoaderManager();

    private LoaderManager(){
        registeLoader("http",new UrlLoader2());
        registeLoader("https",new UrlLoader2());
        registeLoader("file",new LocalLoader2());
    }

    public static LoaderManager getInstance(){
        return loaderManager;
    }

    /**
     * 获取合适的加载器
     * @param schema
     * @return
     */
    public LoaderAPI getLoader(String schema){
        if(schema==null){
            return new NullLoader();
        }
        if(mapLoader.containsKey(schema)){
            return mapLoader.get(schema);
        }
        return new NullLoader();
    }


    /**
     * 注册加载器
     * @param schema
     * @param loaderAPI
     */
    public void registeLoader(String schema,LoaderAPI loaderAPI){
        mapLoader.put(schema,loaderAPI);
    }

}
