package com.system.utils;


public class CacheKeyTransfor {

   /* private static final String colon = ":";
    private static final String separate = "-";
    private static final String finance_company = "浙江大搜车融资租赁有限公司";
    private static final String car_company = "杭州大搜车汽车服务有限公司";
    private static final String supplier_service = "杭州大搜车汽车服务有限公司";
    private static final String supplier_rental = "浙江大搜车融资租赁有限公司";

    public static String getKey(DataTypeEnum dataTypeEnum, String... keys) {
        if(dataTypeEnum == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int size = keys.length;
        for(int i = 0; i < size -1; i++) {
            String key = keys[i];
            sb.append(key);
            sb.append(separate);
        }
        sb.append(keys[size-1]);

        return dataTypeEnum.getCode() + colon + sb.toString();
    }

    public static String getCompanyProperty(String company) {
        if(company.startsWith(finance_company)) {
            return "融租";
        } else if(company.startsWith(car_company)) {
            return "汽服";
        }
        return null;
    }

    public static String getSupplierType(String supplierName) {
        if(supplierName.equals(supplier_service)) {
            return "汽服总公司";
        } else if(supplierName.startsWith(supplier_service)) {
            return "汽服分公司";
        } else if(supplierName.equals(supplier_rental)) {
            return "融租总公司";
        } else {
            return "主机厂";
        }

    }*/
}
