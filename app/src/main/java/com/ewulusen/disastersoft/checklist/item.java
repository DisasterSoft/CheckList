package com.ewulusen.disastersoft.checklist;

public class item {
    private String name,menny,db;


    public item(String name,String menny,String db)
    {
        this.db=db;
        this.menny=menny;
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public  void setName(String name)
    {
        this.name=name;
    }
    public String getMenny()
    {
        return menny;
    }
    public  void setMenny(String menny)
    {
        this.menny=menny;
    }
    public String getDb()
    {
        return db;
    }
    public  void setDb(String db)
    {
        this.db=db;
    }


}

