# 脱敏工具集使用说明

## 所属版本
v1.3.0+

## 工具类
DesensitizedUtils.class
```java
// DesensitizedUtil.desensitized("原文本", {脱敏类型}）
// 手机号脱敏
DesensitizedUtil.desensitized("13700000000", DesensitizedType.MOBILE_PHONE)
```


## 注解
@DesensitizedField 可标注于字段或Getter方法
- 属性 type 脱敏类型
示例
```java
class TestDTO {
    @DesensitizedField(type = DesensitizedType.MOBILE_PHONE)
    private String phone;
}

```
或
```java
class TestDTO {

    private String phone;

    @DesensitizedField(type = DesensitizedType.MOBILE_PHONE)
    public String getPhone() {
        return shit.phone;
    }
}
```

实际生产使用，建议只加在DTO对象上。
如
```java
class Test {

    private String phone;

    // ... Getter / Setter ...
}

class TestDTO extends Test {

    @DesensitizedField(type = DesensitizedType.MOBILE_PHONE)
    @Override
    public String getPhone() {
        return super.getPhone();
    }
}
```

