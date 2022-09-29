package com.vincent.password_manager.utils;

public final class ConstantType 
{
    //CORS
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "http://localhost:4200";

    //USER
    public static final String USER_ROLE_NORMAL = "NORMAL";
    public static final String USER_ROLE_ADMIN = "ADMIN";
    public static final String USER_ROLE_OWNER = "OWNER";

    public static final String HAS_ANY_ALL_AUTHORITY = "hasAnyAuthority('NORMAL', 'OWNER', 'ADMIN')";

    public static final String USER_STATUS_NORMAL = "NORMAL";
    public static final String USER_STATUS_LOCK = "LOCK";

    //item
    public static final String ITEM_STATUS_NORMAL = "NORMAL";
    public static final String ITEM_STATUS_UNSOLD = "UNSOLD";
    public static final String ITEM_STATUS_BANDED = "BANDED";

    //offer
    public static final String OFFER_STATUS_PEDNING = "PENDING";
    public static final String OFFER_STATUS_FULLFILLED = "FULLFILLED";

    //SHIPPING
    public static final String SHIPPING_STATUS_PEDNING = "PENDING";


    public static final String BUCKET_NAME = "aws-vincent-frontend-demo";

    // public static String hasAnyAthority()
    // {
    //     StringBuilder stringBuilder = new StringBuilder();
    //     stringBuilder.append("hasAnyAuthority(");
    //     stringBuilder.append(String.format("'%s'", USER_ROLE_NORMAL));
    //     stringBuilder.append(String.format("'%s'", USER_ROLE_ADMIN));
    //     stringBuilder.append(String.format("'%s'", USER_ROLE_ADMIN));
    //     stringBuilder.append(")");
    //     return stringBuilder.toString();
    // }
}
