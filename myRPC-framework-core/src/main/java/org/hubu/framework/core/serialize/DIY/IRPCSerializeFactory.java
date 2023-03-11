package org.hubu.framework.core.serialize.DIY;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hubu.framework.core.serialize.SerializeFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IRPCSerializeFactory implements SerializeFactory {

    @Data
    @AllArgsConstructor
    class ByteHolder {

        private byte[] bytes;
    }

    @Override
    public <T> byte[] serialize(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        List<ByteHolder> byteHolderList = new ArrayList<>();
        int totalSize = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object orgVal = field.get(t);
                byte[] arr = this.getByteArrayByField(field,orgVal);
                totalSize+=arr.length;
                byteHolderList.add(new ByteHolder(arr));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        byte[] result = new byte[totalSize];
        int index = 0;
        for (ByteHolder byteHolder : byteHolderList) {
            System.arraycopy(byteHolder.getBytes(),0,result,index,byteHolder.getBytes().length);
            index+=byteHolder.getBytes().length;
        }
        return result;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return null;
    }

    public byte[] getByteArrayByField(Field field, Object orgVal) {
        Class type = field.getType();
        if("java.lang.Integer".equals(type.getName())){
            return ByteConvertUtils.intToByte((Integer) orgVal);
        } else if("java.lang.Long".equals(type.getName())){
            return ByteConvertUtils.longToByte((Long) orgVal);
        } else if("java.lang.Short".equals(type.getName())){
            return ByteConvertUtils.shortToByte((Short) orgVal);
        } else if("java.lang.String".equals(type.getName())){
            if(orgVal==null){
                return new byte[0];
            }
            return ((String)orgVal).getBytes();
        }
        return new byte[0];
    }


}
