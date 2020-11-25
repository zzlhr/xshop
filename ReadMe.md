# XShop
一套基于springboot + vue的开源商城

## TODO LIST
- [ ] flutter app
- [ ] vue升级

**处于开发阶段，暂时欠缺部分功能**



## 贡献者列表

感谢所有代码贡献者

如何贡献代码参照 [如何贡献代码](./CONTRIBUTING.md)

- guandw


## 项目结构
- xshop-admin vue后台管理
- xshop-admin-api 后台api
- xshop-core 核心代码
- xshop-dao dao层
- xshop-pojo pojo对象
- xshop-service service层
- xshop-web-api webapp api
- xshop-web-app vue移动端商场页面
- Xshop-app-api flutter app api部分

## 如何贡献代码
参阅 [贡献文档](./CONTRIBUTING.md)

## 开发进度
参阅 [TODO](./docs/todo.md)


## 启动
1. 还原数据库
```bash
# 还原
mysql -uroot -p < ./sql/xshop.sql
# Enter password:
```


2. 安装依赖
```bash
mvn compile
mvn install
```

3. 编译后台
```bash
cd xshop-web-api
mvn package
java -jar target/xshop-web-api.jar
```


4. 编译页面
```bash
cd xshop-web-app
npm install
npm run dev
```



