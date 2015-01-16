package com.lhx.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by lhx on 15-1-16 上午10:04
 *
 * @project gitjava
 * @package com.lhx.entity
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class Index {

    private String id ;
    @Field
    private String name ;
    @Field
    private String manu ;
    @Field
    private String[] cat ;
    @Field
    private String[] features ;
    @Field
    private float price ;
    @Field
    private int popularity ;

    @Override
    public String toString() {
        return this.id + "#" + this.name + "#" + this.manu + "#" + this.cat;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getId() {
        return id;
    }

    @Field
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String[] getCat() {
        return cat;
    }

    public void setCat(String[] cat) {
        this.cat = cat;
    }

    public String[] getFeatures() {
        return features;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Field
    private boolean inStock ;
}
