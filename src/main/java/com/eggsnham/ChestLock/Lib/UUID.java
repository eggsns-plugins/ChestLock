package com.eggsnham.ChestLock.Lib;

public class UUID
{
    public String UUID()
    {
        java.util.UUID uuid = java.util.UUID.randomUUID();
        String string = uuid.toString();
        return string;
    }
}
